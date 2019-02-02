package ca.ubc.eml.soiltopargraphy.editor.ui.terrain;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.drawable.BitmapDrawable;

import com.bumptech.glide.Glide;

import ca.ubc.eml.soiltopargraphy.editor.MainActivity;
import ca.ubc.eml.soiltopargraphy.editor.R;
import ca.ubc.eml.soiltopargraphy.editor.ui.main.MainFragment;

/**
 * Fragment where the list of terrains are displayed
 */

public class TerrainListFragment extends Fragment {

    private TerrainListViewModel terrainViewModel;
    TerrainAdapter terrainAdapter = new TerrainAdapter();

    public static TerrainListFragment newInstance() {
        return new TerrainListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflates the terrain list fragment UI and finds the recycler view to populate
        View terrainListView = inflater.inflate(R.layout.terrain_list_fragment, container, false);
        RecyclerView mRecyclerView = terrainListView.findViewById(R.id.recyclerView);

        // Sets the layout manager for the recycler view to be linear (standard vertical scrolling)
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Sets the context of the adapter to be the context of the fragment (context is
        // needed to call functions relating to switching fragments when an item is clicked)
        terrainAdapter.setContext(getActivity());

        // Sets the adapter to be the terrain adapter below
        mRecyclerView.setAdapter(terrainAdapter);

        // When the "add" button is clicked, switches to fragment which allows user to create a new terrain
        Button addButton = terrainListView.findViewById(R.id.addButton);
        addButton.setOnClickListener(view -> {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                System.out.println("Context for button is: " + getActivity());
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment newFragment = new AddTerrainFragment();
                transaction.replace(R.id.container, newFragment);
                transaction.commit();
        });

        return terrainListView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        terrainViewModel = ViewModelProviders.of(this).get(TerrainListViewModel.class);

        // Submits an updated list of terrains to the adapter when a change is observed
        // in the terrains table in the room database (via LiveData)
        terrainViewModel.getTerrains().observe(this, list -> {
            terrainAdapter.submitList(list);
        });
    }

}

class TerrainAdapter extends ListAdapter<Terrain, TerrainAdapter.TerrainViewHolder> {

    private Context context;

    public TerrainAdapter() {
        super(TerrainAdapter.DIFF_CALLBACK);
    }

    // Sets the context of the TerrainAdapter, which is needed for item onClick functionality
    public void setContext(Context context) {
        this.context = context;
    }

    // Creates viewholders with the layout specified in the row xml file
    @Override
    public TerrainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.terrain_list_fragment_row, parent, false);

        return new TerrainViewHolder(itemView);
    }

    // Binds terrains to viewholders as they should appear on the screen
    @Override
    public void onBindViewHolder(TerrainViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }

    //Calculates updates for the list of terrains to be displayed
    public static DiffUtil.ItemCallback<Terrain> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Terrain>() {
                @Override
                public boolean areItemsTheSame(@NonNull Terrain oldTerrain, @NonNull Terrain newTerrain) {
                    return oldTerrain.getTerrainId() == newTerrain.getTerrainId();
                }
                @Override
                public boolean areContentsTheSame(@NonNull Terrain oldTerrain, @NonNull Terrain newTerrain) {
                    return oldTerrain.equals(newTerrain);
                }
            };

    // Dictates how the viewholders should be populated with data
    class TerrainViewHolder extends RecyclerView.ViewHolder {
        private TextView terrainLatitude;
        private TextView terrainLongitude;
        private ImageView heightmapThumbnail;

        // Finds all of the view to be populated and connects them to variables
        public TerrainViewHolder(View view) {
            super(view);
            terrainLatitude = view.findViewById(R.id.latitudeText);
            terrainLongitude = view.findViewById(R.id.longitudeText);
            heightmapThumbnail = view.findViewById(R.id.heightmapView);
        }

        // Populates the views with the appropriate data from a given terrain
        public void bindTo(Terrain terrain) {
            terrainLatitude.setText("Latitude: " + terrain.getLatitude());
            terrainLongitude.setText("Longitude: " + terrain.getLongitude());

            Glide.with(context).load(R.drawable.hardcoded_heightmap).into(heightmapThumbnail);

            // Sets onClickListener for each individual item
            itemView.setOnClickListener(view -> {
                // When an item is clicked, changes out this fragment for the main fragment
                // and tells the main fragment which terrain was clicked so it can display the
                // right one
                FragmentManager manager = ((MainActivity) context).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment newFragment = new MainFragment();
                ((MainFragment) newFragment).setTerrain(terrain);
                transaction.replace(R.id.container, newFragment);
                transaction.commit();
            });

        }
    }

}

