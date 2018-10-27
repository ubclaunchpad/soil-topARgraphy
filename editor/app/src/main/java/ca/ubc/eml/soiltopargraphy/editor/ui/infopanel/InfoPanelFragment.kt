package ca.ubc.eml.soiltopargraphy.editor.ui.infopanel

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.EventLogTags
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import ca.ubc.eml.soiltopargraphy.editor.R

class InfoPanelFragment : Fragment() {

    companion object {
        fun newInstance() = InfoPanelFragment()
    }

    private lateinit var viewModel: InfoPanelViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view  = inflater.inflate(R.layout.info_panel_fragment, container, false)
        //find all three field
        val titleView = view.findViewById<TextView>(R.id.Info_panel_title)
        val descriptionView = view.findViewById<TextView>(R.id.Info_panel_description)
        val ImageView = view.findViewById<ImageView>(R.id.ImageView)
        val
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(InfoPanelViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
