package buu.supakin.mathgameverviewmodel.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import buu.supakin.mathgameverviewmodel.R
import buu.supakin.mathgameverviewmodel.databinding.FragmentPlayBinding
import buu.supakin.mathgameverviewmodel.viewmodelfactories.GameViewModelFactory
import buu.supakin.mathgameverviewmodel.viewmodels.GameViewModel
import buu.supakin.mathgameverviewmodel.models.Question
import buu.supakin.mathgameverviewmodel.models.Score
import buu.supakin.mathgameverviewmodel.viewmodelfactories.PlayViewModelFactory
import buu.supakin.mathgameverviewmodel.viewmodels.PlayViewModel


class PlayFragment : Fragment() {
    private var btnArray = arrayListOf<Button>()
    private lateinit var binding: FragmentPlayBinding
    private lateinit var viewModel: PlayViewModel
    private lateinit var  viewModelFactory: PlayViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_play, container, false)

        viewModelFactory = PlayViewModelFactory(
            Score(
                PlayFragmentArgs.fromBundle(requireArguments()).scoreCorrect,
                PlayFragmentArgs.fromBundle(requireArguments()).scoreInCorrect
            ),
            PlayFragmentArgs.fromBundle(requireArguments()).menu
        )

        viewModel = ViewModelProvider(this, viewModelFactory).get(PlayViewModel::class.java)

        viewModel.eventNext.observe(viewLifecycleOwner, Observer { eventNext->
            if (eventNext) onNext()
        })

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            view?.findNavController()?.navigate(
                PlayFragmentDirections.actionPlayFragmentToMenuFragment(
                    viewModel!!.score?.value?.scoreCorrect?:0,
                    viewModel!!.score?.value?.scoreInCorrect?:0,
                )
            )
        }

        binding.playViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

//        this.play()

        return binding.root
    }

    private fun onNext () {
        view?.findNavController()?.navigate(
            PlayFragmentDirections.actionPlayFragmentToResultFragment(
                viewModel!!.score.value?.scoreCorrect?:0,
                viewModel!!.score.value?.scoreInCorrect?:0,
                viewModel!!.menu.value?:0,
                viewModel!!.getResult()?: false
            )
        )
    }

//    private fun play() {
//        this.setAllButton()
//    }


//    private fun setAllButton () {
//        binding.apply{
//            btnArray = arrayListOf(
//                btnAnswer1,
//                btnAnswer2,
//                btnAnswer3,
//                btnAnswer4
//            )
//        }
//        for ((index, btn) in btnArray.withIndex()) {
//            btn.text = question!!.answerArray[index].toString()
//            btn.setOnClickListener { this.getResult(btn.text.toString().toInt()) }
//        }
//    }



//    private fun getResult(answer: Int)  {
//        val result = if (question!!.getResult(answer)) {
//            gameViewModel!!.onCorrect()
//            true
//        } else {
//            gameViewModel!!.onInCorrect()
//            false
//        }
//
//        view?.findNavController()?.navigate(
//            PlayFragmentDirections.actionPlayFragmentToResultFragment(
//                gameViewModel.correctScore.value?:0,
//                gameViewModel.inCorrectScore.value?:0,
//                question.menu,
//                result
//            )
//        )
//    }


}