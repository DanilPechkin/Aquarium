package com.danilp.aquariumhelper.presentation.screens.aquarium.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.danilp.aquariumhelper.R
import com.danilp.aquariumhelper.presentation.screens.SearchField
import com.danilp.aquariumhelper.presentation.screens.destinations.AquariumEditDestination
import com.danilp.aquariumhelper.presentation.screens.destinations.InAquariumScreenDestination
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun AquariumList(
    navigator: DestinationsNavigator,
    viewModel: AquariumListViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isRefreshing)
    var isSearchFieldVisible by rememberSaveable { mutableStateOf(false) }
    val searchFieldFocusRequester = remember { FocusRequester() }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.aquariums_top_bar_title),
                        modifier = Modifier.padding(horizontal = 4.dp),
                        maxLines = 1
                    )
                },
                actions = {
                    SearchField(
                        value = state.searchQuery,
                        onValueChange = {
                            viewModel.onEvent(
                                AquariumListEvent.OnSearchQueryChange(it)
                            )
                        },
                        isSearchFieldVisible = isSearchFieldVisible,
                        hideSearchField = { isSearchFieldVisible = false },
                        focusRequester = searchFieldFocusRequester
                    )

                    IconButton(
                        onClick = {
                            isSearchFieldVisible = !isSearchFieldVisible
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Search, contentDescription = stringResource(
                                R.string.search_icon
                            )
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = MaterialTheme.colorScheme.primary,
                onClick = {
                    navigator.navigate(
                        AquariumEditDestination(0)
                    )
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = stringResource(R.string.add_aquarium_fab)
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {

            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = { viewModel.onEvent(AquariumListEvent.Refresh) }
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(128.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.aquariums) { aquarium ->
                        AquariumListItem(
                            aquarium = aquarium,
                            modifier = Modifier
                                .clickable {
                                    viewModel.onEvent(
                                        AquariumListEvent.OnAquariumClicked(aquarium.id)
                                    )
                                    navigator.navigate(
                                        InAquariumScreenDestination()
                                    )
                                }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}