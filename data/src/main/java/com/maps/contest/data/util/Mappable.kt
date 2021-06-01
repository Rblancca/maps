package com.maps.contest.data.util

interface Mappable<out Domain> {
    fun toDomain(): Domain
}