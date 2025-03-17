package dev.kiryao.ethertree.core.ui_kit.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NodeItem(
    modifier: Modifier = Modifier,
    name: String,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    deleteButtonTintColor: Color = MaterialTheme.colorScheme.onPrimary,
    onNodeClick: () -> Unit,
    onDeleteButtonClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .clickable { onNodeClick() }
            .background(
                color = backgroundColor,
                shape = ShapeDefaults.Medium
            )
            .height(74.dp)
            .padding(10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.padding(start = 5.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = name,
                color = textColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }

        IconButton(
            modifier = Modifier.size(24.dp),
            onClick = onDeleteButtonClick
        ) {
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = null,
                tint = deleteButtonTintColor
            )
        }
    }
}

@Composable
@Preview
private fun NodeItemPreview() {
    NodeItem(
        onNodeClick = {},
        modifier = Modifier,
        name = "dg25fdh55h6g2gt5gdg4",
    ) { }
}