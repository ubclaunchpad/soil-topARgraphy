package ca.ubc.eml.soiltopargraphy.editor.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import ca.ubc.eml.soiltopargraphy.editor.ui.flag.Flag;

@Dao
public interface FlagDao {

    @Insert
    void insert(Flag flag);

    @Query("DELETE FROM flag_table")
    void deleteAll();

    @Query("SELECT * from flag_table")
    LiveData<List<Flag>> getAllFlags();

    @Query("DELETE FROM flag_table WHERE id = :flagId")
    void deleteFlag(int flagId);

    // Given a certain terrainId, gets all the flags in the table associated with that terrainId
    // and stores in a LiveData list. This should be used when getting flags to be displayed in UI
    // where LiveData is useful
    @Query("SELECT * from flag_table WHERE terrainId = :terrainId")
    LiveData<List<Flag>> getFlagsInTerrainLive(int terrainId);

}
