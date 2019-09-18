package ru.geekbrains.android.level3.valeryvpetrov

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var eventBus: EventBus<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        eventBus = EventBus()

        val observable1 = RxTextView.textChangeEvents(input)
            .skipInitialValue()
            .debounce(200, TimeUnit.MILLISECONDS)
            .map { "to subscriber1: ${it.text()}" }
            .observeOn(AndroidSchedulers.mainThread())
        val observable2 = Observable
            .interval(0, 500, TimeUnit.MILLISECONDS)
            .map { "to subscriber2: $it" }
            .observeOn(AndroidSchedulers.mainThread())

        eventBus.addObservables(observable1, observable2)

        eventBus.subscribe(Consumer {
            if (it.startsWith("to subscriber1"))
                subscriber1.text = it
        })
        eventBus.subscribe(Consumer {
            if (it.startsWith("to subscriber2"))
                subscriber2.text = it
        })
    }

    override fun onStop() {
        super.onStop()
        eventBus.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        eventBus.dispose()
    }
}
