package com.kkp.buddytrainer.core.qr

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlinx.coroutines.channels.Channel
import kotlin.math.abs
import kotlin.math.roundToInt
import kotlin.math.sqrt

class SensorHandler(
    context: Context,
) : SensorEventListener {

    private lateinit var sensorManager : SensorManager
    val accelData : Channel<SensorData> = Channel(Channel.UNLIMITED)
    init{
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(this, accelSensor, SensorManager.SENSOR_DELAY_UI)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null){
            if (event.sensor.type == Sensor.TYPE_ACCELEROMETER){
                val aX = event.values[0]
                val aY = event.values[1]
                val aZ = event.values[2]
                val accl = (sqrt((aX*aX)+(aY*aY)+(aZ*aZ)) *100.0).roundToInt() / 100.0
                if(accl > 0){
                    val cleanData =  abs(accl - 9.81f)
                    accelData.trySend(
                        SensorData(
                            accelerometer = String.format("%.2f",cleanData)
                        )
                    )
                }
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }
    fun cancel(){
        sensorManager.unregisterListener(this)
    }
}
data class SensorData(
    val accelerometer: String = ""
)
