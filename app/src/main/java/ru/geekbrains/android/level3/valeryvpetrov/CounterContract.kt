package ru.geekbrains.android.level3.valeryvpetrov

interface CounterContract {

    interface View {
        fun updateCounter(index: Int, value: Int)

        fun attachPresenter()

        fun detachPresenter()
    }

    interface Presenter {
        fun incCounter(index: Int)

        fun attachView(view: View)

        fun detachView()

        fun restoreViewState()
    }

}