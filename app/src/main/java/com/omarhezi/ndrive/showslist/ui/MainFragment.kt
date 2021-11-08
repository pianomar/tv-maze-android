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
    private lateinit var adapter: ShowsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.lifecycleOwner = this

        binding.searchBox.setOnEditorActionListener { _, _, _ ->
            binding.showsListProgress.visibility = View.VISIBLE
            viewModel.getShowsListByQuery(binding.searchBox.text.toString())
            binding.searchBox.setText("")
            true
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ShowsAdapter {
            findNavController().navigate(
                R.id.action_details_fragment,
                bundleOf(ShowDetailsFragment.SHOW to it)
            )
        }

        viewModel.showsLiveData.observe(viewLifecycleOwner) { result ->
            binding.showsListProgress.visibility = View.GONE

            when (result) {
                is ShowsListViewModel.ShowsListStatus.Success -> {
                    binding.showsList.adapter = adapter
                    adapter.items = result.data
                    adapter.notifyDataSetChanged()
                }
                is ShowsListViewModel.ShowsListStatus.Error -> {
                    if (result.message != null) {
                        Snackbar.make(
                            view,
                            result.message,
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

}