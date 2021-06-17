package ru.internetcloud.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

private const val EXTRA_ANSWER_SHOWN = "ru.internetcloud.geoquiz.answer_shown"
private const val EXTRA_ANSWER_IS_TRUE = "ru.internetcloud.geoquiz.answer_is_true"
private const val KEY_ANSWER_TEXT_VIEW = "ru.internetcloud.geoquiz.answer_text_view"

class CheatActivity : AppCompatActivity() {

    private var answerIsTrue = false
    private var isAnswerShown = false

    private lateinit var showAnswerButton: Button
    private lateinit var answerTextView: TextView
    private lateinit var apiTextView: TextView

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }

        public fun decodeAnswerShownResult(dataIntent: Intent): Boolean {
            return dataIntent.getBooleanExtra(EXTRA_ANSWER_SHOWN, false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        answerTextView = findViewById(R.id.answer_text_view)

        savedInstanceState?.let {
            answerTextView.text = it.getString(KEY_ANSWER_TEXT_VIEW)
        }

        apiTextView = findViewById(R.id.api_text_view)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        apiTextView.text = "API Level " + Build.VERSION.SDK_INT

        showAnswerButton = findViewById(R.id.show_answer_button)

        isAnswerShown = savedInstanceState?.getBoolean(EXTRA_ANSWER_SHOWN, false) ?: false
        showAnswerButton.setEnabled(!isAnswerShown)

        showAnswerButton.setOnClickListener {
            isAnswerShown = true
            showAnswerButton.setEnabled(!isAnswerShown)
            App.cheatAmount--
            val resIdAnswerText = when {
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            answerTextView.setText(resIdAnswerText)
            setAnswerShownResult(isAnswerShown)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val answerString = answerTextView.getText() as String?

        outState.putString(KEY_ANSWER_TEXT_VIEW, answerString)
        outState.putBoolean(EXTRA_ANSWER_SHOWN, isAnswerShown)
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK , data)
    }


}