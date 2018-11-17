package ca.ubc.eml.soiltopargraphy.editor.ui.flag;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import ca.ubc.eml.soiltopargraphy.editor.db.AppRepository;

public class FlagMapViewModel extends AndroidViewModel {
    private boolean flagItemClicked;

    public void setFlagItemClicked(boolean isClicked) {
        flagItemClicked = isClicked;
    }
    public boolean getFlagItemClicked() {
        return flagItemClicked;
    }

    private AppRepository mRepository;

    // gets a reference to the repository and he list of words from the repository.
    public FlagMapViewModel (Application application) {
        super(application);               // needs AndroidViewModel
        mRepository = new AppRepository(application);
    }

    LiveData<List<Flag>> getFlags() { return mRepository.getAllFlags(); }

    public void insert(Flag flag) { mRepository.insertFlag(flag); }

    //This is the flag that the FlagMapFragment is allowing the user to interact with
    //It should be set when the FlagMapFragment is displayed
    private Flag mFlag;

    public void setFlag(Flag flag) { mFlag = flag; }

    //When the user clicks delete in the FlagMapFragment, it calls this function and deletes the
    //flag from the room database
    public void deleteFlag() { mRepository.deleteFlag(mFlag); }
}
