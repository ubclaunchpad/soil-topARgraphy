package ca.ubc.eml.soiltopargraphy.editor.db;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import java.util.List;
import ca.ubc.eml.soiltopargraphy.editor.ui.infopanel.InfoPanel;

public class InfoPanelRepository {

    private InfoPanelDao mInfoPanelDao;
    private LiveData<List<InfoPanel>> mAllInfoPanel;

    InfoPanelRepository(Application application) {
        InfoPanelRoomDatabase db = InfoPanelRoomDatabase.getDatabase(application);
        mInfoPanelDao = db.infoPanelDao();
        mAllInfoPanel = mInfoPanelDao.getAllInfoPanels();
    }

    LiveData<List<InfoPanel>> getAllInfoPanel() {
        return mAllInfoPanel;
    }

    // Must call this on non-UI thread or app will crash
    public void insert (InfoPanel infoPanel) {
        new insertAsyncTask(mInfoPanelDao).execute(infoPanel);
    }

    private static class insertAsyncTask extends AsyncTask<InfoPanel, Void, Void> {

        private InfoPanelDao mAsyncTaskDao;

        insertAsyncTask(InfoPanelDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final InfoPanel... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
