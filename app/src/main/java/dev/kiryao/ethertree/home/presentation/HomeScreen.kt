package dev.kiryao.ethertree.home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.kiryao.ethertree.R
import dev.kiryao.ethertree.core.domain.model.Node
import dev.kiryao.ethertree.core.ui_kit.composables.CreateNodeFloatingActionButton
import dev.kiryao.ethertree.core.ui_kit.composables.EtherTreeTopBar
import dev.kiryao.ethertree.core.ui_kit.composables.NodeItem

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeScreenUiState,
    onCreateNodeFloatingActionButtonClick: () -> Unit,
    onDeleteNodeButtonClick: (String) -> Unit,
    onNodeClick: (String) -> Unit,
    onBackClick: () -> Unit
) {

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            EtherTreeTopBar(
                modifier = Modifier.shadow(4.dp),
                title = when (uiState) {
                  is HomeScreenUiState.Content -> "Level ${uiState.currentLevel + 1}"
                  else -> stringResource(R.string.ether_tree_main_topbar_title)
                },
                showBackButton = uiState is HomeScreenUiState.Content && uiState.currentLevel > 0,
                onBackClick = onBackClick
            )
        },
        floatingActionButton = {
            CreateNodeFloatingActionButton {
                onCreateNodeFloatingActionButtonClick()
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when(uiState) {
                is HomeScreenUiState.Empty -> HomeScreenEmpty()
                is HomeScreenUiState.Content -> HomeScreenContent(
                    nodes = uiState.nodes,
                    onDeleteNodeButtonClick = onDeleteNodeButtonClick,
                    onNodeClick = onNodeClick
                )
            }
        }
    }
}

@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier,
    nodes: List<Node>,
    onDeleteNodeButtonClick: (String) -> Unit,
    onNodeClick: (String) -> Unit
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        itemsIndexed(nodes) { index, node ->
            Spacer(modifier = Modifier.height(20.dp))
            NodeItem(
                modifier = Modifier
                    .padding(horizontal = 15.dp),
                name = node.name,
                onNodeClick = { onNodeClick(node.hash) },
                onDeleteButtonClick = { onDeleteNodeButtonClick(node.hash) }
            )
        }
    }
}

@Composable
private fun HomeScreenEmpty(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(R.string.dont_have_any_nodes_banner),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 27.sp,
            textAlign = TextAlign.Center,
        )
    }
}