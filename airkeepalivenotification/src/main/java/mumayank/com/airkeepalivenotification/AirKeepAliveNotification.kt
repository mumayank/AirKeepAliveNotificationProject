package mumayank.com.airkeepalivenotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.work.Constraints
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import mumayank.com.airkeepalivenotification.notification.NotificationHelper
import mumayank.com.airkeepalivenotification.notification.PersistentNotificationPeriodicWorker
import java.util.concurrent.TimeUnit

class AirKeepAliveNotification {

    companion object {

        val PERSISTENT_NOTIFICATION_PERIODIC_WORKER_STARTED = "PERSISTENT_NOTIFICATION_PERIODIC_WORKER_STARTED"

        @JvmStatic
        fun init(context: Context) {
            createNotificationChannel(context)

            if (context.getSharedPreferences(NotificationHelper.getApplicationName(context), Context.MODE_PRIVATE).getBoolean(PERSISTENT_NOTIFICATION_PERIODIC_WORKER_STARTED, false).not()) {
                WorkManager.getInstance(context).enqueue(
                    PeriodicWorkRequest.Builder(
                        PersistentNotificationPeriodicWorker::class.java, 15, TimeUnit.MINUTES
                    ).setConstraints(
                        Constraints.Builder()
                            // add constraints optionally
                            .build()
                    ).build()
                )

                context.getSharedPreferences(NotificationHelper.getApplicationName(context), Context.MODE_PRIVATE).edit().putBoolean(PERSISTENT_NOTIFICATION_PERIODIC_WORKER_STARTED, true).commit()
            }
        }

        @JvmStatic
        fun init2(context: Context) {
            NotificationHelper.startService(context)
        }

        private fun createNotificationChannel(context: Context) {
            // Create the NotificationChannel, but only on API 26+ because the NotificationChannel class is new and not in the support library
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                // first kind
                val importance = NotificationManager.IMPORTANCE_HIGH
                val channel = NotificationChannel(
                    context.resources.getString(R.string.notification_channel_id_high_priority),
                    context.resources.getString(R.string.notification_channel_id_high_priority),
                    importance
                )
                channel.description = context.resources.getString(R.string.notification_channel_id_high_priority)

                // second kind
                val importance2 = NotificationManager.IMPORTANCE_HIGH
                val channel2 = NotificationChannel(
                    context.resources.getString(R.string.notification_channel_id_low_priority),
                    context.resources.getString(R.string.notification_channel_id_low_priority),
                    importance2
                )
                channel.description = context.resources.getString(R.string.notification_channel_id_low_priority)

                // Register the channel with the system; you can't change the importance or other notification behaviors after this
                val notificationManager = context.getSystemService(NotificationManager::class.java)
                if (notificationManager != null) {
                    val notificationChannels = ArrayList<NotificationChannel>()
                    notificationChannels.add(channel)
                    notificationChannels.add(channel2)
                    notificationManager.createNotificationChannels(notificationChannels)
                }
            }
        }
    }

}