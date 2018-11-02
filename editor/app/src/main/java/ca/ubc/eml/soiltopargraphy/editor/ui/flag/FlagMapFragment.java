package ca.ubc.eml.soiltopargraphy.editor.ui.flag;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import ca.ubc.eml.soiltopargraphy.editor.R;

public class FlagMapFragment extends Fragment {

    private FlagMapViewModel mViewModel;

    public static FlagMapFragment newInstance() {
        return new FlagMapFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = ViewModelProviders.of(this).get(FlagMapViewModel.class);

        //Ensures the toolbar initially displays the options that it should
        //(ie. just the listview option) when the flag item is not clicked
        mViewModel.setFlagItemClicked(false);

        View view = inflater.inflate(R.layout.flag_map_fragment, container, false);
        Toolbar flagToolBar = view.findViewById(R.id.flagToolBar);

        //Sets the action bar for the activity as the custom flag toolbar
        ((AppCompatActivity)getActivity()).setSupportActionBar(flagToolBar);

        //Ensures that the menu is actually displayed in the toolbar
        setHasOptionsMenu(true);

        //Assigns an onClickListener to flag item that will update the toolbar when flag is clicked
        //TODO: Button flagItem should be replaced with actual flag item before merging
        Button flagItem = view.findViewById(R.id.flagItem);
        flagItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //When flag is clicked, updates the view model variable to reflect this and refreshes the toolbar view
                mViewModel.setFlagItemClicked(true);
                getActivity().invalidateOptionsMenu();
            }
        });

        //After clicking on the flag, if the user clicks anywhere else on the screen, updates
        //the toolbar to go back to showing only the listview item
        FrameLayout frameLayout = view.findViewById(R.id.frameLayout);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.setFlagItemClicked(false);
                getActivity().invalidateOptionsMenu();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FlagMapViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_flag_map, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        //Using a switch here so that eventually, when we add more options in the toolbar, this function can handle multiple possible selections
        switch (item.getItemId()) {
            //When the "List View" button is selected, swaps this fragment out for the flag list fragment
            case R.id.action_to_listview:
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.container, new FlagListFragment());
                transaction.commit();
                break;
            case R.id.action_edit:
                //TODO: insert code to edit flag here
                break;
            case R.id.action_delete:
                //TODO: insert code to delete flag here
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public void onPrepareOptionsMenu (Menu menu) {
        //When flag is clicked, shows only edit and delete options
        if (mViewModel.getFlagItemClicked()) {
            menu.findItem(R.id.action_to_listview).setVisible(false);
            menu.findItem(R.id.action_edit).setVisible(true);
            menu.findItem(R.id.action_delete).setVisible(true);
        }
        //When flag is not clicked, shows only listview options
        else {
            menu.findItem(R.id.action_to_listview).setVisible(true);
            menu.findItem(R.id.action_edit).setVisible(false);
            menu.findItem(R.id.action_delete).setVisible(false);
        }
    }
}
