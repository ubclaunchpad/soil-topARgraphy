package ca.ubc.eml.soiltopargraphy.editor.ui.flag;

import android.arch.lifecycle.ViewModelProviders;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.recyclerview.extensions.ListAdapter;

import java.io.File;

import ca.ubc.eml.soiltopargraphy.editor.R;

/**
 * Fragment where the list of flags (and their image and descriptions) are displayed
 */

public class FlagListFragment extends Fragment {

    public static FlagListFragment newInstance() {
        return new FlagListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View flagListView = inflater.inflate(R.layout.flag_list_fragment, container, false);
        RecyclerView recyclerView = flagListView.findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        FlagAdapter flagAdapter = new FlagAdapter();
        recyclerView.setAdapter(flagAdapter);

        return flagListView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FlagListViewModel flagViewModel = ViewModelProviders.of(this).get(FlagListViewModel.class);
        FlagAdapter flagAdapter = new FlagAdapter();

        // Submits a new updated list of flags to the flag adapter when a change is observed
        // in the flag table in the room database via LiveData
        flagViewModel.getFlagList().observe(this, list -> flagAdapter.submitList(list));
    }

}


class FlagViewHolder extends RecyclerView.ViewHolder {
    private TextView name;
    private TextView description;
    private ImageView image;
    private Button editButton;

    public FlagViewHolder(View view) {
        super(view);
        name = view.findViewById(R.id.nameTextView);
        description = view.findViewById(R.id.descriptionTextView);
        image = view.findViewById(R.id.mImageView);
        editButton = view.findViewById(R.id.editButton);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void bindTo(Flag flag) {
        this.name.setText(flag.getInfoPanel().getName());

        String description = flag.getInfoPanel().getDescription();
        if(description.length() > 50) {
            this.description.setText(flag.getInfoPanel().getName().substring(0, 47) + "...");
        }
        else {
            this.description.setText(flag.getInfoPanel().getName());
        }

        this.image.setImageURI(Uri.parse(flag.getInfoPanel().getImage()));
    }
}


class FlagAdapter extends ListAdapter<Flag, FlagViewHolder> {

    public FlagAdapter() {
        super(FlagAdapter.DIFF_CALLBACK);
    }

    @Override
    public FlagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.flag_list_fragment_row, parent, false);

        return new FlagViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FlagViewHolder holder, int position) {
        holder.bindTo(this.getItem(position));
    }

    //Calculates updates for the list of flags to be displayed
    public static final DiffUtil.ItemCallback<Flag> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Flag>() {
                @Override
                public boolean areItemsTheSame(@NonNull Flag oldFlag, @NonNull Flag newFlag) {
                    return oldFlag.getId() == newFlag.getId();
                }
                @Override
                public boolean areContentsTheSame(@NonNull Flag oldFlag, @NonNull Flag newFlag) {
                    return oldFlag.equals(newFlag);
                }
            };

}