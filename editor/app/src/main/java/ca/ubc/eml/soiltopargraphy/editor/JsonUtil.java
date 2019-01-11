package ca.ubc.eml.soiltopargraphy.editor;

import org.json.JSONException;
import org.json.JSONObject;

import ca.ubc.eml.soiltopargraphy.editor.ui.infopanel.InfoPanel;
import ca.ubc.eml.soiltopargraphy.editor.ui.quizpanel.QuestionnairePanel;

public class JsonUtil {

    // Convert InfoPanel data to JSon data
    public static JSONObject infoToJson (InfoPanel infoPanel){
        JSONObject jsInfoPanel = new JSONObject();
        try {
            jsInfoPanel.put("Name", infoPanel.getName());
            jsInfoPanel.put("Description", infoPanel.getDescription());
            jsInfoPanel.put("Image", infoPanel.getImage());
            jsInfoPanel.put("Questionnaire", infoPanel.getQuestionnaire());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsInfoPanel;
    }

    //Convert the QuestionnairePanel data to JSon code
    public static JSONObject quizToJson (QuestionnairePanel questionnaire){
        JSONObject jsQuizPanel = new JSONObject();
        try {
            jsQuizPanel.put("Type", questionnaire.getSoilType());
            jsQuizPanel.put("Color", questionnaire.getColor());
            jsQuizPanel.put("Density", questionnaire.getDensity());
            jsQuizPanel.put("Fruitfulness", questionnaire.getFruitfulness());
            jsQuizPanel.put("Humidity", questionnaire.getHumidity());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsQuizPanel;
    }
}
