package com.basri.mvvmandnotif.model.get_lapor

data class ResponseLapor(
    val lapor: ArrayList<Lapor>? = null ,
    val message: String,
    val success: Boolean
)