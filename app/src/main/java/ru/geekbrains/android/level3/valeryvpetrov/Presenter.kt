package ru.geekbrains.android.level3.valeryvpetrov

class Presenter(
    var mView: CounterContract.View?,
    val mModel: Model
) : CounterContract.Presenter {

    override fun attachView(view: CounterContract.View) {
        this.mView = view
    }

    override fun detachView() {
        this.mView = null
    }

    override fun restoreViewState() {
        for ((index, value) in mModel.mList.withIndex())
            mView?.updateCounter(index, value)
    }

    override fun incCounter(index: Int) {
        if (index < 0) return

        val newValue = calcNewModelValue(index)
        mModel.setElementValueAtIndex(index, newValue)
        mView?.updateCounter(index, newValue)
    }

    private fun calcNewModelValue(index: Int) = mModel.getElementValueAtIndex(index) + 1

}