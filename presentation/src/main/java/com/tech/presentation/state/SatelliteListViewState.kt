package com.tech.presentation.state

import com.tech.presentation.model.SatelliteUIModel

sealed class SatelliteListViewState {
    data object Loading : SatelliteListViewState()
    data class Success(val satellites: List<SatelliteUIModel>) : SatelliteListViewState()
    data class Error(val message: String) : SatelliteListViewState()
}