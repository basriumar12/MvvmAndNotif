package com.basri.mvvmandnotif.network

import com.basri.mvvmandnotif.model.ResponseData
import com.basri.mvvmandnotif.model.ResponseUpload
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface RestApi {

    @FormUrlEncoded
    @POST("create.php")
    fun insertData(
        @Field("nama_pelapor") nama: String?,
        @Field("lokasi") detail: String?,
        @Field("deksripsi_lapor") desk: String?,
        @Field("gambar") gambar: String?,
        @Field("tanggal_lapor") tanggal_lapor: String?
    ): Call<ResponseData>

    @Multipart
    @POST("add_foto.php")
    fun createFoto(
        @Part file: MultipartBody.Part?,
        @Part("nama_foto") namaFoto: RequestBody?
    ): Call<ResponseUpload>
}