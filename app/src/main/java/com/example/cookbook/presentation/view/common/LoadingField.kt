package com.example.cookbook.presentation.view.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cookbook.R

@Composable
fun LoadingField() {

    val configuration = LocalConfiguration.current
    val cardHeight = configuration.screenHeightDp.dp / 3

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        TextHeader4(
            header = stringResource(id = R.string.loading),
            modifier = Modifier.padding(end = 6.dp)
        )
        CircularProgressIndicator(
            modifier = Modifier
                .padding(horizontal = 6.dp)
                .size(36.dp)
        )
    }

}