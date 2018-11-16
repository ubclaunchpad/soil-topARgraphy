package ca.ubc.eml.soiltopargraphy.editor.ui.terrain;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "terrain_table")
public class Terrain {
    @PrimaryKey(autoGenerate = true)
    private int terrainId;
    private double latitude;
    private double longitude;
    //TODO: Add other terrain variables

    Terrain(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getTerrainId() { return terrainId; }
}
