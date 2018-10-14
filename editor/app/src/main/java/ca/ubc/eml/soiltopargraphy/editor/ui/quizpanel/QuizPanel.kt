package ca.ubc.eml.soiltopargraphy.editor.ui.quizpanel

/**
 * QuizPanel will hold question, answers (which includes a correct answer)
 */

data class QuizPanel(

//fields
var question: String,
var correctAnswer: String,
var wrongAnswer1: String,
var wrongAnswer2: String,
var WrongAnswer3: String)