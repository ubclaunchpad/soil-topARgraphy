package ca.ubc.eml.soiltopargraphy.editor.ui.quizpanel;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.support.v4.app.FragmentTransaction;

import ca.ubc.eml.soiltopargraphy.editor.R;
import ca.ubc.eml.soiltopargraphy.editor.ui.flag.FlagMapFragment;

/**
 */

public class QuestionnairePanelFragment extends Fragment {

    private QuestionnairePanelViewModel mViewModel;

    private String soilType;
    private String color;
    private String density;
    private String fruitfulness;
    private String humidity;


    public static QuestionnairePanelFragment newInstance() {
        return new QuestionnairePanelFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.questionnaire_panel_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(QuestionnairePanelViewModel.class);
        new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditText editText = (EditText) v;
                String value = editText.getText().toString();
                switch (editText.getId()) {
                    case R.id.SoilTypeAnswer:
                        soilType = value;
                        break;
                    case R.id.SoilColorAnswer:
                        color = value;
                        break;
                    case R.id.SoilDensityAnswer:
                        density = value;
                        break;
                    case R.id.SoilFruitfulnessAnswer:
                        fruitfulness = value;
                        break;
                    case R.id.SoilHumidityAnswer:
                        humidity = value;
                        break;
                }
                nextQuestion(v);
            }
        };
    }

    public void nextQuestion(View view) {
        QuestionnairePanel newSoilType = new QuestionnairePanel(soilType, color, density, fruitfulness, humidity);
    }


    public void clickToMain(View v) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        if (fragmentManager != null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.container, FlagMapFragment.newInstance());
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}


