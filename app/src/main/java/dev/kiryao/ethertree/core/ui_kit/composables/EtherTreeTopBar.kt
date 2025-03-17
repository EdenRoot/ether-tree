package dev.kiryao.ethertree.core.ui_kit.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import dev.kiryao.ethertree.R

@Composable
fun EtherTreeTopBar(
    modifier: Modifier = Modifier,
    showBackButton: Boolean,
    onBackClick: () -> Unit,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    titleContentColor: Color = MaterialTheme.colorScheme.onPrimary,
    title: String
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = { Text(title) },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(
                            R.string.top_bar_back_button_description
                        )
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            titleContentColor = titleContentColor,
            containerColor = containerColor
        )
    )
}
