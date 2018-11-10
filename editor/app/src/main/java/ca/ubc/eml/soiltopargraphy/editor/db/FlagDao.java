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
}
