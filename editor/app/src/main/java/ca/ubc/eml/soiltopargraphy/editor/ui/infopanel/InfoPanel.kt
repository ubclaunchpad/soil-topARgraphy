package ca.ubc.eml.soiltopargraphy.editor.ui.infopanel

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.net.Uri
import ca.ubc.eml.soiltopargraphy.editor.ui.quizpanel.QuizPanel

@Entity(tableName = "info_panel_table")
data class InfoPanel(
        @PrimaryKey
        val name: String,
        val description: String,
        val image: Uri, // TODO decide on type
        val quiz: QuizPanel
)