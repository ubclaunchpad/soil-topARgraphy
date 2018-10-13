package ca.ubc.eml.soiltopargraphy.editor.ui.quizpanel;

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
 * Quiz Panel where user enters 1 question and 4 answers
 */

public class QuizPanelFragment extends Fragment {

    private QuizPanelViewModel mViewModel;

    public static QuizPanelFragment newInstance() {
        return new QuizPanelFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.quiz_panel_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(QuizPanelViewModel.class);
        // TODO: Use the ViewModel
    }

}
