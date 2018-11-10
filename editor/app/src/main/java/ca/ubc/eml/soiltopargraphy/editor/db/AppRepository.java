package ca.ubc.eml.soiltopargraphy.editor.db;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import ca.ubc.eml.soiltopargraphy.editor.ui.flag.Flag;
import ca.ubc.eml.soiltopargraphy.editor.ui.infopanel.InfoPanel;

public class AppRepository {

    private FlagDao mFlagDao;
    private InfoPanelDao mInfoPanelDao;

    // Making this public so FlagMapViewModel can access
    public AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);

        mFlagDao = db.flagDao();
        mInfoPanelDao = db.infoPanelDao();
    }

    public LiveData<List<Flag>> getAllFlags() {
        return mFlagDao.getAllFlags();
    }
    public LiveData<List<InfoPanel>> getAllInfoPanels() {
        return mInfoPanelDao.getAllInfoPanels();
    }

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
}
