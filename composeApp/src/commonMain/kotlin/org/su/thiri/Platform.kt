package org.su.thiri

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform