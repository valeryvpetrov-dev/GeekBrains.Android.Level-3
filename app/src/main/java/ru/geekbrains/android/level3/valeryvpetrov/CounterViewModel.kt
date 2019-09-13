package ru.geekbrains.android.level3.valeryvpetrov

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel(
    val mModel: Model = Model()
) : ViewModel(), ICounterViewModel {

    private val _counter1 = MutableLiveData(0)
    private val _counter2 = MutableLiveData(0)
    private val _counter3 = MutableLiveData(0)

    override val counter1: LiveData<Int> = _counter1
    override val counter2: LiveData<Int> = _counter2
    override val counter3: LiveData<Int> = _counter3

    override fun incCounter1() {
        val newValue = calcNewModelValue(0)
        mModel.setElementValueAtIndex(0, newValue)
        _counter1.value = newValue
    }

    override fun incCounter2() {
        val newValue = calcNewModelValue(1)
        mModel.setElementValueAtIndex(1, newValue)
        _counter2.value = newValue
    }

    override fun incCounter3() {
        val newValue = calcNewModelValue(2)
        mModel.setElementValueAtIndex(2, newValue)
        _counter3.value = newValue
    }

    private fun calcNewModelValue(index: Int) = mModel.getElementValueAtIndex(index) + 1

}