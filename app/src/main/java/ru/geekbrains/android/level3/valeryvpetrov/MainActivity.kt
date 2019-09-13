package ru.geekbrains.android.level3.valeryvpetrov

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :
    AppCompatActivity(),
    View.OnClickListener {

    private lateinit var mCounterViewModel: ICounterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mCounterViewModel = ViewModelProviders.of(this).get(CounterViewModel::class.java)

        btnCounter1.setOnClickListener(this)
        btnCounter2.setOnClickListener(this)
        btnCounter3.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()

        mCounterViewModel.counter1.observe(this, Observer {
            btnCounter1.text = "Counter = $it"
        })
        mCounterViewModel.counter2.observe(this, Observer {
            btnCounter2.text = "Counter = $it"
        })
        mCounterViewModel.counter3.observe(this, Observer {
            btnCounter3.text = "Counter = $it"
        })
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnCounter1 -> mCounterViewModel.incCounter1()
            R.id.btnCounter2 -> mCounterViewModel.incCounter2()
            R.id.btnCounter3 -> mCounterViewModel.incCounter3()
        }
    }

}
