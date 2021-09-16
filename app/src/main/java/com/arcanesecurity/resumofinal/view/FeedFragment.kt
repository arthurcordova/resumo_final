package com.arcanesecurity.resumofinal.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.arcanesecurity.resumofinal.R
import com.arcanesecurity.resumofinal.adapter.FeedImageAdapter
import com.arcanesecurity.resumofinal.adapter.HeaderAdapter
import com.arcanesecurity.resumofinal.databinding.FeedFragmentBinding
import com.arcanesecurity.resumofinal.model.Image
import com.arcanesecurity.resumofinal.services.NotificationHandler
import com.arcanesecurity.resumofinal.view_model.FeedViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FeedFragment : Fragment(R.layout.feed_fragment) {

    @Inject
    lateinit var notificationHandler: NotificationHandler

    private lateinit var viewModel: FeedViewModel
    private lateinit var binding: FeedFragmentBinding
    private val feedAdapter = FeedImageAdapter() {
        FeedFragmentDirections.actionFeedFragmentToFeedDetailFragment(it).apply {
            findNavController().navigate(this)
        }
    }
    var clearList = false
    private val searchAdapter = HeaderAdapter() {
        clearList = true
        viewModel.searchFor(it)
    }
    private val observerImages = Observer<List<Image>> {
        feedAdapter.update(it, clearList)
    }
    private val observerPages = Observer<Int> {
        viewModel.fetchImages(page = it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FeedFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)

        (requireActivity() as? AppCompatActivity)?.supportActionBar?.show()

        viewModel.images.observe(viewLifecycleOwner, observerImages)
        viewModel.page.observe(viewLifecycleOwner, observerPages)

        binding.buttonNextPage.setOnClickListener {
            clearList = false
            viewModel.nextPage()
        }

        binding.buttonShowNotification.setOnClickListener {
            showNotification()
        }

        setupRecyclerView()
    }

    fun setupRecyclerView() = with(binding.feedRecyclerView) {
        adapter = ConcatAdapter(searchAdapter, feedAdapter)
        layoutManager = GridLayoutManager(requireContext(), 4).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (position == 0) 4 else 1
                }
            }
        }
        viewModel.nextPage()
    }

    fun showNotification() {
        notificationHandler.createNotification("Notificação", "Quero comprar um camelo").run {
            val notificationManager = NotificationManagerCompat.from(requireContext())
            notificationManager.notify(1, this)
        }
    }


}