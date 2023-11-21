package com.ramindu.weeraman.resturantfinder.ui

import com.ramindu.weeraman.resturantfinder.data.RestaurantItem

data class RestaurantUIState(
    val restaurants: List<RestaurantItem> = emptyList(),
    val loading: Boolean = false
)