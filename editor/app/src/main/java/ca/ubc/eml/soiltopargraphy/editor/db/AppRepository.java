package ca.ubc.eml.soiltopargraphy.editor.db;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import ca.ubc.eml.soiltopargraphy.editor.ui.flag.Flag;
import ca.ubc.eml.soiltopargraphy.editor.ui.infopanel.InfoPanel;
import ca.ubc.eml.soiltopargraphy.editor.ui.terrain.Terrain;

public class AppRepository {

    private FlagDao mFlagDao;
    private InfoPanelDao mInfoPanelDao;
    private TerrainDao mTerrainDao;

    // Making this public so FlagMapViewModel can access
    public AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);

        mFlagDao = db.flagDao();
        mInfoPanelDao = db.infoPanelDao();
        mTerrainDao = db.terrainDao();
    }

    public LiveData<List<Flag>> getAllFlags() {
        return mFlagDao.getAllFlags();
    }
    public LiveData<List<InfoPanel>> getAllInfoPanels() {
        return mInfoPanelDao.getAllInfoPanels();
    }
    public LiveData<List<Terrain>> getAllTerrains() { return mTerrainDao.getAllTerrains(); }

    /* FLAG FUNCTIONS *******************************************************************************************/

    // Must call this on non-UI thread or app will crash
    public void insertFlag(Flag flag) {
        new insertFlagAsyncTask(mFlagDao).execute(flag);
    }

    private static class insertFlagAsyncTask extends AsyncTask<Flag, Void, Void> {

        private FlagDao mAsyncTaskDao;

        insertFlagAsyncTask(FlagDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Flag... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    //Deletes a singular flag from the flag room database
    public void deleteFlag(Flag flag) {

        new deleteFlagAsyncTask(mFlagDao).execute(flag);

        //Deletes the corresponding info panel from the info panel database at the same time
        deletePanel(flag.getInfoPanel());
    }

    private static class deleteFlagAsyncTask extends AsyncTask<Flag, Void, Void> {

        private FlagDao mAsyncTaskDao;

        deleteFlagAsyncTask(FlagDao dao) { mAsyncTaskDao = dao; }

        @Override
        protected Void doInBackground(final Flag... params) {
            mAsyncTaskDao.deleteFlag(params[0].getId());
            return null;
        }
    }

    public LiveData<List<Flag>> getFlagsInTerrain(Terrain terrain) {
        return mFlagDao.getFlagsInTerrainLive(terrain.getTerrainId());
    }

    /* INFOPANEL FUNCTIONS **************************************************************************************/

    public void insertInfoPanel(InfoPanel infoPanel) {
        new insertInfoPanelAsyncTask(mInfoPanelDao).execute(infoPanel);
    }

    private static class insertInfoPanelAsyncTask extends AsyncTask<InfoPanel, Void, Void> {

        private InfoPanelDao mAsyncTaskDao;

        insertInfoPanelAsyncTask(InfoPanelDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final InfoPanel... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    //Deletes a singular info panel from the database given its name
    public void deletePanel(InfoPanel panel) {
        new deletePanelAsyncTask(mInfoPanelDao).execute(panel);
    }

    private static class deletePanelAsyncTask extends AsyncTask<InfoPanel, Void, Void> {

        private InfoPanelDao mAsyncTaskDao;

        deletePanelAsyncTask(InfoPanelDao dao) { mAsyncTaskDao = dao; }

        @Override
        protected Void doInBackground(final InfoPanel... params) {
            mAsyncTaskDao.deletePanel(params[0].getName());
            return null;
        }
    }

    /* TERRAIN FUNCTIONS ****************************************************************************************/

    public void insertTerrain(Terrain terrain) {
        new insertTerrainAsyncTask(mTerrainDao).execute(terrain);
    }

    private static class insertTerrainAsyncTask extends AsyncTask<Terrain, Void, Void> {

        private TerrainDao mAsyncTaskDao;

        insertTerrainAsyncTask(TerrainDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Terrain... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    //Deletes a singular terrain from the database given its unique id
    public void deleteTerrain(Terrain terrain) {
        new deleteTerrainAsyncTask(mTerrainDao).execute(terrain);

        // Gets all the flags that are associated with the terrain that was just deleted
        // and deletes all these flags from the flag table
        List<Flag> flagsInTerrain = mFlagDao.getFlagsInTerrainNotLive(terrain.getTerrainId());
        for(Flag flag : flagsInTerrain) {
            deleteFlag(flag);
        }
    }

    private static class deleteTerrainAsyncTask extends AsyncTask<Terrain, Void, Void> {

        private TerrainDao mAsyncTaskDao;

        deleteTerrainAsyncTask(TerrainDao dao) { mAsyncTaskDao = dao; }

        @Override
        protected Void doInBackground(final Terrain... params) {
            mAsyncTaskDao.deleteTerrain(params[0].getTerrainId());
            return null;
        }
    }
}
