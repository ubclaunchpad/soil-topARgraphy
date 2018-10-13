package ca.ubc.eml.soiltopargraphy.editor.ui.infopanel;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.ubc.eml.soiltopargraphy.editor.R;

/**
 * Fragment where user enters Flag name and description
 */

public class DescriptionPanelFragment extends Fragment {

    private DescriptionPanelViewModel mViewModel;

    public static DescriptionPanelFragment newInstance() {
        return new DescriptionPanelFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.description_panel_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DescriptionPanelViewModel.class);
        // TODO: Use the ViewModel
    }

}
