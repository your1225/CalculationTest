package com.example.calculationtest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.calculationtest.databinding.WinFragmentBinding

class WinFragment : Fragment() {

    companion object {
        fun newInstance() = WinFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val viewModel = ViewModelProviders.of(requireActivity(), SavedStateViewModelFactory(requireActivity().application, this)).get(MyViewModel::class.java)
//        val viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        val viewModel = ViewModelProvider(
            requireActivity(),
            SavedStateViewModelFactory(requireActivity().application, this)
        ).get(MyViewModel::class.java)
        val binding  = DataBindingUtil.inflate<WinFragmentBinding>(inflater, R.layout.win_fragment, container, false)
        binding.data = viewModel
        binding.lifecycleOwner = requireActivity()

        binding.button10.setOnClickListener {
            var controller = Navigation.findNavController(it)
            controller.navigate(R.id.action_winFragment_to_titleFragment)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
