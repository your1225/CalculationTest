package com.example.calculationtest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.calculationtest.databinding.LoseFragmentBinding

class LoseFragment : Fragment() {

    companion object {
        fun newInstance() = LoseFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        return inflater.inflate(R.layout.lose_fragment, container, false)

        val viewModel = ViewModelProvider(
            requireActivity(),
            SavedStateViewModelFactory(requireActivity().application, this)
        ).get(MyViewModel::class.java)

        var binding: LoseFragmentBinding =
            DataBindingUtil.inflate<LoseFragmentBinding>(inflater, R.layout.lose_fragment, container, false)
        binding.data = viewModel
        binding.lifecycleOwner = requireActivity()

        binding.button11.setOnClickListener {
            var controller: NavController = Navigation.findNavController(it)
            controller.navigate(R.id.action_loseFragment_to_titleFragment)
        }

//        Toast.makeText( activity, viewModel.currentScore.value.toString(), Toast.LENGTH_LONG).show()
//        Toast.makeText(activity, viewModel.getTestData().value!!.toString(), Toast.LENGTH_LONG).show()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}
