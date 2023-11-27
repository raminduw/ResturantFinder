package com.ramindu.weeraman.resturantfinder.ui

import android.util.Log
import androidx.car.app.CarContext
import androidx.car.app.CarToast
import androidx.car.app.Screen
import androidx.car.app.hardware.CarHardwareManager
import androidx.car.app.hardware.common.OnCarDataAvailableListener
import androidx.car.app.hardware.info.EnergyLevel
import androidx.car.app.hardware.info.EnergyProfile
import androidx.car.app.hardware.info.Model
import androidx.car.app.model.Action
import androidx.car.app.model.MessageTemplate
import androidx.car.app.model.Template

class CarInfoScreen(carContext: CarContext) : Screen(carContext) {
    private var carEnergyProfile: String = "Display car information here..!"

    override fun onGetTemplate(): Template {
        return MessageTemplate
            .Builder(carEnergyProfile)
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
        getModelInfo()
        getEnergyProfile()
    }

    private fun getModelInfo() {
        val carInfo = carContext.getCarService(CarHardwareManager::class.java).carInfo
        val listener = OnCarDataAvailableListener<Model> { data ->
            val carInfoStr =
                "Car manufacturer : " + data.manufacturer.value + " , Car Model : " + data.name.value + " , Model year : " + data.year.value
            carInfoStr.let {
                CarToast.makeText(carContext, it, CarToast.LENGTH_LONG).show()
            }
        }
        carInfo.fetchModel(carContext.mainExecutor, listener)
    }

    private fun getEnergyProfile() {
        val carEnergy = carContext.getCarService(CarHardwareManager::class.java).carInfo
        val listener = OnCarDataAvailableListener<EnergyProfile> { data ->
            carEnergyProfile = "Fuel Type :" + data.fuelTypes.value?.get(0).toString()
            carEnergyProfile += "\nEV Charger Type :" + data.evConnectorTypes.value?.get(0)
            invalidate()
        }
        carEnergy.fetchEnergyProfile(carContext.mainExecutor, listener)

    }

}