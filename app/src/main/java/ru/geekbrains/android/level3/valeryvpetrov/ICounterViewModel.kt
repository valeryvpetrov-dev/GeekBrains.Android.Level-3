package ru.geekbrains.android.level3.valeryvpetrov

import androidx.lifecycle.LiveData

interface ICounterViewModel {

    val counter1: LiveData<Int>

    val counter2: LiveData<Int>

    val counter3: LiveData<Int>

    fun incCounter1()

    fun incCounter2()

    fun incCounter3()

}