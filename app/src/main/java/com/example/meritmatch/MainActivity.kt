package com.example.meritmatch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.meritmatch.navigation.Navigation
import com.example.meritmatch.ui.theme.MeritMatchTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val context = this
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MeritMatchTheme {
                Box(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Navigation(context = context)
                }
            }
        }
    }
}