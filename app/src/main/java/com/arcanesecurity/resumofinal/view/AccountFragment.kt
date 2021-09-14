package com.arcanesecurity.resumofinal.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arcanesecurity.resumofinal.R
import com.arcanesecurity.resumofinal.databinding.AccountFragmentBinding
import com.arcanesecurity.resumofinal.view_model.AccountViewModel

class AccountFragment : Fragment(R.layout.account_fragment) {

    companion object {
        fun newInstance() = AccountFragment()
    }

    private lateinit var viewModel: AccountViewModel
    private lateinit var binding: AccountFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AccountFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(AccountViewModel::class.java)

    }

}