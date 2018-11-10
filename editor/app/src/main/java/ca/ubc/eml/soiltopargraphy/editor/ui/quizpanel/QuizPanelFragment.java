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
    private String color;
    private String density;
    private String fruitfulness;
    private String humidity;


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
                    case R.id.SoilTypeAnswerTv:
                        type = value;
                        break;
                    case R.id.SoilColorAnswerTv:
                        color = value;
                        break;
                    case R.id.SoilDensityAnswerTv:
                        density = value;
                        break;
                    case R.id.SoilFruitfulnessAnswerTv:
                        fruitfulness = value;
                        break;
                    case R.id.SoilHumidityAnswerTv:
                        humidity = value;
                        break;
                }
                nextQuestion(v);
            }
        };
    }

    public void nextQuestion(View view) {
        QuizPanel newSoilType = new QuizPanel(type, color, density, fruitfulness, humidity);
    }

}
