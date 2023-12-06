package com.tech.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SatelliteUIModel(
    val id: Int,
    val active: Boolean,
    val name: String
): Parcelable