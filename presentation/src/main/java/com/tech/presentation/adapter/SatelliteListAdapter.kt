package com.tech.presentation.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tech.presentation.R
import com.tech.presentation.model.SatelliteUIModel
import com.tech.presentation.databinding.ItemSatelliteBinding

class SatelliteListAdapter(
    private val satelliteClickListener: (Int) -> Unit
) : ListAdapter<SatelliteUIModel, SatelliteListAdapter.SatelliteViewHolder>(
    SatelliteDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SatelliteViewHolder {
        return SatelliteViewHolder(
            ItemSatelliteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SatelliteViewHolder, position: Int) {
        val satellite = getItem(position)
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            satelliteClickListener(satellite.id)
        }
    }

    inner class SatelliteViewHolder(private val binding: ItemSatelliteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(satellite: SatelliteUIModel) {
            with(binding) {
                satelliteName.text = satellite.name
                statusIndicator.setBackgroundColor(if (satellite.active) Color.GREEN else Color.RED)
                status.text =
                    itemView.context.getString(if (satellite.active) R.string.active else R.string.passive)
                status.setTextColor(
                    if (satellite.active) ContextCompat.getColor(
                        itemView.context,
                        R.color.active_color
                    ) else ContextCompat.getColor(
                        itemView.context, R.color.passive_color
                    )
                )
            }
        }
    }

    class SatelliteDiffCallback : DiffUtil.ItemCallback<SatelliteUIModel>() {
        override fun areItemsTheSame(
            oldItem: SatelliteUIModel,
            newItem: SatelliteUIModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SatelliteUIModel,
            newItem: SatelliteUIModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}