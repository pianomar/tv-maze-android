package com.omarhezi.ndrive.showslist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.omarhezi.ndrive.R
import com.omarhezi.ndrive.databinding.FragmentMainBinding
import com.omarhezi.ndrive.showdetails.ShowDetailsFragment
import com.omarhezi.ndrive.showslist.core.ShowsListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: ShowsListViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.lifecycleOwner = this

        binding.searchBox.setOnEditorActionListener { _, _, _ ->
            viewModel.getShowsListByQuery(binding.searchBox.text.toString())
            binding.searchBox.setText("")
            true
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ShowsAdapter {
            findNavController().navigate(R.id.action_details_fragment, bundleOf(ShowDetailsFragment.SHOW to it))
        }

        binding.showsList.adapter = adapter

        viewModel.showsLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ShowsListViewModel.ShowsListStatus.Success -> {
                    if (result.data.isNotEmpty()) {
                        adapter.items = result.data
                        adapter.notifyDataSetChanged()
                    }
                }
                is ShowsListViewModel.ShowsListStatus.Error -> Snackbar.make(view, result.message, Snackbar.LENGTH_LONG).show()
            }
        }
    }

}