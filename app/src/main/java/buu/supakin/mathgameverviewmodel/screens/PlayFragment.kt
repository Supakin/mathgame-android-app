package buu.supakin.mathgameverviewmodel.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import buu.supakin.mathgameverviewmodel.PlayFragmentArgs
import buu.supakin.mathgameverviewmodel.PlayFragmentDirections
import buu.supakin.mathgameverviewmodel.R
import buu.supakin.mathgameverviewmodel.databinding.FragmentPlayBinding
import kotlin.random.Random

class PlayFragment : Fragment() {
    private var realAnswer = 0
    private var btnArray = arrayListOf<Button>()
    private var menu = 0
    private var scoreCorrect = 0
    private var scoreInCorrect = 0
    private var result = false

    private lateinit var binding: FragmentPlayBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_play, container, false)

        menu = PlayFragmentArgs.fromBundle(requireArguments()).menu
        scoreCorrect = PlayFragmentArgs.fromBundle(requireArguments()).scoreCorrect
        scoreInCorrect = PlayFragmentArgs.fromBundle(requireArguments()).scoreInCorrect

        binding.apply{
            btnArray = arrayListOf(
                btnAnswer1,
                btnAnswer2,
                btnAnswer3,
                btnAnswer4
            )
        }
        this.play()
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            view?.findNavController()?.navigate(
                PlayFragmentDirections.actionPlayFragmentToMenuFragment(
                    scoreCorrect,
                    scoreInCorrect
                )
            )
        }



        return binding.root
    }

    private fun play() {
        realAnswer = setQuestionAndAnswer()
        this.setGuideLine()
        this.setButtonAction()
    }

    private fun setQuestionAndAnswer(): Int {
        var num1 = Random.nextInt(0, 10)
        var num2 = 0
        if (menu == 4) {
            while (true) {
                val tempDummy = Random.nextInt(1, 10)
                if (num1 % tempDummy == 0) {
                    num2 = tempDummy
                    break
                }
            }

        } else {
            num2 = Random.nextInt(0, 10)
        }
        val answer = when (menu) {
            1 -> num1 + num2
            2 -> num1 - num2
            3 -> num1 * num2
            else -> num1 / num2
        }
        this.setTextQuestion(num1, num2)
        this.setTextAnswer(answer)
        return answer
    }

    private fun setTextQuestion(number1: Int, number2: Int) {
        binding.apply {
            txtNumber1.text = number1.toString()
            txtNumber2.text = number2.toString()
            when (menu) {
                1 -> txtOperator.text = getString(R.string.operator_plus)
                2 -> txtOperator.text = getString(R.string.operator_minus)
                3 -> txtOperator.text = getString(R.string.operator_multiplied)
                else -> txtOperator.text = getString(R.string.operator_divide)
            }
        }

    }

    private fun setTextAnswer(answer: Int) {
        val answerIndex = Random.nextInt(0, 3)
        val answerArray = arrayListOf<Int>()
        answerArray.add(answer)
        for ((index, btn) in btnArray.withIndex()) {
            if (answerIndex == index) {
                btn.text = answer.toString()
            } else {
                val anotherAnswer = this.getAnotherAnswer(answerArray)
                answerArray.add(anotherAnswer)
                btn.text = anotherAnswer.toString()
            }
        }
    }

    private fun getAnotherAnswer(answerArray: ArrayList<Int>): Int {
        while (true) {
            val anotherAnswer = when (menu) {
                1 -> Random.nextInt(0, 20)
                2 -> Random.nextInt(-10, 10)
                3 -> Random.nextInt(0, 100)
                else -> Random.nextInt(0, 10)
            }
            if (!answerArray.contains(anotherAnswer)) return anotherAnswer
            else continue
        }
    }

    private fun getResult(answer: Int)  {
        result = if (answer == realAnswer) {
            scoreCorrect++
            true
        } else {
            scoreInCorrect++
            false
        }

        view?.findNavController()?.navigate(
            PlayFragmentDirections.actionPlayFragmentToResultFragment(
                scoreCorrect,
                scoreInCorrect,
                menu,
                result
            )
        )
    }

    private fun setButtonAction() {
        for (btn in btnArray) btn.setOnClickListener { this.getResult(btn.text.toString().toInt()) }
    }

    private fun setGuideLine() {
        binding.apply {
            txtGuideLine.text = when (menu) {
                1 -> getString(R.string.caption_play_plus)
                2 -> getString(R.string.caption_play_minus)
                3 -> getString(R.string.caption_play_multiplied)
                else -> getString(R.string.caption_play_divide)
            }
        }

    }

}