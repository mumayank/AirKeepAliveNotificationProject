package mumayank.com.airkeepalivenotificationproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mumayank.com.airkeepalivenotification.AirKeepAliveNotification

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AirKeepAliveNotification.init2(this)
    }
}
