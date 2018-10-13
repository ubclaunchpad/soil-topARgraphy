package ca.ubc.eml.soiltopargraphy.editor.ui.infopanel;

import ca.ubc.eml.soiltopargraphy.editor.ui.quizpanel.QuizPanel;

public class JInfoPanel {

    private String name;
    private String description;
    private String image;
    private QuizPanel quiz;

    public JInfoPanel (String name, String description, String image, QuizPanel quiz) {
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

    public QuizPanel getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizPanel quiz) {
        this.quiz = quiz;
    }
}
