package ca.ubc.eml.soiltopargraphy.editor.ui.flag;

import android.arch.lifecycle.ViewModel;

import java.util.List;

public class FlagListViewModel extends ViewModel {
    private List<Flag> flagList;

    public List<Flag> getFlagList(){
        if(flagList == null) {
            //TODO: database query
        }
        return flagList;
    }
}
