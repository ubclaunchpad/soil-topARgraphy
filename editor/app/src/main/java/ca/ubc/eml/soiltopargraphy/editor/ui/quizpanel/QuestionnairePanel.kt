package ca.ubc.eml.soiltopargraphy.editor.ui.quizpanel

/**
 * QuestionnairePanel will hold question, answers (which includes a correct answer)
 */

data class QuestionnairePanel(

//fields
var soilType: String,
var color: String,
var density: String,
var fruitfulness: String,
var humidity: String)