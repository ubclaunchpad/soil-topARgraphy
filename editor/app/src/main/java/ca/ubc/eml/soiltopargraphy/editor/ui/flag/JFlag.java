package ca.ubc.eml.soiltopargraphy.editor.ui.flag;

import ca.ubc.eml.soiltopargraphy.editor.ui.infopanel.JInfoPanel;

import java.util.ArrayList;



public class JFlag {

    private String location;
    private float latitude;
    private float longitude;
    private ArrayList<JInfoPanel> infoPanels;
    private int id;


    public JFlag(String location, float latitude, float longitude, ArrayList<JInfoPanel> infoPanels, int id) {
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.infoPanels = infoPanels;
        this.id = id;
    }

    public void addInfoPanel(JInfoPanel infoPanel) {
        infoPanels.add(infoPanel);
    }

    public void removeInfoPanel(JInfoPanel infoPanel) {
        infoPanels.remove(infoPanel);
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

    public ArrayList<JInfoPanel> getInfoPanels() {
        return infoPanels;
    }

    public void setInfoPanels(ArrayList<JInfoPanel> infoPanels) {
        this.infoPanels = infoPanels;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

