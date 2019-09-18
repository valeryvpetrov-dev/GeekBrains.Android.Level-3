package ru.geekbrains.android.level3.valeryvpetrov

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject

class EventBus<T> {

    private val compositeDisposable = CompositeDisposable()
    private val subject: PublishSubject<T> =
        PublishSubject.create<T>() // choose subject type you need

    private var mObservable: Observable<T>? = null

    fun addObservables(observable1: Observable<T>, observable2: Observable<T>) {
        Observable.merge(observable1, observable2)
            .subscribe(subject)
        mObservable = unionObservables(observable1, observable2)
        mObservable!!.subscribe(subject)
    }

    private fun unionObservables(
        observable1: Observable<T>,
        observable2: Observable<T>
    ): Observable<T> {
        // manage observables interaction
        return Observable.merge(observable1, observable2)
    }

    fun subscribe(consumer: Consumer<T>) {
        compositeDisposable.add(subject.subscribe(consumer))
    }

    fun clear() {
        compositeDisposable.clear()
    }

    fun dispose() {
        compositeDisposable.dispose()
    }


}