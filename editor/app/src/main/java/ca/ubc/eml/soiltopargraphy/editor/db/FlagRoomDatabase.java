package ca.ubc.eml.soiltopargraphy.editor.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import ca.ubc.eml.soiltopargraphy.editor.ui.flag.Flag;

@Database(entities = {Flag.class}, version = 1)
public abstract class FlagRoomDatabase extends RoomDatabase {
    public abstract FlagDao flagDao();

    private static volatile FlagRoomDatabase INSTANCE;

    static FlagRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FlagRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FlagRoomDatabase.class, "flag_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
