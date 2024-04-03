package com.example.cookbook.presentation.view.bookmarkScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cookbook.R
import com.example.cookbook.presentation.view.common.TextHeader

@Composable
fun BookMarkScreen(

) {

    Column(
        modifier = Modifier.padding(
            start = 20.dp,
            end = 20.dp
        )
    ) {

        TextHeader(
            header = stringResource(id = R.string.saved_recipes),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(
                    top = 16.dp,
                    bottom = 16.dp,
                )
        )

//        LazyColumn(
//
//        ) {
//            RecipeBookmarkCard
//        }

    }

}