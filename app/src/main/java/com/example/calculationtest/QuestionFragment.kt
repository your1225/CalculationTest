package com.example.calculationtest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.calculationtest.databinding.QuestionFragmentBinding
import java.lang.StringBuilder

class QuestionFragment : Fragment() {

    companion object {
        fun newInstance() = QuestionFragment()
    }

    private lateinit var viewModel: MyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return inflater.inflate(R.layout.question_fragment, container, false)
//        viewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        viewModel = ViewModelProvider(
            requireActivity(),
            SavedStateViewModelFactory(requireActivity().application, this)
        ).get(MyViewModel::class.java)
        viewModel.currentScore.value = 0
        viewModel.generator()
        var binding: QuestionFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.question_fragment, container, false)
        binding.data = viewModel
        binding.lifecycleOwner = requireActivity()

        var builder: StringBuilder = StringBuilder()

        var listener: View.OnClickListener = View.OnClickListener {
            when (it.id) {
                R.id.button0 -> builder.append("0")
                R.id.button1 -> builder.append("1")
                R.id.button2 -> builder.append("2")
                R.id.button3 -> builder.append("3")
                R.id.button4 -> builder.append("4")
                R.id.button5 -> builder.append("5")
                R.id.button6 -> builder.append("6")
                R.id.button7 -> builder.append("7")
                R.id.button8 -> builder.append("8")
                R.id.button9 -> builder.append("9")
                R.id.buttonClear -> builder.clear()
            }

            if (builder.isEmpty())
                binding.textView9.text = getString(R.string.input_indicator)
            else
                binding.textView9.text = builder.toString()
        }

        binding.button0.setOnClickListener(listener)
        binding.button1.setOnClickListener(listener)
        binding.button2.setOnClickListener(listener)
        binding.button3.setOnClickListener(listener)
        binding.button4.setOnClickListener(listener)
        binding.button5.setOnClickListener(listener)
        binding.button6.setOnClickListener(listener)
        binding.button7.setOnClickListener(listener)
        binding.button8.setOnClickListener(listener)
        binding.button9.setOnClickListener(listener)
        binding.buttonClear.setOnClickListener(listener)

        binding.buttonSubmit.setOnClickListener {
            if (builder.toString().toInt() == viewModel.keyAnswer.value) {
                viewModel.answerCorrect()
                builder.clear()
                binding.textView9.text = getText(R.string.answer_current_message)
//                builder.append(getString(R.string.answer_current_message))
            } else {
                var controller: NavController = Navigation.findNavController(it)

                if (viewModel.win_flag == true) {
                    controller.navigate(R.id.action_questionFragment_to_winFragment)
                    viewModel.win_flag = false
                    viewModel.save()
                } else {
                    controller.navigate(R.id.action_questionFragment_to_loseFragment)
                }
            }
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
