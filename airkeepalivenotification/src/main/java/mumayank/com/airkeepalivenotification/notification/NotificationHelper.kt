package mumayank.com.airkeepalivenotification.notification

import android.app.Notification
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import android.content.pm.ApplicationInfo
import mumayank.com.airkeepalivenotification.R

class NotificationHelper {

    companion object {

        @JvmStatic
        fun startService(context: Context) {
            val intent = Intent(context, PersistentNotificationService::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(intent)
            } else {
                context.startService(intent)
            }
        }

        @JvmStatic
        fun showPersistentNotification(
            context: Context,
            appName: String
        ): Notification? {
            val notificationLayoutExpanded = RemoteViews(context.packageName, R.layout.notification_persistent)
            notificationLayoutExpanded.setTextViewText(R.id.titleTextView, "$appName is active")
            return NotificationCompat.Builder(context, context.getString(R.string.notification_channel_id_low_priority))
                .setSmallIcon(R.drawable.notification_icon_invisible)
                .setCustomBigContentView(notificationLayoutExpanded)
                .setContent(notificationLayoutExpanded)
                .setCustomContentView(notificationLayoutExpanded)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVisibility(NotificationCompat.VISIBILITY_SECRET)
                .setOnlyAlertOnce(true)
                .setGroup(System.currentTimeMillis().toString())
                .build()
        }

        @JvmStatic
        fun getApplicationName(context: Context): String {
            val applicationInfo = context.applicationInfo
            val stringId = applicationInfo.labelRes
            return if (stringId == 0) applicationInfo.nonLocalizedLabel.toString() else context.getString(stringId)
        }

    }

}
