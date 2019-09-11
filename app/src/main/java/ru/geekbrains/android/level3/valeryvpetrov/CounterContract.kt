package ru.geekbrains.android.level3.valeryvpetrov

interface CounterContract {

    interface View {
        fun updateCounter(index: Int, value: Int)
    }

    interface Presenter {
        fun incCounter(index: Int)
    }

}