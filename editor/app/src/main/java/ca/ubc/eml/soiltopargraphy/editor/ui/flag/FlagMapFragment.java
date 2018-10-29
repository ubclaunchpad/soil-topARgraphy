package ca.ubc.eml.soiltopargraphy.editor.ui.flag;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
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
        return inflater.inflate(R.layout.flag_map_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FlagMapViewModel.class);
        // TODO: Use the ViewModel
    }

}
