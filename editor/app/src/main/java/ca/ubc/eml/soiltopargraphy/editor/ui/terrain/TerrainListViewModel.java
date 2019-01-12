package ca.ubc.eml.soiltopargraphy.editor.ui.terrain;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import java.util.List;

import ca.ubc.eml.soiltopargraphy.editor.db.AppRepository;
import ca.ubc.eml.soiltopargraphy.editor.ui.flag.Flag;

public class TerrainListViewModel extends AndroidViewModel {
    private LiveData<List<Terrain>> terrains;
    private AppRepository mRepository;

    public TerrainListViewModel (Application application) {
        super(application);
        mRepository = new AppRepository(application);
    }

    // Querys the database for all the terrains that are associated with terrain id
    // and returns a list of these flags
    public LiveData<List<Terrain>> getTerrains(){
        if(terrains == null) {
            terrains = mRepository.getAllTerrains();
        }
        return terrains;
    }


}
