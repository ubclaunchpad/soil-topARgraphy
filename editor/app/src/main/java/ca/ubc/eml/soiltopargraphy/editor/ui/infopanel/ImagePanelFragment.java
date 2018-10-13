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
 * Fragment where user uploads image from library or take image
 */


public class ImagePanelFragment extends Fragment {

    private ImagePanelViewModel mViewModel;

    public static ImagePanelFragment newInstance() {
        return new ImagePanelFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.image_panel_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ImagePanelViewModel.class);
        // TODO: Use the ViewModel
    }

}
