package ca.ubc.eml.soiltopargraphy.editor.db;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import ca.ubc.eml.soiltopargraphy.editor.ui.flag.Flag;

public class FlagRepository {

    private FlagDao mFlagDao;
    private LiveData<List<Flag>> mAllFlags;

    // Making this public so FlagMapViewModel can access
    public FlagRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mFlagDao = db.flagDao();
        mAllFlags = mFlagDao.getAllFlags();
    }

    public LiveData<List<Flag>> getAllFlags() {
        return mAllFlags;
    }

    // Must call this on non-UI thread or app will crash
    public void insert (Flag flag) {
        new insertAsyncTask(mFlagDao).execute(flag);
    }

    private static class insertAsyncTask extends AsyncTask<Flag, Void, Void> {

        private FlagDao mAsyncTaskDao;

        insertAsyncTask(FlagDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Flag... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
