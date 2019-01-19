package ca.ubc.eml.soiltopargraphy.editor.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import ca.ubc.eml.soiltopargraphy.editor.R
import ca.ubc.eml.soiltopargraphy.editor.ui.flag.FlagListFragment
import ca.ubc.eml.soiltopargraphy.editor.ui.infopanel.DescriptionPanelFragment

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var mViewModel: MainViewModel

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

        // Button to add a new flag
        val addFlag = view.findViewById<View>(R.id.addButton)
        button.setOnClickListener {
            //Add flag to centre of screen
            val flagImage: ImageView = ImageView(context)
            flagImage.setImageResource(R.drawable.ic_flag)
            flagImage.x = (view.width / 2).toFloat()
            flagImage.y = (view.height / 2).toFloat()
            flagImage.visibility = (View.VISIBLE)
        }

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
            menu.findItem(R.id.action_edit).isVisible = true
            menu.findItem(R.id.action_delete).isVisible = true
        } else {
            menu!!.findItem(R.id.action_to_listview).isVisible = true
            menu.findItem(R.id.action_edit).isVisible = false
            menu.findItem(R.id.action_delete).isVisible = false
        } //When flag is not clicked, shows only listview options
    }
}
