package ru.geekbrains.android.level3.valeryvpetrov

import java.lang.ref.WeakReference

class MainPresenter(
    private val mModel: Model = Model()
) : MainContract.Presenter {

    private lateinit var mViewReference: WeakReference<MainContract.View>

    private fun getView() = mViewReference.get()

    override fun incCounter(index: Int) {
        if (index < 0) return

        val newValue = calcNewModelValue(index)
        mModel.setElementValueAtIndex(index, newValue)
        getView()?.updateCounter(index, newValue)
    }

    override fun bindView(view: MainContract.View) {
        this.mViewReference = WeakReference(view)
    }

    override fun unbindView() {
        var view = getView()
        view = null
    }

    override fun restoreViewState() {
        for ((index, value) in mModel.mList.withIndex())
            getView()?.updateCounter(index, value)
    }

    private fun calcNewModelValue(index: Int) = mModel.getElementValueAtIndex(index) + 1

}