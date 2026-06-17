package com.linkin.stepsandbites

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform