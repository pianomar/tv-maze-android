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
import com.omarhezi.ndrive.databinding.FragmentMainBinding
import com.omarhezi.ndrive.showslist.core.ShowsListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: ShowsListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentMainBinding>(inflater, R.layout.fragment_main, container, false)
        binding.searchBox.setOnEditorActionListener { _, _, _ ->
            viewModel.getShowsListByQuery(binding.searchBox.text.toString())
            binding.searchBox.setText("")
            true
        }
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.showsLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it[0].summary, Toast.LENGTH_SHORT).show()
        }
    }

}