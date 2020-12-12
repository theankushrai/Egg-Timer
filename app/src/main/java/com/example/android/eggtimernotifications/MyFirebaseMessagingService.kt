package com.example.android.eggtimernotifications

import android.app.NotificationManager
import android.content.ContentValues.TAG
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.android.eggtimernotifications.util.sendNotification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "From : ${remoteMessage.from}")
        // when mesage has data payload
        remoteMessage?.data?.let {
            Log.d(TAG, "MessageDataPayload: ${remoteMessage.data}")
        }
        remoteMessage?.notification?.let {
            Log.d(TAG, "Message notification Body : ${it.body}")
            sendNotification(it.body!!)
        }
    }

    private fun sendNotification(messagebody: String) {
        val notificationManager=ContextCompat.getSystemService(
            applicationContext,NotificationManager::class.java
        )as NotificationManager
        notificationManager.sendNotification(messagebody,applicationContext)
    }

    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed Token : $token")
//        sendRegistrationToServer(token)
    }



}