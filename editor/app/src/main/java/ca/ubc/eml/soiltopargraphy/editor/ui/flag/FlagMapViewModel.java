package ca.ubc.eml.soiltopargraphy.editor.ui.flag;

import android.arch.lifecycle.ViewModel;
import java.util.List;
import ca.ubc.eml.soiltopargraphy.editor.db.AppRepository;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

public class FlagMapViewModel extends AndroidViewModel {
    private boolean flagItemClicked;
    private AppRepository mRepository;

    public void setFlagItemClicked(boolean isClicked) {
        flagItemClicked = isClicked;
    }

    public boolean getFlagItemClicked() {
        return flagItemClicked;
    }

    // gets a reference to the repository and he list of words from the repository.
    public FlagMapViewModel (Application application) {
        super(application);               // needs AndroidViewModel
        mRepository = new AppRepository(application);
    }

    LiveData<List<Flag>> getFlags() { return mRepository.getAllFlags(); }

    public void insert(Flag flag) { mRepository.insertFlag(flag); }
}
