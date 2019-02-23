package ca.ubc.eml.soiltopargraphy.editor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ca.ubc.eml.soiltopargraphy.editor.ui.main.MainFragment
import ca.ubc.eml.soiltopargraphy.editor.ui.terrain.TerrainListFragment






class MainActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TerrainListFragment.newInstance())
                .commitNow()
        }
    }

}
