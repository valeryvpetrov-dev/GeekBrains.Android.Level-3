package ru.geekbrains.android.level3.valeryvpetrov

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :
    AppCompatActivity(),
    View.OnClickListener, CounterContract.View {

    private val mPresenter: CounterContract.Presenter by lazy {
        Presenter(this, Model())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCounter1.setOnClickListener(this)
        btnCounter2.setOnClickListener(this)
        btnCounter3.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        mPresenter.incCounter(
            when (view.id) {
                R.id.btnCounter1 -> 0
                R.id.btnCounter2 -> 1
                R.id.btnCounter3 -> 2
                else -> -1
            }
        )
    }

    override fun updateCounter(index: Int, value: Int) {
        when (index) {
            0 -> btnCounter1.text = "Counter = $value"
            1 -> btnCounter2.text = "Counter = $value"
            2 -> btnCounter3.text = "Counter = $value"
        }
    }
}
