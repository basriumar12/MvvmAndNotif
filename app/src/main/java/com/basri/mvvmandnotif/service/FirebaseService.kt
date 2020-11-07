package com.basri.mvvmandnotif.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.net.Uri
import android.util.Log
import androidx.core.app.NotificationCompat
import com.basri.mvvmandnotif.MainActivity
import com.basri.mvvmandnotif.R
import com.google.firebase.iid.FirebaseInstanceIdService
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.json.JSONObject

class FirebaseService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        if (remoteMessage.data.isNotEmpty()) {

            Log.e("TAG", "Message data payload :" + remoteMessage.data)
            Log.e("TAG", "Message data payload :"
                    + remoteMessage.notification?.title)
        }
        remoteMessage.data?.let {

            //Log.e("TAG","data ${Gson().toJson(remoteMessage.data)}")
        }
        val jsonObject = JSONObject(remoteMessage.data as Map<*, *>)
        if (jsonObject != null) {
            val jsonObjectTitle = jsonObject.getString("title")
            val jsonObjectBody = jsonObject.getString("body")
            val jsonObjectMessage = jsonObject.getString("message")
            sendNotification(jsonObjectBody,jsonObjectTitle)
        }
    }

    private fun sendNotification(messageBody: String, messageTitle : String) {
        val GROUP_KEY_WORK = "grup"

        val soundUri: Uri =
            Uri.parse("android.resource://" + getApplicationContext().packageName + "/" + R.raw.sirene)
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val mNotificationManager =
                this.getSystemService(Context.NOTIFICATION_SERVICE) as android.app.NotificationManager
            val importance = android.app.NotificationManager.IMPORTANCE_HIGH
            val mChannel =
                NotificationChannel("Constant.CHANNEL_ID", "Constant.CHANNEL_NAME", importance)
            mChannel.description = "Constant.CHANNEL_DESCRIPTION"
            mChannel.enableLights(true)
            mChannel.lightColor = Color.RED
            mChannel.enableVibration(true)

            val audioAttributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build()
            mChannel.setSound(soundUri, audioAttributes)

            mNotificationManager.createNotificationChannel(mChannel)
        }

        val mBuilder =
            NotificationCompat.Builder(this, "Constant.CHANNEL_ID")
                .setSmallIcon(R.drawable.ic_launcher_background)

                .setContentTitle(messageTitle)
                .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
                .setSound(soundUri)
                .setAutoCancel(true)
                .setOngoing(true)
                .setGroup(GROUP_KEY_WORK)
                .setContentText(messageBody)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        val resultIntent = Intent(this, MainActivity::class.java)
        resultIntent.putExtra("data", messageBody)

        val pendingIntent = PendingIntent.getActivity(
            this, 0, resultIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        mBuilder.setContentIntent(pendingIntent)
        val mNotifyMgr =
            this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotifyMgr?.notify(1, mBuilder.build())

    }
}