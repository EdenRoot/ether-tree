package dev.kiryao.ethertree

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import dev.kiryao.ethertree.home.presentation.navigation.HomeScreenNavigation
import dev.kiryao.ethertree.ui.theme.EtherTreeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EtherTreeTheme(dynamicColor = false) {
                HomeScreenNavigation()
            }
        }
    }
}