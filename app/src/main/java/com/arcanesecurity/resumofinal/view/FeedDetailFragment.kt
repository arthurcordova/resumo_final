package com.arcanesecurity.resumofinal.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.arcanesecurity.resumofinal.R
import com.arcanesecurity.resumofinal.databinding.FeedDetailFragmentBinding
import com.arcanesecurity.resumofinal.view_model.FeedDetailViewModel

class FeedDetailFragment : Fragment(R.layout.feed_detail_fragment) {

    private lateinit var viewModel: FeedDetailViewModel
    private lateinit var binding: FeedDetailFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FeedDetailFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(FeedDetailViewModel::class.java)

        binding.buttonClose.setOnClickListener {
            findNavController().apply {
                navigate(R.id.action_feedDetailFragment_to_accountFragment)
            }
        }


    }

}