package com.caner.composemoviedb.domain.extension

import com.caner.composemoviedb.data.mapper.Mapper
import com.caner.composemoviedb.data.viewstate.Resource

fun <A, B> A.filterMapperResponse(mapper: Mapper<A, B>): Resource<B> {
    return Resource.Success(mapper.to(this))
}