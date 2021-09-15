package com.arcanesecurity.resumofinal.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.arcanesecurity.resumofinal.R
import com.arcanesecurity.resumofinal.adapter.FeedImageAdapter
import com.arcanesecurity.resumofinal.databinding.FeedFragmentBinding
import com.arcanesecurity.resumofinal.model.Image
import com.arcanesecurity.resumofinal.view_model.FeedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : Fragment(R.layout.feed_fragment) {

    private lateinit var viewModel: FeedViewModel
    private lateinit var binding: FeedFragmentBinding
    private val feedAdapter = FeedImageAdapter()
    private val observerImages = Observer<List<Image>> {
        feedAdapter.update(it)
    }
    private val observerPages = Observer<Int> {
        viewModel.fetchImages(page = it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FeedFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)

        viewModel.images.observe(viewLifecycleOwner, observerImages)
        viewModel.page.observe(viewLifecycleOwner, observerPages)

        setupRecyclerView()

        binding.buttonNextPage.setOnClickListener {
            viewModel.nextPage()
        }
    }

    fun setupRecyclerView() = with(binding.feedRecyclerView) {
        adapter = feedAdapter
        layoutManager = GridLayoutManager(requireContext(), 2)
        viewModel.nextPage()
    }

}