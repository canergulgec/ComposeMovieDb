package com.caner.composemoviedb.domain.mapper

interface Mapper<T, E> {

    fun to(t: T): E
}