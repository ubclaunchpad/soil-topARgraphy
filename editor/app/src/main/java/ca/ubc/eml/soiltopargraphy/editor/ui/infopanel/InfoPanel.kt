package ca.ubc.eml.soiltopargraphy.editor.ui.infopanel

import ca.ubc.eml.soiltopargraphy.editor.ui.quizpanel.QuizPanel

data class InfoPanel(
        val name: String,
        val description: String,
        val image: Any, // TODO decide on type
        val quiz: QuizPanel
)