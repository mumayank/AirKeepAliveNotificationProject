package mumayank.com.airkeepalivenotification.notification

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class PersistentNotificationPeriodicWorker(val appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {

    override fun doWork(): Result {
        // Do the work here
        NotificationHelper.startService(appContext)

        // Indicate whether the task finished successfully with the Result
        return Result.success()
    }
}