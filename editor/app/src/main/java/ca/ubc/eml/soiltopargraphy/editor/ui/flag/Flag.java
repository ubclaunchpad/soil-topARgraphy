package ca.ubc.eml.soiltopargraphy.editor.ui.flag;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import ca.ubc.eml.soiltopargraphy.editor.ui.infopanel.InfoPanel;

@Entity(tableName = "flag_table")
public class Flag {

    private String location;
    private float latitude;
    private float longitude;
    private InfoPanel infoPanel;
    @PrimaryKey
    private int id;


    public Flag(String location, float latitude, float longitude, InfoPanel infoPanel, int id) {
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.infoPanel = infoPanel;
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public InfoPanel getInfoPanel() {
        return infoPanel;
    }

    public void setInfoPanel(InfoPanel infoPanel) {
        this.infoPanel = infoPanel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


