package ca.ubc.eml.soiltopargraphy.editor.db;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;

import ca.ubc.eml.soiltopargraphy.editor.ui.infopanel.InfoPanel;

public class Converters {
    @TypeConverter
    public static InfoPanel infoPanelFromJson(String value) {
        return new Gson().fromJson(value, InfoPanel.class);
    }

    @TypeConverter
    public static String jsonFromInfoPanel(InfoPanel panel) {
        return new Gson().toJson(panel);
    }
}
