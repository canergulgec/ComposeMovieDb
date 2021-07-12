package com.caner.composemoviedb.common.ext

import com.caner.composemoviedb.common.ApiError
import com.caner.composemoviedb.common.Mapper
import com.caner.composemoviedb.common.Resource
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