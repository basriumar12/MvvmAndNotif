package com.basri.mvvmandnotif

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tangkapData = intent.getStringExtra("data")
        Log.e("TAG","tangkap data $tangkapData")


        FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w(ContentValues.TAG, "getInstanceId failed", task.exception)
                        return@OnCompleteListener
                    }
                    if (task.isSuccessful) {
                        task.result?.let {
                            val token = task.result?.token

                            ///hit api utk save token


                            Log.e("TOKEN fcm","$token")
                        }
                    }

                })
    }
}