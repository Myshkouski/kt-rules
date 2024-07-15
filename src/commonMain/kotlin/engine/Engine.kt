package engine

import Parameters
import fact.Fact

interface Engine {
    fun run(facts: Array<Fact>): Parameters
}

class DefaultEngine : Engine {
    private val context: EngineContext = TODO()

    override fun run(facts: Array<Fact>): Parameters {
        TODO("Not yet implemented")
    }
}

