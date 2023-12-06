package com.tech.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tech.domain.model.Position
import com.tech.domain.model.SatelliteDetailDomainModel
import com.tech.domain.usecase.GetSatellitesFromMemoryUseCase
import com.tech.domain.usecase.UpdatePositionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

//With VM reducing Configuration Changes & Memory leaks
@HiltViewModel
class SatelliteDetailViewModel @Inject constructor(
    private val getSatellitesFromMemoryUseCase: GetSatellitesFromMemoryUseCase,
    private val updatePositionUseCase: UpdatePositionUseCase
) : ViewModel() {

    private val _satelliteDetails = MutableSharedFlow<SatelliteDetailDomainModel>()
    val satelliteDetails = _satelliteDetails.asSharedFlow()

    private val _positionsFlow = MutableSharedFlow<Position>()
    val positionsFlow = _positionsFlow.asSharedFlow()

    fun loadSatelliteDetails(satelliteID: Int) {
        viewModelScope.launch {
            getSatellitesFromMemoryUseCase.getSatellitesFromMemory(satelliteID).collect {
                _satelliteDetails.emit(it)
            }
        }
    }

    fun startPositionUpdates(id: Int) {
        //viewModelScope handle automatically on onCleared async flows
        viewModelScope.launch {
            val positions = updatePositionUseCase.updatePosition(id).first()
            var index = 0

            while (isActive) { // live with coroutine lifecycle and more reduce crash for when coroutine cancel
                val position = positions.getOrNull(index)
                    ?: break // Break the loop when you reach the end of the list
                _positionsFlow.emit(position)
                delay(3000)
                index = (index + 1) % positions.size
            }
        }
    }

}