package com.ramindu.weeraman.resturantfinder

import android.content.Intent
import androidx.car.app.Screen
import androidx.car.app.Session
import com.ramindu.weeraman.resturantfinder.ui.CarInfoScreen
import com.ramindu.weeraman.resturantfinder.ui.RestaurantListScreen

class MyCarAppSession : Session() {

    override fun onCreateScreen(intent: Intent): Screen {
        return RestaurantListScreen(carContext)
    }
}