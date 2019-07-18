package mumayank.com.airkeepalivenotification.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootCompletedReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null) {
            return
        }

        if (intent == null) {
            return
        }

        if (intent.action != Intent.ACTION_BOOT_COMPLETED) {
            return
        }

        NotificationJobIntentService.enqueueWork(context, Intent())
    }

}
