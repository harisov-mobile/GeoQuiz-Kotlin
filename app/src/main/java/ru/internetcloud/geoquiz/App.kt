package ru.internetcloud.geoquiz

import android.app.Application

private const val MAX_CHEAT_AMOUNT = 3 // максимальное количество подсказок

class App: Application() {

    companion object {
        var cheatAmount = MAX_CHEAT_AMOUNT

        fun resetCheatAmount() {
            cheatAmount = MAX_CHEAT_AMOUNT
        }
    }
}