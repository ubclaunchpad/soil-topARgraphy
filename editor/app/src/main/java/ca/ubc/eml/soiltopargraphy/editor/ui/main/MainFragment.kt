package ca.ubc.eml.soiltopargraphy.editor.ui.main

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.*
import ca.ubc.eml.soiltopargraphy.editor.JsonUtil
import ca.ubc.eml.soiltopargraphy.editor.R
import ca.ubc.eml.soiltopargraphy.editor.db.AppDatabase
import ca.ubc.eml.soiltopargraphy.editor.db.FlagDao
import ca.ubc.eml.soiltopargraphy.editor.ui.flag.Flag
import ca.ubc.eml.soiltopargraphy.editor.ui.flag.FlagListFragment
import ca.ubc.eml.soiltopargraphy.editor.ui.infopanel.DescriptionPanelFragment
import ca.ubc.eml.soiltopargraphy.editor.ui.infopanel.InfoPanel
import ca.ubc.eml.soiltopargraphy.editor.ui.quizpanel.QuestionnairePanel
import ca.ubc.eml.soiltopargraphy.editor.ui.terrain.TerrainListFragment
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.terrain_list_fragment.*


class MainFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMapLongClickListener {


    //Google maps
    lateinit var googleMap: GoogleMap
    lateinit var mMapView: MapView

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var mViewModel: MainViewModel

    private fun onCreateInfoButtonClick(view: View) {
        val manager = activity?.supportFragmentManager
        if (manager != null) {
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.container, DescriptionPanelFragment.newInstance())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val view = inflater.inflate(R.layout.main_fragment, container, false)

        val addButton = view.findViewById<Button>(R.id.addButton)
        addButton.bringToFront()

        val shareButton = view.findViewById<Button>(R.id.shareButton)

        //attach listener to method for creating info panel
        val button = view.findViewById<View>(R.id.create_info_button)
        button.setOnClickListener { onCreateInfoButtonClick(view) }

        //listener method for adding a flag
        addButton.setOnClickListener {
            //add a flag in the centre of the map
            val center = this.googleMap.cameraPosition.target
            this.googleMap.addMarker(MarkerOptions()
                    .position(center)
                    .draggable(true))
        }

        //listener for share button
        shareButton.setOnClickListener {

            val dataBase = AppDatabase.getDatabase(Application())
            val flagDao = dataBase.flagDao()

            var flagsToExport = flagDao.getFlagsInTerrainLive().toString() //TODO

            var shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.setType("text/plain")
            var shareBody = "Flag data: "
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, flagsToExport)
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
            startActivity(Intent.createChooser(shareIntent, "Share flag data using: "))


        }

        mViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        //Ensures the toolbar initially displays the options that it should
        //(ie. just the listview option) when the flag item is not clicked
        mViewModel.flagItemClicked = false

        val flagToolBar = view.findViewById<Toolbar>(R.id.flagToolBar)

        //Sets the action bar for the activity as the custom flag toolbar
        (activity as AppCompatActivity).setSupportActionBar(flagToolBar)

        //Ensures that the menu is actually displayed in the toolbar
        setHasOptionsMenu(true)


        //After clicking on the flag, if the user clicks anywhere else on the screen, updates
        //the toolbar to go back to showing only the listview item
        val frameLayout = view.findViewById<android.support.constraint.ConstraintLayout>(R.id.frameLayout)
        frameLayout.setOnClickListener { v ->
            mViewModel.flagItemClicked = false
            activity!!.invalidateOptionsMenu()
        }


        //Update the database with new flag information when the save button is clicked
        val saveFlag = view.findViewById<Button>(R.id.saveButton)
        saveFlag.setOnClickListener {

            //get list of flags from database
            val db = AppDatabase.getDatabase(Application())

            val mFlagDao = db.flagDao()
            val flags = mFlagDao.allFlags

            val mainViewModel = MainViewModel(Application())

            if (flags != null){
                for (flag in flags as List<Flag> ){
                    mainViewModel.saveMarkerAsFlag(flag)
                }
            }
        }

        return view
    }

    //Creates the mapView for the Google map
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mMapView = view.findViewById(R.id.map)

        mMapView.onCreate(null)
        mMapView.onResume()
        mMapView.getMapAsync(this)
    }

    //Sets up what the map initially shows after the screen has loaded
    override fun onMapReady(googleMap: GoogleMap) {

        MapsInitializer.initialize(context)
        this.googleMap = googleMap
        googleMap.mapType = GoogleMap.MAP_TYPE_TERRAIN

        var start: CameraPosition = CameraPosition.fromLatLngZoom(LatLng(50.713836, -120.350008), 12.0f)
        mViewModel.createMarker(googleMap)
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(start))
        googleMap.setOnMapLongClickListener(this)
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
        val questionnaire = QuestionnairePanel("", "", "", "", "")
        val infoPanel = InfoPanel("", "", Uri.EMPTY, questionnaire)
        val flag = Flag("", 0.toFloat(), 0.toFloat(), infoPanel, 1, 1)

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

    override fun onMapLongClick(point: LatLng) {
        this.googleMap.addMarker(MarkerOptions()
                .position(point)
                .draggable(true))
    }
}