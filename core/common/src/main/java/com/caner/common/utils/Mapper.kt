package com.caner.common.utils

interface Mapper<From, To> {
    fun transform(from: From): To
}