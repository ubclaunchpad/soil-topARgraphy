package ca.ubc.eml.soiltopargraphy.editor.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import ca.ubc.eml.soiltopargraphy.editor.R
import ca.ubc.eml.soiltopargraphy.editor.ui.infopanel.DescriptionPanelFragment

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    fun onCreateInfoButtonClick(view: View){
        val manager = activity?.supportFragmentManager
        if(manager!=null){
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.container, DescriptionPanelFragment.newInstance())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        //attach listener to method for creating info panel
        val button = view.findViewById<View>(R.id.create_info_button)
        button.setOnClickListener { onCreateInfoButtonClick(view) }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
