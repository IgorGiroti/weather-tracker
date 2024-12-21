package com.igorgiroti.weathertracker.utils


fun String.toHttps() = "https:${this}"
fun String.toPercentage() = "$this%"
fun Double.toDegree() = "$this \u2109"

