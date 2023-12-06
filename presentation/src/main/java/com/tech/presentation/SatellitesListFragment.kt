package com.tech.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tech.core.extensions.collectWithLifecycle
import com.tech.presentation.adapter.SatelliteListAdapter
import com.tech.presentation.databinding.FragmentSatellitesBinding
import com.tech.presentation.state.SatelliteListViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SatellitesListFragment : Fragment() {

    private val viewModel: SatellitesListViewModel by viewModels()

    private var _binding: FragmentSatellitesBinding? = null
    private val binding get() = _binding!!
    private var satelliteAdapter: SatelliteListAdapter? = null
    private lateinit var navController: NavController // handle leak automatically inside navigation component

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSatellitesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        viewModel.loadSatellites()
        bindRecyclerView()
        observeViewModel()
        setupSearchView()
    }

    private fun observeViewModel() {
        collectWithLifecycle(viewModel.satelliteListState) { uiState ->
            when (uiState) {
                is SatelliteListViewState.Loading -> binding.progressBar.visibility = View.VISIBLE
                is SatelliteListViewState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    satelliteAdapter?.submitList(uiState.satellites)
                }

                is SatelliteListViewState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, uiState.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.updateSearchQuery(newText.orEmpty())
                viewModel.startSearch()
                return true
            }
        })
    }

    private fun bindRecyclerView(){
        satelliteAdapter = SatelliteListAdapter(
            satelliteClickListener = { satelliteID ->
                navController.navigate(
                    R.id.satelliteDetailFragment,
                    viewModel.setBundle(satelliteID)
                )
            }
        )
        binding.recyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = satelliteAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Possible Memory Leak Reducing
        _binding = null
        satelliteAdapter = null
    }
}