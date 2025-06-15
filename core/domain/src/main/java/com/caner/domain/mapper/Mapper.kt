package com.caner.domain.mapper

interface Mapper<From, To> {
    fun transform(from: From): To
}
