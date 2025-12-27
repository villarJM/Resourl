package com.devvillar.resourl.features.addresource.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ResourceItem() {

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(40.dp))
            .background(Color.White)
            .height(78.dp)
            .padding(12.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(40.dp))
                    .background(Color(0XFFF3EFFF))
                    .size(50.dp)
            ) {

            }

            Column(
                modifier = Modifier
                    .weight(1f),
            ) {
                Text(
                    "Name Resource"
                )
                Text(
                    "https://example.com"
                )
                Text(
                    "DD, Month YYYY"
                )
            }

            FilledIconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(50.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResourceItemPreview() {
    ResourceItem()
}