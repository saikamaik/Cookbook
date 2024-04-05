package com.example.cookbook.presentation.view.common.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cookbook.R
import com.example.cookbook.presentation.view.common.TextMedium
import com.example.cookbook.ui.theme.TertiaryGray70

@Composable
fun NoSearchResults() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        TextMedium(
            text = stringResource(id = R.string.no_matches),
            color = TertiaryGray70
        )
    }
}