package test

import engine.EngineBuilder
import kotlin.test.Test

class EngineTests {
    @Test
    fun test() {
        val engineBuilder = EngineBuilder()
        val engine = engineBuilder.build()
    }
}