package ca.ubc.eml.soiltopargraphy.editor

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ca.ubc.eml.soiltopargraphy.editor.ui.main.MainFragment






class MainActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

        when {
            intent?.action == Intent.ACTION_SEND -> {
                if ("text/plain" == intent.type) {
                    handleSendText(intent) // Handle text being sent
                } 
            }
        }

    }

    private fun handleSendText(intent: Intent){
        intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
            //Update UI to reflect text being shared
        }

    }

}
