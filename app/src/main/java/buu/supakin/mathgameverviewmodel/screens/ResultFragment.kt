package buu.supakin.mathgameverviewmodel.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import buu.supakin.mathgameverviewmodel.R
import buu.supakin.mathgameverviewmodel.databinding.FragmentResultBinding
import buu.supakin.mathgameverviewmodel.models.Score
import buu.supakin.mathgameverviewmodel.viewmodelfactories.ResultViewModelFactory
import buu.supakin.mathgameverviewmodel.viewmodels.ResultViewModel

class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    private lateinit var viewModel: ResultViewModel
    private lateinit var  viewModelFactory: ResultViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false)
        viewModelFactory = ResultViewModelFactory(
            Score(
                ResultFragmentArgs.fromBundle(requireArguments()).scoreCorrect,
                ResultFragmentArgs.fromBundle(requireArguments()).scoreInCorrect,
            ),
            ResultFragmentArgs.fromBundle(requireArguments()).menu,
            ResultFragmentArgs.fromBundle(requireArguments()).result
        )

        viewModel = ViewModelProvider(this, viewModelFactory).get(ResultViewModel::class.java)

        viewModel.eventNext.observe(viewLifecycleOwner, Observer { eventNext ->
            if (eventNext) onNext()
        })

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            view?.findNavController()?.navigate(
                ResultFragmentDirections.actionResultFragmentToPlayFragment(
                    viewModel!!.score.value?.scoreCorrect?:0,
                    viewModel!!.score.value?.scoreInCorrect?:0,
                    viewModel!!.menu.value?:0
                )
            )
        }

        binding.resultViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        this.init()
        return binding.root
    }

    private fun init() {
        this.setBackgroundColor()
        this.setResourceBackgroundColor()
        this.setImageResult()
    }

    private fun setBackgroundColor () {
        binding.clResult.setBackgroundColor(
            if (viewModel!!.result.value != false) resources.getColor(R.color.colorSuccess)
            else resources.getColor(R.color.colorDanger)
        )
    }

    private fun setResourceBackgroundColor () {
        binding.btnNext.setBackgroundResource(if (viewModel!!.result.value != false) R.drawable.btn_rounded_success else R.drawable.btn_rounded_danger)
    }

    private fun setImageResult () {
        binding.imgResult.setImageResource(if (viewModel!!.result.value != false) R.drawable.correct else R.drawable.incorrect)
    }

    private fun onNext () {
        view?.findNavController()?.navigate(
            ResultFragmentDirections.actionResultFragmentToPlayFragment(
                viewModel!!.score.value?.scoreCorrect?:0,
                viewModel!!.score.value?.scoreInCorrect?:0,
                viewModel!!.menu.value?:0
            )
        )
    }


}