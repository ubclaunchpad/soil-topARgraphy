package ca.ubc.eml.soiltopargraphy.editor.ui.main

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

import ca.ubc.eml.soiltopargraphy.editor.db.AppRepository
import ca.ubc.eml.soiltopargraphy.editor.ui.flag.Flag
import ca.ubc.eml.soiltopargraphy.editor.ui.terrain.Terrain

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
}
