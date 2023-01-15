package eu.thomaskuenneth.sweet_dreams

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.window.layout.*

class SweetDreamsActivity : ComponentActivity() {

    companion object {
        val TAG: String = this::class.java.simpleName
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                content = {
                    Scaffold(
                        topBar = {
                            TopAppBar(title = {
                                Text(stringResource(id = R.string.app_name))
                            })
                        }
                    ) { padding ->
                        Content(
                            padding = padding
                        ) {
                            openScreenSaverSettings()
                        }
                    }
                },
                colorScheme = if (isSystemInDarkTheme())
                    darkColorScheme()
                else
                    lightColorScheme()
            )

        }
    }

    private fun openScreenSaverSettings() {
        val intent = Intent("android.settings.DREAM_SETTINGS")
        try {
            startActivity(intent)
        } catch (ex: ActivityNotFoundException) {
            Log.d(TAG, "${intent.action} not handeled by any activity}")
        }
    }
}

@Composable
fun Content(padding: PaddingValues, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(paddingValues = padding)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = onClick) {
            Text(text = stringResource(id = R.string.open_screensaver_settings))
        }
    }
}
