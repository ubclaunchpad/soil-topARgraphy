package ca.ubc.eml.soiltopargraphy.editor.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import ca.ubc.eml.soiltopargraphy.editor.ui.infopanel.InfoPanel;

@Database(entities = {InfoPanel.class}, version = 1)
public abstract class InfoPanelRoomDatabase extends RoomDatabase {

    public abstract InfoPanelDao infoPanelDao();

    private static volatile InfoPanelRoomDatabase INSTANCE;

    static InfoPanelRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (InfoPanelRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            InfoPanelRoomDatabase.class, "info_panel_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
