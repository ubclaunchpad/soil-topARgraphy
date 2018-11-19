package ca.ubc.eml.soiltopargraphy.editor.ui.terrain;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.text.Editable;

import ca.ubc.eml.soiltopargraphy.editor.db.AppRepository;

public class AddTerrainViewModel extends AndroidViewModel {

    AppRepository mRepository;

    public AddTerrainViewModel (Application application) {
        super(application);
        mRepository = new AppRepository(application);
    }

    // Creates a new instance of terrain and calls addTerrain to add the terrain to the terrain
    // table in the room database
    public void createNewTerrain(Editable latitudeText, Editable longitudeText) {

        double latitude = Double.parseDouble(latitudeText.toString());
        double longitude = Double.parseDouble(longitudeText.toString());
        Terrain terrain = new Terrain(latitude, longitude);

        addTerrain(terrain);
    }

    // Adds a given terrain to the terrain table in the room database
    public void addTerrain(Terrain terrain) {
        mRepository.insertTerrain(terrain);
    }

}
