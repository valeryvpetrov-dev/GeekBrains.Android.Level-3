package ru.geekbrains.android.level3.valeryvpetrov

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface IMainView : MvpView {

    fun updateCounter1(value: Int)

    fun updateCounter2(value: Int)

    fun updateCounter3(value: Int)
}

interface IMainPresenter {

    fun incCounter(index: Int)
}
