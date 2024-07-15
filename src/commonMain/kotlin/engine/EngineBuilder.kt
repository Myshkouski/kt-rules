package engine

import Builder

interface EngineBuilder : MutableEngine, Builder<Engine>

fun EngineBuilder(): EngineBuilder = TODO()
