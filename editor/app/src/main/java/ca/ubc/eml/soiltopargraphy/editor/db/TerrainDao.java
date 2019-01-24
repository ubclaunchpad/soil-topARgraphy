package ca.ubc.eml.soiltopargraphy.editor.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Query;

import java.util.List;

import ca.ubc.eml.soiltopargraphy.editor.ui.infopanel.InfoPanel;
import ca.ubc.eml.soiltopargraphy.editor.ui.terrain.Terrain;

@Dao
public interface TerrainDao {

    @Insert
    void insert(Terrain terrain);

    @Query("DELETE FROM terrain_table")
    void deleteAll();

    @Query("SELECT * FROM terrain_table")
    LiveData<List<Terrain>> getAllTerrains();

    @Query("DELETE FROM terrain_table WHERE terrainId = :terrainId")
    void deleteTerrain(int terrainId);
}
