package ca.ubc.eml.soiltopargraphy.editor.ui.quizpanel

import android.arch.persistence.room.Entity

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