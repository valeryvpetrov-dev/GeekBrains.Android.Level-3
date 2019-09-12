package ru.geekbrains.android.level3.valeryvpetrov

import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicLong

object PresenterManager {

    private val mCurrentId: AtomicLong = AtomicLong()

    private val mPresentersCache: Cache<Long, MainContract.Presenter> =
        CacheBuilder.newBuilder()
            .maximumSize(10)
            .expireAfterWrite(30, TimeUnit.SECONDS)
            .build()

    fun storePresenter(presenter: MainContract.Presenter): Long {
        val presenterId = mCurrentId.incrementAndGet()
        mPresentersCache.put(presenterId, presenter)
        return presenterId
    }

    fun restorePresenter(presenterId: Long): MainContract.Presenter? {
        val presenter = mPresentersCache.getIfPresent(presenterId)
        mPresentersCache.invalidate(presenterId)
        return presenter
    }
}