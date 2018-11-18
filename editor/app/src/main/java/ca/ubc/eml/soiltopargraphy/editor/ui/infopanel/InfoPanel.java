package ca.ubc.eml.soiltopargraphy.editor.ui.infopanel;

import ca.ubc.eml.soiltopargraphy.editor.ui.quizpanel.QuestionnairePanel;

public class InfoPanel {
    private String name;
    private String description;
    private String image;
    private QuestionnairePanel quiz;

    public InfoPanel(String name, String description, String image, QuestionnairePanel quiz) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.quiz = quiz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public QuestionnairePanel getQuiz() {
        return quiz;
    }

    public void setQuiz(QuestionnairePanel quiz) {
        this.quiz = quiz;
    }
}
