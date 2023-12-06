package com.tech.presentation

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tech.domain.usecase.SatelliteUseCase
import com.tech.presentation.mapper.toUiModelList
import com.tech.presentation.state.SatelliteListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SatellitesListViewModel @Inject constructor(private val getSatelliteUseCase: SatelliteUseCase) :
    ViewModel() {

    private val _satelliteListState =
        MutableStateFlow<SatelliteListViewState>(SatelliteListViewState.Loading)
    val satelliteListState = _satelliteListState.asStateFlow()

    private val searchQuery = MutableStateFlow("")

    fun loadSatellites() {
        viewModelScope.launch {
            getSatelliteUseCase.getSatellites()
                .onStart { delay(500) }
                .onStart { _satelliteListState.value = SatelliteListViewState.Loading }
                .collect { satellites ->
                    _satelliteListState.value = SatelliteListViewState.Success(satellites.toUiModelList())
                }
        }
    }
    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    fun startSearch() {
        viewModelScope.launch {
            searchQuery
                .debounce(300)
                .flatMapLatest { query ->
                    if (query.isEmpty()) {
                        getSatelliteUseCase.getSatellites()
                    } else {
                        getSatelliteUseCase.getSatellites().map { satellites ->
                            satellites.filter { it.name.contains(query, ignoreCase = true) }
                        }
                    }
                }.collect { filteredSatellites ->
                    _satelliteListState.value = SatelliteListViewState.Success(filteredSatellites.toUiModelList())
                }
        }
    }

    fun setBundle(satelliteID: Int): Bundle {
        val bundle = Bundle().apply {
            putInt("satelliteID", satelliteID)
        }
        return bundle
    }

    fun updateSearchQuery(query: String) {
        searchQuery.value = query
    }

}