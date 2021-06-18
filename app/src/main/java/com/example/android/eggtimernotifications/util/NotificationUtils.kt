/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.eggtimernotifications.util

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.example.android.eggtimernotifications.MainActivity
import com.example.android.eggtimernotifications.R
import com.example.android.eggtimernotifications.receiver.SnoozeReceiver

// Notification ID.
private val NOTIFICATION_ID = 0
private val REQUEST_CODE = 0
private val FLAGS = 0


/**
 * Builds and delivers the notification.
 *
 * @param context, activity context.
 */
fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {
    // Create the content intent for the notification, which launches
    // this activity

    val contentIntent=Intent(applicationContext,MainActivity::class.java)
    //  create PendingIntent
    val pendingIntent=PendingIntent.getActivity(applicationContext, NOTIFICATION_ID,
                                    contentIntent,PendingIntent.FLAG_UPDATE_CURRENT)
    //  Step 2.0 add style
    val eggImage=BitmapFactory.decodeResource(
        applicationContext.resources,
        R.drawable.cooked_egg
    )
    val bigPicture=NotificationCompat.BigPictureStyle()
        .bigPicture(eggImage)
        .bigLargeIcon(null)

    // add snooze action
    val snoozeIntent=Intent(applicationContext,SnoozeReceiver::class.java)
    val snoozePenndingIntent:PendingIntent=PendingIntent.getBroadcast(
        applicationContext,
        REQUEST_CODE,
        snoozeIntent,
        FLAGS
    )

    // get an instance of NotificationCompat.Builder
    val builder=NotificationCompat.Builder(applicationContext,
        applicationContext.getString(R.string.egg_notification_channel_id))

        //use the new 'breakfast' notification channel

        //  set title, text and icon to builder
        .setSmallIcon(R.drawable.cooked_egg)
        .setContentTitle(applicationContext.getString(R.string.notification_title))
        .setContentText(applicationContext.getString(R.string.notification_text))

        //  set content intent
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)

        //  add style to builder
        .setStyle(bigPicture)
        .setLargeIcon(eggImage)

        // add snooze action
        .addAction(
            R.drawable.egg_icon,
            applicationContext.getString(R.string.snooze),
            snoozePenndingIntent
        )

        //  set priority
        .setPriority(NotificationCompat.PRIORITY_HIGH)

    //  call notify
    notify(NOTIFICATION_ID,builder.build())
}

//  Cancel all notifications
fun NotificationManager.CancelNotifications(){
    cancelAll()
}