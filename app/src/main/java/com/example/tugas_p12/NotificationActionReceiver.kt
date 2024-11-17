package com.example.tugas_p12



import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class NotificationActionReceiver : BroadcastReceiver() {

    companion object {
        var likeCount = 0
        var dislikeCount = 0
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            "LIKE_ACTION" -> {
                likeCount++
                Toast.makeText(context, "Liked! Total: $likeCount", Toast.LENGTH_SHORT).show()
            }
            "DISLIKE_ACTION" -> {
                dislikeCount++
                Toast.makeText(context, "Disliked! Total: $dislikeCount", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
