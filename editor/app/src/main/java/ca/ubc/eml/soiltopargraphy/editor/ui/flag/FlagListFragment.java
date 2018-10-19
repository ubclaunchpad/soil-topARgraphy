package ca.ubc.eml.soiltopargraphy.editor.ui.flag;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ca.ubc.eml.soiltopargraphy.editor.R;

/**
 * Fragment where the list of flags (and their image and descriptions) are displayed
 */

public class FlagListFragment extends Fragment {

    private FlagListViewModel mViewModel;

    public static FlagListFragment newInstance() {
        return new FlagListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.flag_list_fragment, container, false);
        RecyclerView recyclerView;
        recyclerView = mView.findViewById(R.id.recyclerView);

        // Generates the list of flags that will be displayed in the list view
        // TODO: make a query to the database
        List<Flag> flagList = new ArrayList<>();
        FlagAdapter flagAdapter = new FlagAdapter(flagList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(flagAdapter);

        return inflater.inflate(R.layout.flag_list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FlagListViewModel.class);
        // TODO: Use the ViewModel
    }

}

class FlagAdapter extends RecyclerView.Adapter<FlagAdapter.mViewHolder> {
    private List<Flag> flagList;

    public class mViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView description;
        public ImageView image;

        public mViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.nameTextView);
            description = view.findViewById(R.id.descriptionTextView);
            image = view.findViewById(R.id.mImageView);
        }
    }

    public FlagAdapter (List<Flag> flagList) {
        this.flagList = flagList;
    }

    // Tells the recyclerview to use the flag_list_fragment_view layout as the layout for each row in the recyclerview
    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.flag_list_fragment_row, parent, false);

        return new mViewHolder(itemView);
    }

    // "Binds" each datapoint from the database to a viewholder in the recyclerview
    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
        Flag flag = flagList.get(position);

        holder.name.setText(flag.getPanel().getName());

        String description = flag.getPanel().getDescription();
        if(description.length() > 50) {
            holder.description.setText(flag.getPanel().getName().substring(0, 47) + "...");
        }
        else {
            holder.description.setText(flag.getPanel().getName());
        }

        holder.image.setImageURI(flag.getPanel().getImage());
    }

    // Returns the number of items in the recyclerview
    @Override
    public int getItemCount() {
        return flagList.size();
    }

}

