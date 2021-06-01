package com.maps.contest.data.util

fun <T> List<Mappable<T>>?.listToDomain(): List<T> {
    return this?.map { it.toDomain() } ?: emptyList()
}