package com.ramindu.weeraman.resturantfinder.ui

import androidx.car.app.CarContext
import androidx.car.app.CarToast
import androidx.car.app.Screen
import androidx.car.app.hardware.CarHardwareManager
import androidx.car.app.hardware.common.OnCarDataAvailableListener
import androidx.car.app.hardware.info.Model
import androidx.car.app.model.Action
import androidx.car.app.model.MessageTemplate
import androidx.car.app.model.Template

class CarInfoScreen(carContext: CarContext) : Screen(carContext) {
    private var carInfoStr: String? = null
    override fun onGetTemplate(): Template {
        return MessageTemplate
            .Builder("Display car information here..!")
            .setTitle("Car Information")
            .addAction(
                Action.Builder()
                    .setTitle("SHOW CAR INFO")
                    .setOnClickListener {
                        getCarData()
                    }
                    .build()
            )
            .build()
    }

    private fun getCarData() {
        val carInfo = carContext.getCarService(CarHardwareManager::class.java).carInfo
        val listener = OnCarDataAvailableListener<Model> { data ->

            carInfoStr =
                "Car manufacturer : " + data.manufacturer.value + " , Car Model : " + data.name.value + " , Model year : " + data.year.value

            carInfoStr.let {
                if (it != null) {
                    CarToast.makeText(carContext, it, CarToast.LENGTH_LONG).show()
                }
            }
        }
        carInfo.fetchModel(carContext.mainExecutor, listener)
    }
}