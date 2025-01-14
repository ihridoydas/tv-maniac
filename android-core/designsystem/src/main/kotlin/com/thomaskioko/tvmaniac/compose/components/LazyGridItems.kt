package com.thomaskioko.tvmaniac.compose.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thomaskioko.tvmaniac.compose.extensions.copy

@Composable
fun <T> LazyGridItems(
    listState: LazyListState,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    items: List<T> = listOf(),
    rows: Int = 3,
    hPadding: Int = 8,
    itemContent: @Composable (LazyItemScope.(T) -> Unit),
) {
    val chunkedList = items.chunked(rows)
    LazyColumn(
        state = listState,
        contentPadding = paddingValues.copy(copyTop = false),
        modifier = modifier
            .padding(horizontal = hPadding.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        itemsIndexed(chunkedList) { _, item ->
            Row(modifier = Modifier) {
                item.forEachIndexed { _, item ->
                    Box(
                        modifier = Modifier
                            .weight(1F)
                            .align(Alignment.Top)
                            .padding(2.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        itemContent(item)
                    }
                }
                repeat(rows - item.size) {
                    Box(
                        modifier = Modifier
                            .weight(1F)
                            .padding(8.dp),
                    )
                }
            }
        }
    }
}
