package com.caner.composemoviedb.domain.extension

import com.caner.composemoviedb.data.viewstate.ApiError
import com.caner.composemoviedb.data.mapper.Mapper
import com.caner.composemoviedb.data.viewstate.Resource
import retrofit2.Response

fun <A, B> Response<A>.filterMapperResponse(mapper: Mapper<A, B>): Resource<B> {
    return when (this.isSuccessful) {
        true -> {
            this.body()?.let {
                return Resource.Success(mapper.to(it))
            } ?: return Resource.Error(ApiError(code(), "Response body is null"))
        }

        false -> Resource.Error(ApiError(code(), "Response is not successful"))
    }
}

fun <A, B> A.filterMapperResponse(mapper: Mapper<A, B>): Resource<B> {
    return Resource.Success(mapper.to(this))
}