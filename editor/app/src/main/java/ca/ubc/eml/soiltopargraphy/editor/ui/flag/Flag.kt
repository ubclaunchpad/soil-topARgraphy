package ca.ubc.eml.soiltopargraphy.editor.ui.flag

import ca.ubc.eml.soiltopargraphy.editor.ui.infopanel.InfoPanel

data class Flag(
        val latitude: Float,
        val longitude: Float,
        val ID: Int,
        val panel: InfoPanel
)
