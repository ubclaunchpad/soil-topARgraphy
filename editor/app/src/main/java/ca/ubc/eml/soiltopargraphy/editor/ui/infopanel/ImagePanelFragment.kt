package ca.ubc.eml.soiltopargraphy.editor.ui.infopanel

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ca.ubc.eml.soiltopargraphy.editor.R

/**
 * Fragment where user uploads image from library or take image
 */


class ImagePanelFragment : Fragment() {

    private var mViewModel: ImagePanelViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.image_panel_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(ImagePanelViewModel::class.java)
        // TODO: Use the ViewModel
    }

    companion object {

        fun newInstance(): ImagePanelFragment {
            return ImagePanelFragment()
        }
    }

}
