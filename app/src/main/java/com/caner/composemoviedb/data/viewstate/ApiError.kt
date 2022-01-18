package com.caner.composemoviedb.data.viewstate

data class ApiError(
    val code: Int? = -1,
    override val message: String? = ""
) : Throwable()