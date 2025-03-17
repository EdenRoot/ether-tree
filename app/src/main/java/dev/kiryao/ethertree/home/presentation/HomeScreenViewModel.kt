package dev.kiryao.ethertree.home.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.kiryao.ethertree.core.domain.model.Node
import dev.kiryao.ethertree.home.domain.usecase.AddNodeUseCase
import dev.kiryao.ethertree.home.domain.usecase.DeleteNodeUseCase
import dev.kiryao.ethertree.home.domain.usecase.GetNodesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.security.MessageDigest
import javax.inject.Inject

internal sealed interface HomeScreenUiEvent {
    data class NavigateTo(val parentHash: String?) : HomeScreenUiEvent
    data object OnCreateClick : HomeScreenUiEvent
    data class OnDeleteClick(val hash: String) : HomeScreenUiEvent
    data class NodeClick(val hash: String) : HomeScreenUiEvent
    data object NavigateBack : HomeScreenUiEvent
}

internal sealed interface HomeScreenUiState {
    data object Empty: HomeScreenUiState
    data class Content(
        val nodes: List<Node>,
        val currentLevel: Int
    ): HomeScreenUiState
}

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val getNodesUseCase: GetNodesUseCase,
    private val deleteNodeUseCase: DeleteNodeUseCase,
    private val addNodeUseCase: AddNodeUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeScreenUiState>(HomeScreenUiState.Empty)
    val uiState: StateFlow<HomeScreenUiState> = _uiState.asStateFlow()

    private var currentParentHash: String? = savedStateHandle.get<String?>("parentHash")
    private val navigationStack = mutableStateListOf<String?>().apply {
        add(savedStateHandle.get<String?>("parentHash"))
    }

    init {
        loadNodes()
    }

    fun handleEvent(event: HomeScreenUiEvent) {
        when (event) {
            is HomeScreenUiEvent.NodeClick -> navigateToNode(event.hash)
            is HomeScreenUiEvent.OnCreateClick -> createNode()
            is HomeScreenUiEvent.OnDeleteClick -> deleteNode(event.hash)
            is HomeScreenUiEvent.NavigateBack -> navigateBack()
            is HomeScreenUiEvent.NavigateTo -> {
                currentParentHash = event.parentHash
                loadNodes()
            }
        }
    }

    private fun loadNodes() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val nodes = getNodesUseCase(currentParentHash).first()
                _uiState.value = if (nodes.isEmpty() && navigationStack.size == 1) {
                    HomeScreenUiState.Empty
                } else {
                    HomeScreenUiState.Content(
                        nodes = nodes,
                        currentLevel = navigationStack.size - 1
                    )
                }
            } catch (e: Exception) {
                _uiState.value = HomeScreenUiState.Empty
            }
        }
    }

    private fun navigateToNode(hash: String) {
        navigationStack.add(hash)
        currentParentHash = hash
        loadNodes()
    }

    private fun navigateBack() {
        if (navigationStack.size > 1) {
            navigationStack.removeAt(navigationStack.lastIndex)
            currentParentHash = navigationStack.last()
            loadNodes()
        }
    }

    private fun createNode() {
        viewModelScope.launch(Dispatchers.IO) {
            val node = generateNode(currentParentHash)
            addNodeUseCase(node)
            loadNodes()
        }
    }

    private fun deleteNode(hash: String) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteNodeUseCase(hash)
            loadNodes()
        }
    }

    private fun generateNode(parentHash: String?): Node {
        val seed = "${parentHash ?: ""}|${System.currentTimeMillis()}"
        val bytes = MessageDigest
            .getInstance("SHA-256")
            .digest(seed.toByteArray(Charsets.UTF_8))

        val hash = "0x" + bytes.takeLast(20).joinToString("") { "%02x".format(it) }

        return Node(
            hash = hash,
            name = hash.takeLast(20),
            parentHash = parentHash
        )
    }
}