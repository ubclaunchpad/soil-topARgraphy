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

    public Terrain(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setTerrainId(int terrainId) {
        this.terrainId = terrainId;
    }

    public int getTerrainId() { return terrainId; }
}
