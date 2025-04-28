package com.caner.common.utils

interface Mapper<T, E> {

    fun to(t: T): E
}