package com.caner.common.extension

import com.caner.common.utils.Mapper
import com.caner.common.network.Resource

fun <A, B> A.mapTo(mapper: Mapper<A, B>): Resource<B> {
    return Resource.Success(mapper.to(this))
}