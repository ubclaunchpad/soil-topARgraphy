package ca.ubc.eml.soiltopargraphy.editor;

import org.json.JSONException;
import org.json.JSONObject;

import ca.ubc.eml.soiltopargraphy.editor.ui.infopanel.InfoPanel;
import ca.ubc.eml.soiltopargraphy.editor.ui.quizpanel.QuizPanel;

public class JsonUtil {

    // Convert InfoPanel data to JSon data
    public static String InfoToJSon (InfoPanel infoPanel){
        JSONObject jsInfoPanel = new JSONObject();
        try {
            jsInfoPanel.put("Name", infoPanel.getName());
            jsInfoPanel.put("Description", infoPanel.getDescription());
            jsInfoPanel.put("Image", infoPanel.getImage());
            jsInfoPanel.put("Questionnaire", infoPanel.getQuiz());
            return jsInfoPanel.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    //Convert the QuizPanel data to JSon code
    public static String QuizToJSon (QuizPanel questionnaire){
        JSONObject jsInfoPanel = new JSONObject();
        try {
            jsInfoPanel.put("Type", questionnaire.getType());
            jsInfoPanel.put("Color", questionnaire.getColor());
            jsInfoPanel.put("Density", questionnaire.getDensity());
            jsInfoPanel.put("Fruitfulness", questionnaire.getFruitfulness());
            jsInfoPanel.put("Humidity", questionnaire.getHumidity());
            return jsInfoPanel.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
