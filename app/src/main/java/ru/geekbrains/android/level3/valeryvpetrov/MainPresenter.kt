package ru.geekbrains.android.level3.valeryvpetrov

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class MainPresenter(
    val mModel: Model
) : MvpPresenter<IMainView>(), IMainPresenter {

    override fun incCounter(index: Int) {
        val _index = index - 1  // converts view input to model format

        if (_index < 0) return  // validates converted input

        val newValue = calcNewModelValue(_index)
        mModel.setElementValueAtIndex(_index, newValue)
        when (_index) {
            0 -> viewState.updateCounter1(newValue)
            1 -> viewState.updateCounter2(newValue)
            2 -> viewState.updateCounter3(newValue)
        }
    }

    private fun calcNewModelValue(index: Int) = mModel.getElementValueAtIndex(index) + 1

}