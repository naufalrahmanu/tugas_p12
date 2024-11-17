import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.tugas_p12.NotificationActionReceiver
import com.example.tugas_p12.R
import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createCustomNotification()
    }

    private fun createCustomNotification() {
        val channelId = "custom_channel_id"
        val notificationId = 1

        // Buat Notification Channel (hanya untuk Android 8.0+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelName = "Custom Notification"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, importance)
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(channel)
        }

        // RemoteViews untuk layout kustom
        val remoteViews = RemoteViews(packageName, R.layout.activity_main)

        // Intent untuk "like"
        val likeIntent = Intent(this, NotificationActionReceiver::class.java).apply {
            action = "LIKE_ACTION"
        }
        val likePendingIntent = PendingIntent.getBroadcast(this, 0, likeIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        remoteViews.setOnClickPendingIntent(R.id.like, likePendingIntent)

        // Intent untuk "dislike"
        val dislikeIntent = Intent(this, NotificationActionReceiver::class.java).apply {
            action = "DISLIKE_ACTION"
        }
        val dislikePendingIntent = PendingIntent.getBroadcast(this, 0, dislikeIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        remoteViews.setOnClickPendingIntent(R.id.dislike, dislikePendingIntent)

        // Buat notifikasi
        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.baseline_person_24)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(remoteViews)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        // Tampilkan notifikasi

    }
}
