package ca.ubc.eml.soiltopargraphy.editor.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import ca.ubc.eml.soiltopargraphy.editor.ui.infopanel.InfoPanel;

@Dao
public interface InfoPanelDao {

    @Insert
    void insert(InfoPanel infoPanel);

    @Query("DELETE FROM info_panel_table")
    void deleteAll();

    @Query("SELECT * from info_panel_table")

    LiveData<List<InfoPanel>> getAllInfoPanels();

    @Query("DELETE FROM info_panel_table WHERE name = :panelName")
    void deletePanel(String panelName);
}
