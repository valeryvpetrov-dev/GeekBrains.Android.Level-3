package ru.geekbrains.android.level3.valeryvpetrov

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :
    AppCompatActivity(),
    View.OnClickListener, MainContract.View {

    companion object {
        const val SIS_KEY_PRESENTER_ID = "presenter_id";
    }

    private var mPresenter: MainContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restorePresenter(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCounter1.setOnClickListener(this)
        btnCounter2.setOnClickListener(this)
        btnCounter3.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        mPresenter?.bindView(this)
        mPresenter?.restoreViewState()
    }

    override fun onPause() {
        super.onPause()
        mPresenter?.unbindView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        storePresenter(outState)
    }

    override fun onClick(view: View) {
        mPresenter?.incCounter(
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

    private fun storePresenter(outState: Bundle) {
        val presenterId = mPresenter?.let { PresenterManager.storePresenter(it) }
        presenterId?.let { outState.putLong(SIS_KEY_PRESENTER_ID, it) }
    }

    private fun restorePresenter(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            val presenterId = savedInstanceState.getLong(SIS_KEY_PRESENTER_ID)
            mPresenter = PresenterManager.restorePresenter(presenterId)
        } else {
            mPresenter = MainPresenter()
        }
    }
}
