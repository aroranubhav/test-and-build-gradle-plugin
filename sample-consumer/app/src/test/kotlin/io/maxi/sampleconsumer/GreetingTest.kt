package io.maxi.sampleconsumer

import org.junit.Assert.assertEquals
import org.junit.Test

class GreetingTest {

    @Test
    fun `message returns expected message`() {
        assertEquals("Hola from sample-consumer", Greetings.message())
    }
}