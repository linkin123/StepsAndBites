package com.example.stepsandbites

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform