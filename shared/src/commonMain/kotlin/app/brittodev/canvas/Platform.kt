package app.brittodev.canvas

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform