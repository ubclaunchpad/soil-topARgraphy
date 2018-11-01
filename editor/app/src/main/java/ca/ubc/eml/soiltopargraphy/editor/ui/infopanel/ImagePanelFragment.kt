package ca.ubc.eml.soiltopargraphy.editor.ui.infopanel

import android.Manifest
import android.app.Activity.RESULT_OK
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import ca.ubc.eml.soiltopargraphy.editor.R
import com.bumptech.glide.Glide


/**
 * Fragment where user uploads image from library or take image
 */


class ImagePanelFragment : Fragment() {

    private var mViewModel: ImagePanelViewModel? = null
    private lateinit var mImageView: ImageView

    fun onImageAddCameraButtonClick() {
        //check permission
        var camerapermission = ContextCompat.checkSelfPermission(this.context!!, Manifest.permission.CAMERA)
        var writeExternalPermission = ContextCompat.checkSelfPermission(this.context!!,Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val requestCode = 0
        if (camerapermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this.requireActivity(),
                    arrayOf(Manifest.permission.CAMERA),
                    requestCode)
        }
        if(writeExternalPermission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this.requireActivity(),
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    requestCode)
        }
        camerapermission = ContextCompat.checkSelfPermission(this.context!!,Manifest.permission.CAMERA)
        writeExternalPermission = ContextCompat.checkSelfPermission(this.context!!,Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (camerapermission == PackageManager.PERMISSION_GRANTED && writeExternalPermission== PackageManager.PERMISSION_GRANTED)
            addImageFromCamera()

    }

    //add image via gallery
    fun onImageAddGalleryButtonClick() {
        //check permission
        var permission = ContextCompat.checkSelfPermission(this.context!!, Manifest.permission.READ_EXTERNAL_STORAGE)
        val requestCode = 0
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this.requireActivity(),
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    requestCode)
        }
        permission = ContextCompat.checkSelfPermission(this.context!!, Manifest.permission.READ_EXTERNAL_STORAGE)
        if (permission == PackageManager.PERMISSION_GRANTED)
            addImageFromGallery()
    }

    fun addImageFromGallery() {
        val getIntent = Intent(Intent.ACTION_GET_CONTENT)
        getIntent.setType("image/*")
        startActivityForResult(getIntent, IMAGE_PICK_CODE)
    }

    fun addImageFromCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val getIntent = Intent(Intent.ACTION_PICK)
        intent.putExtra(Intent.EXTRA_INITIAL_INTENTS,getIntent)
        startActivityForResult(Intent.createChooser(intent, "Pick an appropriate app"), CAMERA_PICK_CODE)
    }
    fun onToViewInfoPanelButtonClick() {
        mViewModel?.imageTitle = view?.findViewById<EditText>(R.id.image_title)?.text.toString()
        val manager = activity?.supportFragmentManager
        if(manager!=null){
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.container, InfoPanelFragment.newInstance())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.image_panel_fragment, container, false)
        val addImageViaCameraButton = view.findViewById<Button>(R.id.add_image_via_camera_button)
        addImageViaCameraButton.setOnClickListener { onImageAddCameraButtonClick() }
        val addImageViaGalleryButton = view.findViewById<Button>(R.id.add_image_via_gallery_button)
        addImageViaGalleryButton.setOnClickListener { onImageAddGalleryButtonClick() }
        val toViewInfoPanelButton = view.findViewById<Button>(R.id.to_view_info_panel_button)
        toViewInfoPanelButton.setOnClickListener {onToViewInfoPanelButtonClick() }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mImageView = view.findViewById(R.id.imageView)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = activity?.run {
            ViewModelProviders.of(this).get(ImagePanelViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        // TODO: Use the ViewModel
    }

    companion object {

        fun newInstance(): ImagePanelFragment {
            return ImagePanelFragment()
        }

        private val IMAGE_PICK_CODE = 20
        private val CAMERA_PICK_CODE = 21
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == CAMERA_PICK_CODE && resultCode == RESULT_OK) {
            val imageBitmap = data.extras.get("data") as Bitmap
            Glide.with(context!!).load(data.data).into(mImageView)
            view?.findViewById<ImageView>(R.id.imageView)?.visibility = View.VISIBLE
            mViewModel?.uri = data.data
        }
        if (requestCode == IMAGE_PICK_CODE && resultCode == RESULT_OK) {

//            view?.findViewById<ImageView>(R.id.imageView)?.setImageURI(data.data)
            Glide.with(context!!).load(data.data).into(mImageView)
//            transformPicture(data.data)
            view?.findViewById<ImageView>(R.id.imageView)?.visibility = View.VISIBLE
            mViewModel?.uri = data.data
        }
    }


}
