package dev.kiryao.ethertree.home.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun HomeScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BackHandler(
        enabled = uiState is HomeScreenUiState.Content &&
                (uiState as HomeScreenUiState.Content).currentLevel > 0
    ) { viewModel.handleEvent(HomeScreenUiEvent.NavigateBack) }

    HomeScreen(
        modifier = modifier,
        uiState = uiState,
        onCreateNodeFloatingActionButtonClick = {
            viewModel.handleEvent(HomeScreenUiEvent.OnCreateClick)
        },
        onDeleteNodeButtonClick = { hash ->
            viewModel.handleEvent(HomeScreenUiEvent.OnDeleteClick(hash))
        },
        onNodeClick = { hash ->
            viewModel.handleEvent(HomeScreenUiEvent.NodeClick(hash))
        },
        onBackClick = { viewModel.handleEvent(HomeScreenUiEvent.NavigateBack) }
    )
}