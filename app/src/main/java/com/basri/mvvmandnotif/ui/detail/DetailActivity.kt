package com.basri.mvvmandnotif.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.basri.mvvmandnotif.R
import com.basri.mvvmandnotif.constant.Constant
import com.basri.mvvmandnotif.model.ResponseData
import com.basri.mvvmandnotif.network.RetroServer
import com.basri.mvvmandnotif.ui.edit.EditActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.activity_detail.*
import okhttp3.Callback
import retrofit2.Call
import retrofit2.Response


class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        getData()
    }

    private fun getData() {
        tv_desk.text = intent.getStringExtra("DESK")
        tv_name.text = intent.getStringExtra("NAME")
        tv_tanggal.text = intent.getStringExtra("TGL")
        tv_lokasi.text = intent.getStringExtra("LOKASI")
        Glide.with(this)
            .load("${Constant.BASE_URL_IMAGE}${intent.getStringExtra("GAMBAR")}")
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .error(R.drawable.ic_launcher_background)
            .into(img_foto)




    }

    fun deleteData(){
        val api = RetroServer.apiServices
        api.deleteData(intent.getStringExtra("ID"))
            .enqueue(object : retrofit2.Callback<ResponseData>{
                override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                   Toast.makeText(this@DetailActivity,"Gagal hapus",
                   Toast.LENGTH_LONG
                       ).show()
                }

                override fun onResponse(
                    call: Call<ResponseData>,
                    response: Response<ResponseData>
                ) {

                    if (response.isSuccessful){
                        Toast.makeText(this@DetailActivity,"Berhasil hapus",
                            Toast.LENGTH_LONG
                        ).show()

                        finish()
                    }
                }
            })
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.item_delete ->{
                    deleteData()
            }

            R.id.item_edit -> {
                editData()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun editData() {
        val kirimdData = Intent(this, EditActivity::class.java)
        kirimdData.putExtra("NAME",tv_name.text)
        kirimdData.putExtra("DESK",tv_desk.text)
        kirimdData.putExtra("TGL",tv_tanggal.text)
        kirimdData.putExtra("LOKASI",tv_lokasi.text)
        kirimdData.putExtra("GAMBAR",intent.getStringExtra("GAMBAR"))
        kirimdData.putExtra("ID",intent.getStringExtra("ID"))
        startActivity(kirimdData)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.item_detail, menu)
        return super.onCreateOptionsMenu(menu)


    }
}