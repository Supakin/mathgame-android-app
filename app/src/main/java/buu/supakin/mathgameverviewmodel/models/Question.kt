package buu.supakin.mathgameverviewmodel.models

import kotlin.random.Random

class Question (menu: Int) {
    var menu = 0
    private var realAnswer = 0
    var num1 = 0
    var num2 = 0
    var operator = ""
    var guideline = ""
    var answerArray: ArrayList<Int>

    init {
        this.menu = menu
        this.num1 = this.initNum1()
        this.num2 = this.initNum2()
        this.realAnswer = this.initRealAnswer(this.num1, this.num2)
        this.answerArray = this.initAnswerArray()
        this.operator = this.initOperator()
        this.guideline = this.initGuideLine()
    }

    private fun initNum1 () : Int {
        return Random.nextInt(0, 10)
    }

    private fun initNum2 () : Int {
        var num = 0
        if (menu == 4) {
            while (true) {
                val tempDummy = Random.nextInt(1, 10)
                if (num1 % tempDummy == 0) {
                    num = tempDummy
                    break
                }
            }

        } else {
            num = Random.nextInt(0, 10)
        }

        return num
    }

    private fun initRealAnswer(num1: Int, num2: Int): Int {
        return when (menu) {
            1 -> num1 + num2
            2 -> num1 - num2
            3 -> num1 * num2
            else -> num1 / num2
        }
    }

    private fun initOperator (): String {
        return when (menu) {
            1 -> "+"
            2 -> "-"
            3 -> "*"
            else -> "/"
        }
    }

    private fun initGuideLine (): String {
        return when (menu) {
            1 -> "ผลบวกของโจทย์ด้านบนคือเท่าไหร่กันนะ ?"
            2 -> "ผลลบของโจทย์ด้านบนคือเท่าไหร่กันนะ ?"
            3 -> "ผลคูณของโจทย์ด้านบนคือเท่าไหร่กันนะ ?"
            else -> "ผลหารของโจทย์ด้านบนคือเท่าไหร่กันนะ ?"
        }
    }

    private fun initAnswerArray () : ArrayList<Int> {
        val answerIndex = Random.nextInt(0, 3)
        val arrayAnswerDummy = arrayListOf<Int>()
        for (index in 0..3) {
            if (index == answerIndex) {
                arrayAnswerDummy.add(this.realAnswer)
            } else {
                arrayAnswerDummy.add(this.initAnotherAnswer(arrayAnswerDummy))
            }
        }

        return arrayAnswerDummy
    }

    private fun initAnotherAnswer(array: ArrayList<Int>): Int {
        while (true) {
            val anotherAnswer = when (menu) {
                1 -> Random.nextInt(0, 20)
                2 -> Random.nextInt(-10, 10)
                3 -> Random.nextInt(0, 100)
                else -> Random.nextInt(0, 10)
            }

            if (!array.contains(anotherAnswer) && anotherAnswer != this.realAnswer) return anotherAnswer
            else continue
        }
    }

    fun getResult (answer: Int): Boolean {
        return answer == this.realAnswer
    }
}