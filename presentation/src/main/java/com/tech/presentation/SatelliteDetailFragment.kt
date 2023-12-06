package com.tech.presentation

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.tech.core.extensions.collectWithLifecycle
import com.tech.domain.model.Position
import com.tech.domain.model.SatelliteDetailDomainModel
import com.tech.presentation.databinding.FragmentSatellitesDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SatelliteDetailFragment : Fragment() {

    private val viewModel: SatelliteDetailViewModel by viewModels()

    private var _binding: FragmentSatellitesDetailBinding? = null
    private val binding get() = _binding!!
    private val args: SatelliteDetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSatellitesDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadSatelliteDetails(args.satelliteID)
        viewModel.startPositionUpdates(args.satelliteID)
        observeViewModel()
    }

    private fun bindDetail(detailData: SatelliteDetailDomainModel) {
        with(binding) {
            textViewSatelliteName.text = getString(R.string.satellite_name_format, detailData.id)
            textViewFirstFlight.text =
                getString(R.string.first_flight_format, detailData.firstFlight)
            textViewHeightMass.text =
                getString(R.string.height_mass_format, detailData.height, detailData.mass)
            textViewCost.text = getString(R.string.cost_format, detailData.costPerLaunch.toString())
        }
    }

    private fun updatePosition(position: Position) {
        val spannable = SpannableString("Last Position: (${position.posX}, ${position.posY})")
        spannable.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            "Last Position:".length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.textViewLastPosition.text = spannable
    }

    private fun observeViewModel() {
        collectWithLifecycle(viewModel.satelliteDetails) {
            bindDetail(it)
        }

        collectWithLifecycle(viewModel.positionsFlow) {
            updatePosition(it)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        // Possible Memory Leak Reducing
        _binding = null
    }
}