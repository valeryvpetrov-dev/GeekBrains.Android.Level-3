package ru.geekbrains.android.level3.valeryvpetrov

class Presenter(
    val mView: CounterContract.View,
    val mModel: Model
) : CounterContract.Presenter {

    override fun incCounter(index: Int) {
        if (index < 0) return

        val newValue = calcNewModelValue(index)
        mModel.setElementValueAtIndex(index, newValue)
        mView.updateCounter(index, newValue)
    }

    private fun calcNewModelValue(index: Int) = mModel.getElementValueAtIndex(index) + 1

}