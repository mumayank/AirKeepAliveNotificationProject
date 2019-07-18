package mumayank.com.airkeepalivenotification.notification

import android.app.Service
import android.content.Intent
import android.os.IBinder

class PersistentNotificationService : Service() {

    companion object {
        const val ONGOING_NOTIFICATION_ID = 100 // must not be zero
    }

    override fun onCreate() {
        startForeground(ONGOING_NOTIFICATION_ID, NotificationHelper.showPersistentNotification(this, NotificationHelper.getApplicationName(applicationContext)))
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // If we get killed, after returning from here, restart
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        // currently not allowing any activity component to bind to it
        return null
    }

    override fun onDestroy() {
        // do nothing
    }

}
