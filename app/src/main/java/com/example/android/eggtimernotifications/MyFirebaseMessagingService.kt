package com.example.android.eggtimernotifications

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "From : ${remoteMessage.from}")
    }

    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed Token : $token")
//        sendRegistrationToServer(token)
    }



}