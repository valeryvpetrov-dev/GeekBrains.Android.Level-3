package ru.geekbrains.android.level3.valeryvpetrov

import java.util.*

class Model(
    val mList: MutableList<Int> = listOf(0, 0, 0).toMutableList()
) : Observable() {

    fun getElementValueAtIndex(index: Int) = mList[index]

    fun setElementValueAtIndex(index: Int, value: Int) {
        mList[index] = value
    }
}