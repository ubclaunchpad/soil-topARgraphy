package ca.ubc.eml.soiltopargraphy.editor.ui.flag;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import ca.ubc.eml.soiltopargraphy.editor.db.AppRepository;

public class FlagMapViewModel extends AndroidViewModel {

    private AppRepository mRepository;

    private LiveData<List<Flag>> mAllFlags;

    // gets a reference to the repository and he list of words from the repository.
    public FlagMapViewModel (Application application) {
        super(application);               // needs AndroidViewModel
        mRepository = new AppRepository(application);
        mAllFlags = mRepository.getAllFlags();
    }

    LiveData<List<Flag>> getAllWords() { return mAllFlags; }

    public void insert(Flag flag) { mRepository.insertFlag(flag); }
}
