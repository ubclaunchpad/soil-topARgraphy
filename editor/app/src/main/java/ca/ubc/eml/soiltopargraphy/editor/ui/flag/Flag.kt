package ca.ubc.eml.soiltopargraphy.editor.ui.flag

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import ca.ubc.eml.soiltopargraphy.editor.ui.infopanel.InfoPanel

@Entity(tableName = "flag_table")
data class Flag(
        val latitude: Float,
        val longitude: Float,
        @PrimaryKey
        val ID: Int,
        val panel: InfoPanel
)
