package com.ramindu.weeraman.resturantfinder.data

import kotlinx.coroutines.delay

interface RestaurantFinderUserCase {
    suspend fun getRestaurantsNearBy(lon: Double, lat: Double): List<RestaurantItem>
}

class RestaurantFinderUserCaseIml() : RestaurantFinderUserCase {
    override suspend fun getRestaurantsNearBy(lon: Double, lat: Double): List<RestaurantItem> {
        delay(3000)
        val item1 = RestaurantItem(
            52.50705869665731, 13.389687836618041, name = "Green Rabbit",
            address = "Steinstr 2, 12105,Berlin",
            type = "Vegan"
        )
        val item2 = RestaurantItem(
            52.50720292735356,
            13.386101039082034,
            name = "Karibisches",
            address = "Steinstr 2, 12105,Berlin",
            type = "Pub & Bar"
        )
        val item3 =
            RestaurantItem(
                52.506874648612246, 13.393952631095573, name = "Choppaluna",
                address = "Steinstr 2, 12105,Berlin",
                type = "Mexican"
            )
        val item4 =
            RestaurantItem(
                52.502089076216656, 13.391567074705963, name = "Delhi 6",
                address = "Steinstr 2, 12105,Berlin",
                type = "Indian"
            )
        val item5 =
            RestaurantItem(
                52.509145204598006, 13.405896994448506, name = "Bocca Felice",
                address = "Steinstr 2, 12105,Berlin",
                type = "Italian"
            )

        return listOf(item1, item2, item3, item4, item5)

    }

}