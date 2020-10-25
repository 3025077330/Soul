package com.example.fromwork.observer

import io.reactivex.observers.DisposableObserver

abstract class CallBackObserver<T> : DisposableObserver<T>(), IObserver<T> {
    override fun onNext(t: T) {
        success(t!!)
    }

    override fun onError(e: Throwable) {
        error(e.message!!)
    }

    override fun onComplete() {

    }
}