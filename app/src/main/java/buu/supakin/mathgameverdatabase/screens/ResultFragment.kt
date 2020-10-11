package buu.supakin.mathgameverdatabase.screens

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
import buu.supakin.mathgameverdatabase.R
import buu.supakin.mathgameverdatabase.database.PlayerDatabase
import buu.supakin.mathgameverdatabase.databinding.FragmentResultBinding
import buu.supakin.mathgameverdatabase.models.Score
import buu.supakin.mathgameverdatabase.viewmodelfactories.ResultViewModelFactory
import buu.supakin.mathgameverdatabase.viewmodels.ResultViewModel

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
        val application = requireNotNull(this.activity).application
        val dataSource = PlayerDatabase.getInstance(application).playerDatabaseDao
        viewModelFactory = ResultViewModelFactory(
            dataSource,
            ResultFragmentArgs.fromBundle(requireArguments()).playerId,
            ResultFragmentArgs.fromBundle(requireArguments()).menu,
            ResultFragmentArgs.fromBundle(requireArguments()).result
        )

        viewModel = ViewModelProvider(this, viewModelFactory).get(ResultViewModel::class.java)

        viewModel.eventNext.observe(viewLifecycleOwner, Observer { eventNext ->
            if (eventNext) {
                onNext()
                viewModel.onNextComplete()
            }
        })

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            view?.findNavController()?.navigate(
                ResultFragmentDirections.actionResultFragmentToPlayFragment(
                    viewModel.player.value!!.getPlayerId(),
                    viewModel.menu.value!!
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
                viewModel.player.value!!.getPlayerId(),
                viewModel.menu.value!!
            )
        )
    }


}