package ru.internetcloud.geoquiz

import androidx.lifecycle.ViewModel

class QuizViewModel: ViewModel() {

    var currentIndex = 0


    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    var currentQuestionIsCheater: Boolean
        get() = questionBank[currentIndex].isCheater
        set(value) { questionBank[currentIndex].isCheater = value }

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrevious() {
        currentIndex = (currentIndex - 1)

        if (currentIndex < 0 ) {
            currentIndex = questionBank.size - 1
        }
    }

}