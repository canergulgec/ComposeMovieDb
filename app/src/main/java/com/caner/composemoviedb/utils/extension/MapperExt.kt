package com.caner.composemoviedb.utils.extension

import com.caner.composemoviedb.domain.mapper.Mapper
import com.caner.composemoviedb.utils.network.Resource

fun <A, B> A.mapTo(mapper: Mapper<A, B>): Resource<B> {
    return Resource.Success(mapper.to(this))
}