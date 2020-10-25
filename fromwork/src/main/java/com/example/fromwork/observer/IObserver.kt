package com.example.fromwork.observer

interface IObserver<T> {
    fun success(bean: T);

    fun error(message: String);
}