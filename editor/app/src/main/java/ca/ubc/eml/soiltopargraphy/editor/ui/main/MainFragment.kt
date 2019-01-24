package ca.ubc.eml.soiltopargraphy.editor.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.Button
import ca.ubc.eml.soiltopargraphy.editor.JsonUtil
import ca.ubc.eml.soiltopargraphy.editor.R
import ca.ubc.eml.soiltopargraphy.editor.ui.flag.Flag
import ca.ubc.eml.soiltopargraphy.editor.ui.flag.FlagListFragment
import ca.ubc.eml.soiltopargraphy.editor.ui.infopanel.DescriptionPanelFragment
import ca.ubc.eml.soiltopargraphy.editor.ui.infopanel.InfoPanel
import ca.ubc.eml.soiltopargraphy.editor.ui.quizpanel.QuestionnairePanel
import ca.ubc.eml.soiltopargraphy.editor.ui.terrain.Terrain
import ca.ubc.eml.soiltopargraphy.editor.ui.terrain.TerrainListFragment
import android.graphics.drawable.BitmapDrawable


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var mViewModel: MainViewModel
    // Terrain that the user is interacting with
    private var mTerrain: Terrain? = null

    // Tells the main fragment which terrain was associated with the item that was clicked
    // from TerrainsInListview
    fun setTerrain(terrain: Terrain) {
        mTerrain = terrain
    }

    private fun onCreateInfoButtonClick(view: View){
        val manager = activity?.supportFragmentManager
        if(manager!=null){
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.container, DescriptionPanelFragment.newInstance())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)

        //attach listener to method for creating info panel
        val button = view.findViewById<View>(R.id.create_info_button)
        button.setOnClickListener { onCreateInfoButtonClick(view) }

        mViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // Sets the viewmodel terrain as the terrain that the user is currently interacting with
        // (couldn't do this in the setTerrain function because of the late initialization
        // of mViewModel)
        mViewModel.terrain = mTerrain

        //Ensures the toolbar initially displays the options that it should
        //(ie. just the listview option) when the flag item is not clicked
        mViewModel.flagItemClicked = false

        val flagToolBar = view.findViewById<Toolbar>(R.id.flagToolBar)

        //Sets the action bar for the activity as the custom flag toolbar
        (activity as AppCompatActivity).setSupportActionBar(flagToolBar)

        //Ensures that the menu is actually displayed in the toolbar
        setHasOptionsMenu(true)

        //Assigns an onClickListener to flag item that will update the toolbar when flag is clicked
        //TODO: Button flagItem should be replaced with actual flag item before merging
        val flagItem = view.findViewById<Button>(R.id.flagItem)
        flagItem.setOnClickListener {
            //When flag is clicked, updates the view model variable to reflect this and refreshes the toolbar view
            mViewModel.flagItemClicked = true
            activity!!.invalidateOptionsMenu()
        }

        //After clicking on the flag, if the user clicks anywhere else on the screen, updates
        //the toolbar to go back to showing only the listview item
        val frameLayout = view.findViewById<android.support.constraint.ConstraintLayout>(R.id.frameLayout)
        frameLayout.setOnClickListener { v ->
            mViewModel.flagItemClicked = false
            activity!!.invalidateOptionsMenu()
        }

        val heightmapView = view.findViewById<android.support.v7.widget.AppCompatImageView>(R.id.heightmapView)
        val heightmap: Bitmap = BitmapFactory.decodeByteArray(mViewModel.terrain!!.heightmap, 0, mViewModel.terrain?.heightmap!!.size)

        val drawableHeightmap = BitmapDrawable(resources, heightmap)
        heightmapView.setImageDrawable(drawableHeightmap)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.menu_flag_map, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        //THIS IS A HARDCODED FLAG FOR TESTING PURPOSES ONLY
        val questionnaire = QuestionnairePanel("","","","","")
        val infoPanel = InfoPanel("", "", Uri.EMPTY, questionnaire)
        val flag = Flag("", 0.toFloat(), 0.toFloat(), infoPanel, 1,1)

        val manager = activity!!.supportFragmentManager
        val transaction = manager.beginTransaction()
        var newFragment: Fragment? = null

        when (item!!.itemId) {
            R.id.action_to_listview ->
                //When the "List View" button is selected, swaps this fragment out for the flag list fragment
                newFragment = FlagListFragment()
            R.id.action_edit -> newFragment = DescriptionPanelFragment()
            R.id.action_delete ->
                //Deletes flag from room database
                mViewModel.deleteFlag()
            R.id.action_back ->
                // When the back button is selected, swaps this fragment out for the TerrainListFragment
                newFragment = TerrainListFragment()
            R.id.action_toJSON ->
                JsonUtil.infoToJson(flag.infoPanel)
            else -> {
            }
        }

        if (newFragment != null) {
            transaction.replace(R.id.container, newFragment)
            transaction.commit()
        }

        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        //When flag is clicked, shows only edit and delete options
        if (mViewModel.flagItemClicked) {
            menu!!.findItem(R.id.action_to_listview).isVisible = false
            menu.findItem(R.id.action_back).isVisible = false
            menu.findItem(R.id.action_edit).isVisible = true
            menu.findItem(R.id.action_delete).isVisible = true
            menu.findItem(R.id.action_toJSON).isVisible = true
        } else { //When flag is not clicked, shows only listview option
            menu!!.findItem(R.id.action_to_listview).isVisible = true
            menu.findItem(R.id.action_back).isVisible = true
            menu.findItem(R.id.action_edit).isVisible = false
            menu.findItem(R.id.action_delete).isVisible = false
            menu.findItem(R.id.action_toJSON).isVisible = false
        }
    }
}
