package io.github.myshkouski.kotlin.engine

import io.github.myshkouski.kotlin.Parameters
import io.github.myshkouski.kotlin.fact.Fact
import io.github.myshkouski.kotlin.operator.Operator
import io.github.myshkouski.kotlin.storage.Storage

interface Engine {
    suspend fun run(facts: Storage<out Fact>): Parameters
}

interface EvaluationContext {
    val facts: Storage<Fact>
    val operators: Storage<Operator>
}

class DefaultEvaluationContext(
    override val facts: Storage<Fact>,
    override val operators: Storage<Operator>,
): EvaluationContext

class DefaultEngine(
    private val context: EngineProperties
) : Engine {
    override suspend fun run(facts: Storage<out Fact>): Parameters {
        val parameters: Parameters = Storage()

        context.rules.toList().find {
            !it.evaluate(facts, null)
        }

        return parameters
    }
}
