package com.ramindu.weeraman.resturantfinder.ui

import android.text.SpannableString
import android.text.Spanned
import android.util.Log
import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.Action
import androidx.car.app.model.CarColor
import androidx.car.app.model.CarLocation
import androidx.car.app.model.CarText
import androidx.car.app.model.ForegroundCarColorSpan
import androidx.car.app.model.ItemList
import androidx.car.app.model.Place
import androidx.car.app.model.PlaceListMapTemplate
import androidx.car.app.model.PlaceMarker
import androidx.car.app.model.Row
import androidx.car.app.model.Template
import com.ramindu.weeraman.resturantfinder.data.RestaurantFinderUserCaseIml
import com.ramindu.weeraman.resturantfinder.data.RestaurantItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.car.app.model.Metadata

class RestaurantListScreen(carContext: CarContext) : Screen(carContext) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private val restaurantFinderUserCase = RestaurantFinderUserCaseIml()
    private var uiState = RestaurantUIState(
        loading = true
    )

    init {
        updateCafeList()
    }

    @androidx.annotation.OptIn(androidx.car.app.annotations.ExperimentalCarApi::class)
    override fun onGetTemplate(): Template {
        val builder = PlaceListMapTemplate
            .Builder()
            .setTitle("CafeFinder")
            .setHeaderAction(Action.APP_ICON)

        builder.setLoading(uiState.loading)

        if (!uiState.loading) {
            builder.setLoading(false)
            val items = ItemList.Builder()
            uiState.restaurants.forEach {
                items.addItem(itemRow(it))
            }
            builder.setItemList(items.build())
        }
        return builder.build()
    }

    private fun itemRow(item: RestaurantItem): Row {
        return Row.Builder()
            .setTitle(item.name)
            .setMetadata(
                Metadata.Builder()
                    .setPlace(
                        Place.Builder(
                            CarLocation.create(
                                item.lon, item.lat
                            )
                        ).setMarker(
                            PlaceMarker.Builder().setColor(
                                CarColor.createCustom(
                                    0xFF4287f5.toInt(), 0xFF4287f5.toInt()
                                )
                            ).build()
                        )
                            .build()
                    ).build()
            )
            .setBrowsable(true)
            .setOnClickListener {
                 screenManager.push(RestaurantDetailsScreen(carContext, item))
            }
            .addText(item.name)
            .build()
    }

    private fun updateCafeList() {
        coroutineScope.launch {
            uiState = RestaurantUIState(
                loading = false,
                restaurants = restaurantFinderUserCase.getRestaurantsNearBy(0.0, 0.0)
            )
            invalidate()
        }
    }
}