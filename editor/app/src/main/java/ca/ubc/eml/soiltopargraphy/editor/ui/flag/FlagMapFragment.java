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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import ca.ubc.eml.soiltopargraphy.editor.R;
import ca.ubc.eml.soiltopargraphy.editor.ui.infopanel.DescriptionPanelFragment;

public class FlagMapFragment extends Fragment {

    private FlagMapViewModel mViewModel;

    public static FlagMapFragment newInstance() {
        return new FlagMapFragment();
    }


    private Button flag;
    private ViewGroup rootLayout;
    private int _xDelta;
    private int _yDelta;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // =================================================================================================
        // DRAGGABLE FLAG
        super.onCreate(savedInstanceState);
        getActivity().setContentView(R.layout.flag_map_fragment);
        rootLayout = (ViewGroup) getActivity().findViewById(R.id.view_root);
        flag = (Button) rootLayout.findViewById(R.id.flagItem);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150, 150);
        flag.setLayoutParams(layoutParams);
        flag.setOnTouchListener(new ChoiceTouchListener());
        // ==================================================================================================


        mViewModel = ViewModelProviders.of(this).get(FlagMapViewModel.class);

        //Ensures the toolbar initially displays the options that it should
        //(ie. just the listview option) when the flag item is not clicked
        mViewModel.setFlagItemClicked(false);

        View view = inflater.inflate(R.layout.flag_map_fragment, container, false);
        Toolbar flagToolBar = view.findViewById(R.id.flagToolBar);

        //Sets the action bar for the activity as the custom flag toolbar
        ((AppCompatActivity) getActivity()).setSupportActionBar(flagToolBar);

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
        FrameLayout frameLayout = view.findViewById(R.id.view_root);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.setFlagItemClicked(false);
                getActivity().invalidateOptionsMenu();
            }
        });

        return view;
    }

    // ===========================================================================================================
    // DRAGGING BEHAVIOR
    private final class ChoiceTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent event) {

            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    _xDelta = X - lParams.leftMargin;
                    _yDelta = Y - lParams.topMargin;
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    layoutParams.leftMargin = X - _xDelta;
                    layoutParams.topMargin = Y - _yDelta;
                    layoutParams.rightMargin = -250;
                    layoutParams.bottomMargin = -250;
                    view.setLayoutParams(layoutParams);
                    break;
            }
            rootLayout.invalidate();
            return true;
        }
    }
    // ================================================================================================================


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

        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment newFragment = null;

        switch (item.getItemId()) {
            case R.id.action_to_listview:
                //When the "List View" button is selected, swaps this fragment out for the flag list fragment
                newFragment = new FlagListFragment();
                break;
            case R.id.action_edit:
                newFragment = new DescriptionPanelFragment();
                break;
            case R.id.action_delete:
                //Deletes flag from room database
                mViewModel.deleteFlag();
                break;
            default:
                break;
        }

        if (newFragment != null) {
            transaction.replace(R.id.container, newFragment);
            transaction.commit();
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
