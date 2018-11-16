package ca.ubc.eml.soiltopargraphy.editor.ui.terrain;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "terrain_table")
public class Terrain {
    @PrimaryKey
    private int terrainId;
    //TODO: Add other terrain variables

    public int getTerrainId() { return terrainId; }
}
