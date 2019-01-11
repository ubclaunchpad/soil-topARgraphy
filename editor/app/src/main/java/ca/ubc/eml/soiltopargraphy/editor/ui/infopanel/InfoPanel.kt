package ca.ubc.eml.soiltopargraphy.editor.ui.infopanel

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.net.Uri
import ca.ubc.eml.soiltopargraphy.editor.ui.quizpanel.QuestionnairePanel

@Entity(tableName = "info_panel_table")
data class InfoPanel(
        @PrimaryKey
        val name: String,
        val description: String,
        val image: Uri,
        var Questionnaire: QuestionnairePanel?
)