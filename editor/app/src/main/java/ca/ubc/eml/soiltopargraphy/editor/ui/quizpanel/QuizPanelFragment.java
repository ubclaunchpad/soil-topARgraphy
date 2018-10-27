package ca.ubc.eml.soiltopargraphy.editor.ui.quizpanel;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import ca.ubc.eml.soiltopargraphy.editor.MainActivity;
import ca.ubc.eml.soiltopargraphy.editor.R;

/**
 */

public class QuizPanelFragment extends Fragment {

    private QuizPanelViewModel mViewModel;

    private String type;
    private String info1;
    private String info2;
    private String info3;
    private String info4;

    private QuizPanel quizPanel = new QuizPanel("","","","","");


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
        new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditText editText = (EditText) v;
                String value = editText.getText().toString();
                switch (editText.getId()) {
                    case R.id.qEditText:
                        type = value;
                        break;
                    case R.id.caEditText:
                        info1 = value;
                        break;
                    case R.id.wa1EditText:
                        info2 = value;
                        break;
                    case R.id.wa2EditText:
                        info3 = value;
                        break;
                    case R.id.wa3EditText:
                        info4 = value;
                        break;
                }
            }
        };
    }

    public void nextQuestion(View view) {
        QuizPanel newQuestion = new QuizPanel(type, info1, info2, info3, info4);
    }

}
