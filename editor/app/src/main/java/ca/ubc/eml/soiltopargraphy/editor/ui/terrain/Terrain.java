package ca.ubc.eml.soiltopargraphy.editor.ui.terrain;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;

@Entity(tableName = "terrain_table")
public class Terrain {
    // Sets terrainId as the primary key and makes it autogenerated so we don't have to state the id
    // each time a new instance of a terrain is created
    @PrimaryKey(autoGenerate = true)
    private int terrainId;
    // Latitude and longitude refer to the bottom left corner of the terrain
    private double latitude;
    private double longitude;
    // Height and width refer to the real life height and width of the terrain map (ie.
    // how zoomed in is the map)
    private double height;
    private double width;
    private byte[] heightmap;

    public Terrain(double latitude, double longitude, double height, double width, byte[] heightmap) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.height = height;
        this.width = width;
        this.heightmap = heightmap;
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

    public void setHeight(double height) {
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getWidth() {
        return width;
    }

    public byte[] getHeightmap() {
        return heightmap;
    }

    public void setHeightmap(byte[] heightmap) {
        this.heightmap = heightmap;
    }

    public void setTerrainId(int terrainId) {
        this.terrainId = terrainId;
    }

    public int getTerrainId() { return terrainId; }
}
