package ca.ubc.eml.soiltopargraphy.editor.ui.flag;

import android.arch.lifecycle.ViewModel;

public class FlagMapViewModel extends ViewModel {
    private boolean flagItemClicked;

    public void setFlagItemClicked(boolean isClicked) {
        flagItemClicked = isClicked;
    }

    public boolean getFlagItemClicked() {
        return flagItemClicked;
    }
}
