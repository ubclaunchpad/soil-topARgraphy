package ca.ubc.eml.soiltopargraphy.editor.ui.flag;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.AndroidViewModel;

import java.util.List;

import ca.ubc.eml.soiltopargraphy.editor.db.AppRepository;
import ca.ubc.eml.soiltopargraphy.editor.ui.terrain.Terrain;

public class FlagListViewModel extends AndroidViewModel {
    private LiveData<List<Flag>> flagList;
    private AppRepository mRepository;
    private Terrain terrain;

    public void setTerrain(Terrain mTerrain) {
        terrain = mTerrain;
    }

    public FlagListViewModel (Application application) {
        super(application);
        mRepository = new AppRepository(application);
    }

    public LiveData<List<Flag>> getFlagList(){
        if(flagList == null) {
            flagList = mRepository.getFlagsInTerrain(terrain);
        }
        return flagList;
    }
}
