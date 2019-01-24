package ca.ubc.eml.soiltopargraphy.editor.ui.terrain;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Editable;

import java.io.ByteArrayOutputStream;
import java.io.File;

import ca.ubc.eml.soiltopargraphy.editor.MainActivity;
import ca.ubc.eml.soiltopargraphy.editor.R;
import ca.ubc.eml.soiltopargraphy.editor.db.AppRepository;

public class AddTerrainViewModel extends AndroidViewModel {

    AppRepository mRepository;

    public AddTerrainViewModel (Application application) {
        super(application);
        mRepository = new AppRepository(application);
    }

    // Creates a new instance of terrain and calls addTerrain to add the terrain to the terrain
    // table in the room database
    public void createNewTerrain(Editable latitudeText, Editable longitudeText, Editable heightText, Editable widthText) {

        double latitude = Double.parseDouble(latitudeText.toString());
        double longitude = Double.parseDouble(longitudeText.toString());
        double height = Double.parseDouble(heightText.toString());
        double width = Double.parseDouble(widthText.toString());

        Context context = this.getApplication();

        Bitmap heightmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.hardcoded_heightmap);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        heightmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        byte[] byteHeightmap = out.toByteArray();

        Terrain terrain = new Terrain(latitude, longitude, height, width, byteHeightmap);

        addTerrain(terrain);
    }

    // Adds a given terrain to the terrain table in the room database
    public void addTerrain(Terrain terrain) {
        mRepository.insertTerrain(terrain);
    }

}
