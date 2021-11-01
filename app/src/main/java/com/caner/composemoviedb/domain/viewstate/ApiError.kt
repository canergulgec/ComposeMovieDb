package com.caner.composemoviedb.domain.viewstate

data class ApiError(
    val code: Int = -1,
    override val message: String? = ""
) : Throwable()