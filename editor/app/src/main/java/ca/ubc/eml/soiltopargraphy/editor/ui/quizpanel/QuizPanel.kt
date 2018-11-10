package ca.ubc.eml.soiltopargraphy.editor.ui.quizpanel

/**
 * QuizPanel will hold question, answers (which includes a correct answer)
 */

data class QuizPanel(

//fields
var type: String,
var color: String,
var density: String,
var fruitfulness: String,
var humidity: String)