package com.basri.mvvmandnotif.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.basri.mvvmandnotif.R
import com.basri.mvvmandnotif.model.get_lapor.Lapor
import com.basri.mvvmandnotif.model.get_lapor.ResponseLapor
import com.basri.mvvmandnotif.network.RetroServer
import com.basri.mvvmandnotif.ui.adapter.LaporAdapter
import com.basri.mvvmandnotif.ui.create.CreateActivity
import com.basri.mvvmandnotif.ui.detail.DetailActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity(), LaporAdapter.OnLaporListiner {

    var dataLapor : ArrayList<Lapor> = ArrayList()
    lateinit var adapterLapor: LaporAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btn_create.setOnClickListener {
            startActivity(Intent(this,CreateActivity::class.java))

        }




    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    private fun getData() {
        progress_circular.visibility = View.VISIBLE
        val api = RetroServer.apiServices
        api.getLapor().enqueue(object : Callback<ResponseLapor> {
            override fun onFailure(call: Call<ResponseLapor>, t: Throwable) {
                Toast.makeText(this@HomeActivity,
                    "gagal ambil data",Toast.LENGTH_LONG).show()

                progress_circular.visibility = View.GONE
            }

            override fun onResponse(call: Call<ResponseLapor>, response: Response<ResponseLapor>) {

                if (response.isSuccessful){

                    progress_circular.visibility = View.GONE
                    if (!response.body()?.lapor.isNullOrEmpty()){
                        Log.e("TAG","data lapor ${Gson().toJson(response.body()?.lapor)}")

                        response.body()?.lapor?.let {

                             adapterLapor =LaporAdapter(it,this@HomeActivity)
                            rv_lapor.layoutManager = LinearLayoutManager(this@HomeActivity)
                            rv_lapor.setHasFixedSize(true)
                            rv_lapor.adapter = adapterLapor
                        }




                    }else{
                        //data kosong
                        Toast.makeText(this@HomeActivity,
                            "Datanya  Kosong",Toast.LENGTH_LONG).show()
                    }
                }
            }
        })


        search.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapterLapor.filter.filter(newText)
                return false
            }

        })
    }

    override fun onLaporClick(lapor: Lapor) {

        val kirimdData = Intent(this, DetailActivity::class.java)
        kirimdData.putExtra("NAME",lapor.nama_pelapor)
        kirimdData.putExtra("DESK",lapor.deksripsi_lapor)
        kirimdData.putExtra("TGL",lapor.tanggal_lapor)
        kirimdData.putExtra("LOKASI",lapor.lokasi)
        kirimdData.putExtra("GAMBAR",lapor.gambar)
        kirimdData.putExtra("ID",lapor.id)
        startActivity(kirimdData)

    }
}