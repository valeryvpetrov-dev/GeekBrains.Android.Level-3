package ru.geekbrains.android.level3.valeryvpetrov

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :
    MvpAppCompatActivity(),
    IMainView,
    View.OnClickListener {

    @InjectPresenter
    lateinit var mPresenter: MainPresenter

    @ProvidePresenter
    fun providePresenter() = MainPresenter(Model())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCounter1.setOnClickListener(this)
        btnCounter2.setOnClickListener(this)
        btnCounter3.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        mPresenter.incCounter(
            when (view.id) {    // view does not know about business logic
                R.id.btnCounter1 -> 1
                R.id.btnCounter2 -> 2
                R.id.btnCounter3 -> 3
                else -> -1
            }
        )
    }

    override fun updateCounter1(value: Int) {
        btnCounter1.text = "Counter = $value"
    }

    override fun updateCounter2(value: Int) {
        btnCounter2.text = "Counter = $value"
    }

    override fun updateCounter3(value: Int) {
        btnCounter3.text = "Counter = $value"
    }
}
