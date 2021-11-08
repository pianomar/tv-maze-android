package com.omarhezi.ndrive.showdetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.omarhezi.ndrive.R
import com.omarhezi.ndrive.databinding.FragmentDetailsBinding
import com.omarhezi.ndrive.showdetails.core.ShowDetailsViewModel
import com.omarhezi.ndrive.showslist.core.modules.ShowViewData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowDetailsFragment : Fragment() {

    private val viewModel: ShowDetailsViewModel by viewModels()
    lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val show = arguments?.getParcelable<ShowViewData>(SHOW)

        setupAvailableShowInfo(show)

        viewModel.showAliasesStatus.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ShowDetailsViewModel.ShowsAliasesStatus.Success -> {
                    binding.aliasesTxt.text = result.data
                }
                is ShowDetailsViewModel.ShowsAliasesStatus.Error -> {
                    Snackbar.make(view, result.message, Snackbar.LENGTH_LONG).show()
                    binding.aliasesTxt.visibility = View.GONE
                }
            }
        }

        show?.id?.let { viewModel.getShowAliases(it) }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupAvailableShowInfo(show: ShowViewData?) {
        show?.let {

            binding.showTitleDetailsTxt.text = show.name

            // This flag is used because the API returns <p>...</p> structured HTML
            binding.showSummaryDetailsTxt.text = HtmlCompat.fromHtml(show.summary, HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH)

            setupRating(show.rating)

            Glide.with(this)
                .load(show.image)
                .centerCrop()
                .placeholder(R.drawable.no_image_available)
                .into(binding.showPosterDetailsImg)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupRating(
        rating: Double?
    ) {
        if (rating == null) {
            binding.unavailableRatingTxt.visibility = View.VISIBLE
            binding.showRatingBar.visibility =
                View.INVISIBLE // INVISIBLE to keep aliases_txt in place
        } else {
            binding.unavailableRatingTxt.visibility = View.INVISIBLE
            binding.showRatingBar.visibility = View.VISIBLE
            binding.showRatingBar.rating = rating.toFloat()
        }

        binding.showRatingBar.setOnTouchListener { _, _ -> true }
    }

    companion object {
        const val SHOW = "show"
    }
}