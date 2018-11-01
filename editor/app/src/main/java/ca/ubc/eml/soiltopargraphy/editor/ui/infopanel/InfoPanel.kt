package ca.ubc.eml.soiltopargraphy.editor.ui.infopanel

import android.net.Uri
import ca.ubc.eml.soiltopargraphy.editor.ui.quizpanel.QuizPanel

data class InfoPanel(
        val name: String,
        val description: String,
        val image: Uri, // TODO decide on type
        val quiz: QuizPanel
)