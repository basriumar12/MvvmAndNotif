package com.basri.mvvmandnotif.network

import com.basri.mvvmandnotif.model.ResponseData
import com.basri.mvvmandnotif.model.ResponseUpload
import com.basri.mvvmandnotif.model.get_lapor.ResponseLapor
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

    @FormUrlEncoded
    @POST("update.php")
    fun updateData(
        @Field("nama_pelapor") nama: String?,
        @Field("lokasi") detail: String?,
        @Field("deksripsi_lapor") desk: String?,
        @Field("gambar") gambar: String?,
        @Field("tanggal_lapor") tanggal_lapor: String?,
        @Field("id_lapor") id: String?
    ): Call<ResponseData>

    @FormUrlEncoded
    @POST("delete.php")
    fun deleteData(
        @Field("id") id: String

    ): Call<ResponseData>

    @GET("get.php")
    fun getLapor() : Call<ResponseLapor>


}