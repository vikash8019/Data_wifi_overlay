package com.vikash.nettoggle

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var statusText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        statusText = findViewById(R.id.statusText)
        val btnPermission = findViewById<Button>(R.id.btnPermission)
        val btnStart = findViewById<Button>(R.id.btnStart)
        val btnStop = findViewById<Button>(R.id.btnStop)

        btnPermission.setOnClickListener {
            requestOverlayPermission()
        }

        btnStart.setOnClickListener {
            if (hasOverlayPermission()) {
                startService(Intent(this, OverlayService::class.java))
                Toast.makeText(this, "Overlay start ho gaya", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Pehle 'Allow Overlay' permission do", Toast.LENGTH_SHORT).show()
                requestOverlayPermission()
            }
        }

        btnStop.setOnClickListener {
            stopService(Intent(this, OverlayService::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        statusText.text = if (hasOverlayPermission())
            "Overlay permission: ON ✅" else "Overlay permission: OFF ❌"
    }

    private fun hasOverlayPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Settings.canDrawOverlays(this)
        } else true
    }

    private fun requestOverlayPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:$packageName")
            )
            startActivity(intent)
        }
    }
}
