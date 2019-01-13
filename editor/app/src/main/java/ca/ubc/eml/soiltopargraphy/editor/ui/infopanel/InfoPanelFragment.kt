package ca.ubc.eml.soiltopargraphy.editor.ui.infopanel

import android.arch.lifecycle.ViewModelProviders
import android.net.Uri
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.insert
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

import ca.ubc.eml.soiltopargraphy.editor.R
import ca.ubc.eml.soiltopargraphy.editor.db.AppDatabase
import ca.ubc.eml.soiltopargraphy.editor.ui.flag.FlagMapFragment
import com.bumptech.glide.Glide

class InfoPanelFragment : Fragment() {

    companion object {
        fun newInstance() = InfoPanelFragment()
    }

    private lateinit var viewModel: InfoPanelViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view  = inflater.inflate(R.layout.info_panel_fragment, container, false)
        val saveButton = view?.findViewById<Button>(R.id.save_info_panel)
        saveButton?.setOnClickListener{onClickSaveInfoPnael(view)}
        return view
    }

    private fun loadUriInto(uri: Uri?,view: ImageView) {
        if(uri!=null) {
            Glide.with(context!!).load(uri).into(view)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(InfoPanelViewModel::class.java)
        val descModel = activity!!.run {
            ViewModelProviders.of(this).get(DescriptionPanelViewModel::class.java)
        }
        val imgmodel = activity!!.run{
            ViewModelProviders.of(this).get(ImagePanelViewModel::class.java)
        }
        viewModel.image = imgmodel.uri
        viewModel.ImageTitle = imgmodel.imageTitle
        viewModel.description = descModel.description
        viewModel.name = descModel.name
        val titleView = view?.findViewById<TextView>(R.id.Info_panel_title)
        val descriptionView  = view?.findViewById<TextView>(R.id.Info_panel_description)
        val imageView = view?.findViewById<ImageView>(R.id.ImageView)
        val imageTitleView  = view?.findViewById<TextView>(R.id.Image_Title)
        loadUriInto(viewModel.image, imageView!!)
        titleView?.text = viewModel.name
        descriptionView?.text = viewModel.description
        imageTitleView?.text = viewModel.ImageTitle
        // TODO: Use the ViewModel
    }
    private fun onClickSaveInfoPnael(view :View){
        val quiz =  selectQuestionnairePanel()
        val panel = InfoPanel(name = viewModel.name!!,description = viewModel.description!!,image = viewModel.image!!,Questionnaire = null)
    }
    private fun selectQuestionnairePanel(){
    }

    fun ToMain(view: View) {
        val manager = activity?.supportFragmentManager
        if (manager != null) {
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.container, FlagMapFragment.newInstance())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

}
