package ru.internetcloud.geoquiz

import androidx.annotation.StringRes

data class Question(@StringRes var textResId: Int, val answer: Boolean, var isCheater: Boolean = false)