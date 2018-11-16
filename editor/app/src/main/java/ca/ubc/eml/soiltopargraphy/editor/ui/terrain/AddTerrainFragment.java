package ca.ubc.eml.soiltopargraphy.editor.ui.terrain;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ca.ubc.eml.soiltopargraphy.editor.R;

public class AddTerrainFragment extends Fragment {

    private AddTerrainViewModel mViewModel;

    public static AddTerrainFragment newInstance() {
        return new AddTerrainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_terrain_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AddTerrainViewModel.class);
        // TODO: Use the ViewModel
    }

}
