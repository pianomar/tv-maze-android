package com.omarhezi.ndrive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.omarhezi.ndrive.databinding.FragmentMainBinding
import com.omarhezi.ndrive.showslist.core.ShowsListViewModel
import com.omarhezi.ndrive.showslist.ui.ShowsAdapter
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

        val adapter = ShowsAdapter()
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