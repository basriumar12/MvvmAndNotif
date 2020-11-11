package com.basri.mvvmandnotif.ui.edit

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.androidnetworking.interfaces.UploadProgressListener
import com.basri.mvvmandnotif.R
import com.basri.mvvmandnotif.model.ResponseData
import com.basri.mvvmandnotif.network.RetroServer
import com.esafirm.imagepicker.features.ImagePicker
import com.google.gson.Gson
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_edit.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        getData()
        aktifPermission()
        inputData()

    }

    private fun getData() {
        edt_gambar.setText(intent.getStringExtra("GAMBAR"))
        edt_lokasi.setText(intent.getStringExtra("LOKASI"))
        edt_nama.setText(intent.getStringExtra("NAME"))
        edt_tanggal.setText(intent.getStringExtra("TGL"))
        edt_desk.setText(intent.getStringExtra("DESK"))
    }

    fun aktifPermission() {
        Dexter.withActivity(this).withPermissions(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    report?.let {
                        if (it.areAllPermissionsGranted()) {
                        }
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>?,
                    token: PermissionToken?
                ) {
                    token?.continuePermissionRequest()
                }
            }).check()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            // Get a list of picked images
            var images = ImagePicker.getImages(data)
            // or get a single image only
            var image = ImagePicker.getFirstImageOrNull(data)
            uploadFoto(File(image.path), image.name)
        }
        super.onActivityResult(requestCode, resultCode, data)

    }

    fun uploadFoto(image: File, name: String) {

        AndroidNetworking
            .upload("http://192.168.0.107:8070/lapor/lapor/add_foto.php")
            .addMultipartFile("file", image)
            .addMultipartParameter("nama_foto", name)
            .setTag("uploadTest")
            .setPriority(Priority.HIGH)
            .build()
            .setUploadProgressListener(object : UploadProgressListener {
                override fun onProgress(bytesUploaded: Long, totalBytes: Long) {
                    Toast.makeText(this@EditActivity, " Progress Upload", Toast.LENGTH_LONG)
                        .show()
                }
            })
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onError(anError: ANError?) {
                    Log.e("TAG", "error $anError")

                    Toast.makeText(this@EditActivity, " Gagal Upload", Toast.LENGTH_LONG)
                        .show()
                }

                override fun onResponse(response: JSONObject?) {

                    Toast.makeText(this@EditActivity, " Berhasil Upload", Toast.LENGTH_LONG)
                        .show()

                    val jsonObject = JSONObject(response.toString())

                    val msg = jsonObject.getString("gambar").toString()
                    if (jsonObject.getString("success").toString().equals("true")) {
                        edt_gambar.setText(msg.replaceFirst("..", ""))

                    } else {

                    }

                }
            })
    }

    private fun inputData() {

        edt_gambar.setOnClickListener {
            ImagePicker.create(this@EditActivity) // Activity or Fragment
                .start()
        }
        btn_input.setOnClickListener {

            if (
                edt_gambar.text.toString().isNullOrEmpty() ||
                edt_lokasi.text.toString().isNullOrEmpty() ||
                edt_nama.text.toString().isNullOrEmpty() ||
                edt_tanggal.text.toString().isNullOrEmpty() ||
                edt_desk.text.toString().isNullOrEmpty()
            ) {
                Toast.makeText(this, " Data tidak bisa kosong", Toast.LENGTH_LONG)
                    .show()
            } else {
                pb.visibility = View.VISIBLE
                btn_input.visibility = View.GONE


                val api = RetroServer.apiServices
                val insert: Call<ResponseData> = api.updateData(
                    edt_nama.text.toString(),
                    edt_lokasi.text.toString(),
                    edt_desk.text.toString(),
                    edt_gambar.text.toString(),
                    edt_tanggal.text.toString(),
                    intent.getStringExtra("ID")
                )

                insert.enqueue(object : Callback<ResponseData> {
                    override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                        Log.e("TAG", "message data ${t.message}")
                        pb.visibility = View.GONE
                        btn_input.visibility = View.VISIBLE
                        Toast.makeText(
                            this@EditActivity,
                            "Gagal Update, ${t.message}", Toast.LENGTH_LONG
                        )
                            .show()
                    }

                    override fun onResponse(
                        call: Call<ResponseData>,
                        response: Response<ResponseData>
                    ) {

                        if (response.isSuccessful) {


                            pb.visibility = View.GONE

                            Log.e("TAG", "message data ${response.body()?.message}")
                            Log.e("TAG", "message data ${Gson().toJson(response.body()?.message)}")

                            Toast.makeText(
                                this@EditActivity,
                                "Berhasil Update ", Toast.LENGTH_LONG
                            )
                                .show()

                            finish()
                        }

                    }
                })


            }
        }
    }
}