package ru.geekbrains.android.level3.valeryvpetrov

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :
    AppCompatActivity(),
    View.OnClickListener, MainView {

    private val mPresenter: Presenter by lazy {
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
        mPresenter.onButtonClick(view.id)
    }

    override fun setButtonText(buttonId: Int, value: Int) {
        when (buttonId) {
            R.id.btnCounter1 -> btnCounter1.text = "Counter = $value"
            R.id.btnCounter2 -> btnCounter2.text = "Counter = $value"
            R.id.btnCounter3 -> btnCounter3.text = "Counter = $value"
        }
    }
}
