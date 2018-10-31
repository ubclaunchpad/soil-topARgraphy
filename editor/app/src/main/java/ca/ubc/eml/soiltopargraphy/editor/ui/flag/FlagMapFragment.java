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

import ca.ubc.eml.soiltopargraphy.editor.R;

public class FlagMapFragment extends Fragment {

    private FlagMapViewModel mViewModel;

    public static FlagMapFragment newInstance() {
        return new FlagMapFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.flag_map_fragment, container, false);
        Toolbar flagToolBar = view.findViewById(R.id.flagToolBar);

        //Sets the action bar for the activity as the custom flag toolbar
        ((AppCompatActivity)getActivity()).setSupportActionBar(flagToolBar);

        //Ensures that the menu is actually displayed in the toolbar
        setHasOptionsMenu(true);

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
        inflater.inflate(R.menu.menu_flag_not_selected, menu);
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
            default:
                break;
        }

        return true;
    }

}
