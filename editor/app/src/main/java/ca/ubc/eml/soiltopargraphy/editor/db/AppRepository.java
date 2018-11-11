package ca.ubc.eml.soiltopargraphy.editor.db;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import ca.ubc.eml.soiltopargraphy.editor.ui.flag.Flag;

public class AppRepository {

    private FlagDao mFlagDao;

    // Making this public so FlagMapViewModel can access
    public AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);

        mFlagDao = db.flagDao();
    }

    public LiveData<List<Flag>> getAllFlags() {
        return mFlagDao.getAllFlags();
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
}
