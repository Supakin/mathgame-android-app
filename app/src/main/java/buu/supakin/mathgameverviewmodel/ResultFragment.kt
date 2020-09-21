package buu.supakin.mathgameverviewmodel

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import buu.supakin.mathgameverviewmodel.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    private var result: Boolean = false
    private var menu: Int = 0
    private var scoreCorrect = 0
    private var scoreInCorrect = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false)
        menu = ResultFragmentArgs.fromBundle(requireArguments()).menu
        scoreCorrect = ResultFragmentArgs.fromBundle(requireArguments()).scoreCorrect
        scoreInCorrect = ResultFragmentArgs.fromBundle(requireArguments()).scoreInCorrect
        result = ResultFragmentArgs.fromBundle(requireArguments()).result
        this.init()
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            view?.findNavController()?.navigate(ResultFragmentDirections.actionResultFragmentToPlayFragment(scoreCorrect, scoreInCorrect, menu))
        }
        return binding.root
    }

    @SuppressLint("ResourceAsColor")
    private fun init() {
        binding.apply {
            clResult.setBackgroundColor(if (result) resources.getColor(R.color.colorSuccess)  else resources.getColor(R.color.colorDanger))
            btnNext.setBackgroundResource(if (result) R.drawable.btn_rounded_success else R.drawable.btn_rounded_danger)
            txtSummaryScore.text = getSummaryScore(if (result) scoreCorrect else scoreInCorrect)
            imgResult.setImageResource(if (result) R.drawable.correct else R.drawable.incorrect)

            btnNext.setOnClickListener {
                view?.findNavController()?.navigate(ResultFragmentDirections.actionResultFragmentToPlayFragment(scoreCorrect, scoreInCorrect, menu))
            }
        }

    }

    private fun getSummaryScore (score: Int): String {
        return if (result) getString(R.string.total_correct, score) else getString(R.string.total_incorrect, score)
    }

}