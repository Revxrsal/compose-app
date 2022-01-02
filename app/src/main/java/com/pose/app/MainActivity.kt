package com.pose.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pose.app.model.CompViewModel
import com.pose.app.ui.theme.ComposeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAppTheme(darkTheme = true) {
                val model by viewModels<CompViewModel>()
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainView(model = model)
                }
            }
        }
    }
}

@Composable
fun MainView(model: CompViewModel) {
    Text("$model")
}
