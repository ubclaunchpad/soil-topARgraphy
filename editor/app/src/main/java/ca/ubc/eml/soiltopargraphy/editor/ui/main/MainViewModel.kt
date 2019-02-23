package ca.ubc.eml.soiltopargraphy.editor.ui.main

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

import ca.ubc.eml.soiltopargraphy.editor.db.AppRepository
import ca.ubc.eml.soiltopargraphy.editor.ui.flag.Flag
import ca.ubc.eml.soiltopargraphy.editor.ui.terrain.Terrain
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainViewModel// gets a reference to the repository and he list of words from the repository.
(application: Application) : AndroidViewModel(application) {
    var flagItemClicked: Boolean = false
    var terrain: Terrain? = null

    private val mRepository: AppRepository

    internal val flags: LiveData<List<Flag>>
        get() = mRepository.allFlags

    //This is the flag that the Main Fragment is allowing the user to interact with
    //It should be set when the Main Fragment is displayed
    private var mFlag: Flag? = null

    init {
        mRepository = AppRepository(application)
    }// needs AndroidViewModel

    fun insert(flag: Flag) {
        mRepository.insertFlag(flag)
    }

    fun setFlag(flag: Flag) {
        mFlag = flag
    }

    //When the user clicks delete in the Main Fragment, it calls this function and deletes the
    //flag from the room database
    fun deleteFlag() {
        mRepository.deleteFlag(mFlag)
    }
    //method to create marker from the database
    //TODO:Possible to create marker that is only inside view ?
    fun createMarker(map: GoogleMap):List<MarkerOptions>?{
        var markers: MutableList<MarkerOptions> = mutableListOf<MarkerOptions>()
        if(flags.value!=null) {
            for (flag in flags.value!!.listIterator()) {
                val markerOption = MarkerOptions()
                markerOption.position(LatLng(flag.latitude.toDouble(), flag.longitude.toDouble()))
                markerOption.title(flag.latitude.toDouble().toString() + " : " + flag.longitude.toDouble())
                markerOption.draggable(true)
                markers.add(markerOption)
                //add the marker into the map
                val marker = map.addMarker(markerOption)
                marker.tag = flag
            }
            return markers
        }
        return null
    }
    // save the marker as flag inside the map
    //TODO: extend this method using information from other fragments
    fun saveMarkerAsFlag(point: LatLng){
        val flag = Flag("",point.latitude.toFloat(),point.longitude.toFloat(),null,0,0)
        insert(flag)
    }
}
