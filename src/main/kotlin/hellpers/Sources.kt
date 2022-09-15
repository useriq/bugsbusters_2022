package hellpers

import java.io.InputStream

object Sources {
    fun fromResource(path: String): Source = object : Source {
        override fun asString(): String = this.javaClass::class.java.getResource(path).readText()
        override fun asStream(): InputStream = this.javaClass::class.java.getResourceAsStream(path)
    }
}

interface Source {
    fun asString(): String
    fun asStream(): InputStream
}
