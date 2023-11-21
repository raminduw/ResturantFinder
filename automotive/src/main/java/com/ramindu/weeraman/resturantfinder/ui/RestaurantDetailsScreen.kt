package com.ramindu.weeraman.resturantfinder.ui

import android.content.Intent
import android.net.Uri
import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.Action
import androidx.car.app.model.CarColor
import androidx.car.app.model.CarIcon
import androidx.car.app.model.Pane
import androidx.car.app.model.PaneTemplate
import androidx.car.app.model.Row
import androidx.car.app.model.Template
import com.ramindu.weeraman.resturantfinder.data.RestaurantItem

class RestaurantDetailsScreen(carContext: CarContext, private val restaurantItem: RestaurantItem) :
    Screen(carContext) {

    override fun onGetTemplate(): Template {
        return PaneTemplate.Builder(
            Pane.Builder()
                .addRow(
                    Row.Builder()
                        .setTitle(restaurantItem.name)
                        .addText(restaurantItem.address)
                        .addText(restaurantItem.type)
                        .setImage(CarIcon.APP_ICON)
                        .build()
                )
                .addAction(
                    Action.Builder()
                        .setTitle("NAVIGATE")
                        .setOnClickListener {
                            screenManager.pop()
                        }
                        .build()
                )
                .build()
        )
            .setTitle(restaurantItem.name)
            .setHeaderAction(Action.BACK)
            .build()
    }
}
