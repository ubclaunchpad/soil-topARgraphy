package ca.ubc.eml.soiltopargraphy.editor.ui.infopanel

import android.Manifest
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
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
import android.support.v4.content.FileProvider
import java.io.File
import java.io.IOException

/**
 * Fragment where user uploads image from library or take image
 */


class ImagePanelFragment : Fragment() {
    private lateinit var photoPath: String
    private lateinit var photoUri: Uri
    private lateinit var mViewModel: ImagePanelViewModel
    private lateinit var mImageView: ImageView

    private fun onImageAddCameraButtonClick() {
        //check permission
        var camerapermission = ContextCompat.checkSelfPermission(this.context!!,Manifest.permission.CAMERA)
        val requestCode = 0
        if (camerapermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this.requireActivity(),
                    arrayOf(Manifest.permission.CAMERA),
                    requestCode)
        }
        camerapermission = ContextCompat.checkSelfPermission(this.context!!,Manifest.permission.CAMERA)
        if ( camerapermission == PackageManager.PERMISSION_GRANTED)
            addImageFromCamera()
    }

    //add image via gallery
    private fun onImageAddGalleryButtonClick() {
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

    private fun addImageFromGallery() {
        val getIntent = Intent(Intent.ACTION_GET_CONTENT)
        getIntent.setType("image/*")
        startActivityForResult(getIntent, IMAGE_PICK_CODE)
    }

    private fun createImageFile(): File? {
        val fileName = "MyPicture"
        val storageDir = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(
                fileName,
                ".jpg",
                storageDir
        )
        photoPath = image.absolutePath
        return image
    }

    private fun addImageFromCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(intent.resolveActivity(context!!.packageManager)!=null){
            var photoFile: File? = null
            try{
                photoFile = createImageFile()
            }
            catch (e: IOException){}
            if(photoFile != null){
                 photoUri = FileProvider.getUriForFile(
                        context!!,
                        "ca.ubc.eml.soiltopargraphy.editor.fileProvider",
                        photoFile
                )
                intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri)
                startActivityForResult(intent, CAMERA_PICK_CODE)
            }
        }
    }

    private fun onToViewInfoPanelButtonClick() {
        mViewModel.imageTitle = view?.findViewById<EditText>(R.id.image_title)?.text.toString()
        val manager = activity?.supportFragmentManager
        if (manager != null) {
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
        toViewInfoPanelButton.setOnClickListener { onToViewInfoPanelButtonClick() }
        mImageView = view.findViewById(R.id.imageView)
        return view
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

        private const val IMAGE_PICK_CODE = 20
        private const val CAMERA_PICK_CODE = 21
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != RESULT_CANCELED) {
            if (requestCode == CAMERA_PICK_CODE && resultCode == RESULT_OK) {
                mViewModel.uri = photoUri
                mImageView.setImageURI(mViewModel.uri)
                Glide.with(context!!).load(photoUri).into(mImageView)
                mImageView.visibility = View.VISIBLE
            }
            if (requestCode == IMAGE_PICK_CODE && resultCode == RESULT_OK) {

//            view?.findViewById<ImageView>(R.id.imageView)?.setImageURI(data.data)
                Glide.with(context!!).load(data?.data).into(mImageView)
//            transformPicture(data.data)
                view?.findViewById<ImageView>(R.id.imageView)?.visibility = View.VISIBLE
                mViewModel.uri = data!!.data
            }
        }
    }


}
