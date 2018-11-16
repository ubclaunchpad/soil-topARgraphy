package ca.ubc.eml.soiltopargraphy.editor.ui.terrain;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;

import ca.ubc.eml.soiltopargraphy.editor.db.AppRepository;

public class AddTerrainViewModel extends AndroidViewModel {

    AppRepository mRepository;

    public AddTerrainViewModel (Application application) {
        super(application);
        mRepository = new AppRepository(application);
    }

    public void addTerrain(Terrain terrain) {
        mRepository.insertTerrain(terrain);
    }

}
