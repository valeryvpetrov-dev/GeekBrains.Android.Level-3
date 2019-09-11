package ru.geekbrains.android.level3.valeryvpetrov

class Presenter(
    val mView: MainView,
    val mModel: Model
) {

    fun onButtonClick(index: Int) {
        if (index < 0) return

        val newValue = calcNewModelValue(index)
        mModel.setElementValueAtIndex(index, newValue)
        mView.setButtonText(index, newValue)
    }

    private fun calcNewModelValue(index: Int) = mModel.getElementValueAtIndex(index) + 1

}