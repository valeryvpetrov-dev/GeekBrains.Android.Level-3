package ru.geekbrains.android.level3.valeryvpetrov

class Presenter(
    val mView: MainView,
    val mModel: Model
) {

    fun onButtonClick(buttonId: Int) {
        when (buttonId) {
            R.id.btnCounter1 -> {
                val newValue = calcNewModelValue(0)
                mModel.setElementValueAtIndex(0, newValue)
                mView.setButtonText(buttonId, newValue)
            }
            R.id.btnCounter2 -> {
                val newValue = calcNewModelValue(1)
                mModel.setElementValueAtIndex(1, newValue)
                mView.setButtonText(buttonId, newValue)
            }
            R.id.btnCounter3 -> {
                val newValue = calcNewModelValue(2)
                mModel.setElementValueAtIndex(2, newValue)
                mView.setButtonText(buttonId, newValue)
            }
        }
    }

    private fun calcNewModelValue(index: Int) = mModel.getElementValueAtIndex(index) + 1

}