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
import org.w3c.dom.Text

class InfoPanelFragment : Fragment() {

    companion object {
        fun newInstance() = InfoPanelFragment()
    }

    private lateinit var viewModel: InfoPanelViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view  = inflater.inflate(R.layout.info_panel_fragment, container, false)
        //find all four field
        val descModel = activity?.run {
            ViewModelProviders.of(this).get(DescriptionPanelViewModel::class.java)
        }
        val titleView = view.findViewById<TextView>(R.id.Info_panel_title)
        val descriptionView = view.findViewById<TextView>(R.id.Info_panel_description)
        val imageView = view.findViewById<ImageView>(R.id.ImageView)
        val imageTitleView = view.findViewById<TextView>(R.id.Image_Title)
        val imgmodel = activity?.run{
            ViewModelProviders.of(this).get(ImagePanelViewModel::class.java)
        }
        titleView.text = descModel?.name
        descriptionView.text = descModel?.description
        imageView.setImageURI(imgmodel?.uri)
        imageTitleView.text = imgmodel?.imageTitle
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(InfoPanelViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
