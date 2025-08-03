package com.caner.data.mapper

interface Mapper<From, To> {
    fun transform(from: From): To
}
