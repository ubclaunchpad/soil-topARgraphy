package ca.ubc.eml.soiltopargraphy.editor.ui.quizpanel

/**
 * QuizPanel will hold question, answers (which includes a correct answer)
 */

data class QuizPanel(

//fields
var type: String,
var info1: String,
var info2: String,
var info3: String,
var info4: String)