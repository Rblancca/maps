package com.maps.contest.data.entities

enum class RoutersColors(val id: Int) {
    CATEGORY_1(378),
    CATEGORY_2(382),
    CATEGORY_3(402),
    CATEGORY_4(412),
    CATEGORY_5(473);

    companion object {
        fun from(findValue: Int): Float = when (findValue) {
            CATEGORY_1.id -> 0f
            CATEGORY_2.id -> 30f
            CATEGORY_3.id -> 60f
            CATEGORY_4.id -> 90f
            CATEGORY_5.id -> 120f
            else -> 150f
        }
    }
}

