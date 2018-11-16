package ca.ubc.eml.soiltopargraphy.editor.ui.terrain;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ca.ubc.eml.soiltopargraphy.editor.R;

public class AddTerrainFragment extends Fragment {

    private AddTerrainViewModel mViewModel;

    public static AddTerrainFragment newInstance() {
        return new AddTerrainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View addTerrainView = inflater.inflate(R.layout.add_terrain_fragment, container, false);

        // When user presses the "x" cancel button, switches back to the terrain list fragment
        // without creating a new terrain
        Button cancelButton = addTerrainView.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment newFragment = new TerrainListFragment();
                transaction.replace(R.id.container, newFragment);
                transaction.commit();
            }
        });

        EditText latitudeText = addTerrainView.findViewById(R.id.latitudeText);
        EditText longitudeText = addTerrainView.findViewById(R.id.longitudeText);

        double latitude = Double.parseDouble(latitudeText.getText().toString());
        double longitude = Double.parseDouble(longitudeText.getText().toString());

        Button createButton = addTerrainView.findViewById(R.id.createButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Terrain terrain = new Terrain(latitude, longitude);
                mViewModel.addTerrain(terrain);
            }
        });

        return addTerrainView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AddTerrainViewModel.class);
        // TODO: Use the ViewModel
    }

}
