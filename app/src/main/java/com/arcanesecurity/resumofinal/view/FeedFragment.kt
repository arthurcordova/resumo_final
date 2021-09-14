package com.arcanesecurity.resumofinal.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.arcanesecurity.resumofinal.R
import com.arcanesecurity.resumofinal.databinding.FeedFragmentBinding
import com.arcanesecurity.resumofinal.view_model.FeedViewModel

class FeedFragment : Fragment(R.layout.feed_fragment) {

    companion object {
        fun newInstance() = FeedFragment()
    }

    private lateinit var viewModel: FeedViewModel
    private lateinit var binding: FeedFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FeedFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)

        binding.buttonGoToDetails.setOnClickListener {
            findNavController().navigate(R.id.action_feedFragment_to_feedDetailFragment)
        }
    }

}