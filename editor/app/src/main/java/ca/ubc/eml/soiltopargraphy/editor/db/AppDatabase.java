package ca.ubc.eml.soiltopargraphy.editor.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import ca.ubc.eml.soiltopargraphy.editor.ui.flag.Flag;
import ca.ubc.eml.soiltopargraphy.editor.ui.infopanel.InfoPanel;

@Database(entities = {Flag.class, InfoPanel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract FlagDao flagDao();
    public abstract InfoPanelDao infoPanelDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "soil_topargraphy_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
