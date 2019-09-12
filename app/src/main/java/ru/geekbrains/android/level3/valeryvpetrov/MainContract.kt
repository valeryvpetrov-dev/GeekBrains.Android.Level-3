package ru.geekbrains.android.level3.valeryvpetrov

interface MainContract {

    interface View {

        fun updateCounter(index: Int, value: Int)
    }

    interface Presenter {

        fun incCounter(index: Int)

        fun bindView(view: View)

        fun unbindView()

        fun restoreViewState()
    }

}