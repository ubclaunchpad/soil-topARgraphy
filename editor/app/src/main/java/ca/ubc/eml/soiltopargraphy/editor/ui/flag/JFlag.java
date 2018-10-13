package ca.ubc.eml.soiltopargraphy.editor.ui.flag;

import ca.ubc.eml.soiltopargraphy.editor.ui.infopanel.JInfoPanel;

import java.util.ArrayList;



public class JFlag {

    private String location;
    private float lattitude;
    private float longditude;
    private ArrayList<JInfoPanel> infoPanels;
    private int id;


    public JFlag(String location, float lattitude, float longditude, ArrayList<JInfoPanel> infoPanels, int id) {
        this.location = location;
        this.lattitude = lattitude;
        this.longditude = longditude;
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

    public float getLattitude() {
        return lattitude;
    }

    public void setLattitude(float lattitude) {
        this.lattitude = lattitude;
    }

    public float getLongditude() {
        return longditude;
    }

    public void setLongditude(float longditude) {
        this.longditude = longditude;
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

