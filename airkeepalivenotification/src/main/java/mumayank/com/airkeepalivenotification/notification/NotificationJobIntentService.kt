package mumayank.com.airkeepalivenotification.notification

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService

class NotificationJobIntentService : JobIntentService() {

    companion object {
        const val JOB_ID: Int = 0x01

        fun enqueueWork(context: Context, work: Intent) {
            enqueueWork(context, NotificationJobIntentService::class.java, JOB_ID, work)
        }
    }

    override fun onHandleWork(intent: Intent) {
        NotificationHelper.startService(this)
    }

}