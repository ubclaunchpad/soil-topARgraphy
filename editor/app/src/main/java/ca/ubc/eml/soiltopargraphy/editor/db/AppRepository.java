package ca.ubc.eml.soiltopargraphy.editor.db;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import ca.ubc.eml.soiltopargraphy.editor.ui.flag.Flag;
import ca.ubc.eml.soiltopargraphy.editor.ui.terrain.Terrain;

public class AppRepository {

    private FlagDao mFlagDao;
    private TerrainDao mTerrainDao;

    // Making this public so MainViewModel can access
    public AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);

        mFlagDao = db.flagDao();
        mTerrainDao = db.terrainDao();
    }

    public LiveData<List<Flag>> getAllFlags() {
        return mFlagDao.getAllFlags();
    }
    public LiveData<List<Terrain>> getAllTerrains() {
        return mTerrainDao.getAllTerrains();
    }

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
