package tests

import hellpers.Sources
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class NewTest {
    @Test
    fun testAdd() {
        val sources = Sources.fromResource("image.jpg")
        assertEquals(42, Integer.sum(19, 23))
        println(sources.toString())
    }
}

