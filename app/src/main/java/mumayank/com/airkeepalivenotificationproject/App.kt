package mumayank.com.airkeepalivenotificationproject

import android.app.Application
import mumayank.com.airkeepalivenotification.AirKeepAliveNotification

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AirKeepAliveNotification.init(this)
    }

}