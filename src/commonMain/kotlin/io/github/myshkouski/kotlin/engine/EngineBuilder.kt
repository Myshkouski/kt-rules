package io.github.myshkouski.kotlin.engine

import io.github.myshkouski.kotlin.Builder
import io.github.myshkouski.kotlin.criterion.TypedCriterion
import io.github.myshkouski.kotlin.operator.TypedOperator
import io.github.myshkouski.kotlin.rule.Rule

interface EngineBuilder: MutableEngineOperations, Builder<Engine>

class DefaultEngineBuilder: EngineBuilder {
    private val engineProperties: EngineProperties = DefaultEngineProperties()

    override fun addRule(rule: Rule): EngineBuilder {
        engineProperties.rules += rule
        return this
    }

    override fun <T> setCondition(name: String, condition: TypedCriterion<T>): EngineBuilder {
        TODO("Not yet implemented")
    }

    override fun <T, U> addOperator(name: String, operator: TypedOperator<T, U>): EngineBuilder {
        TODO("Not yet implemented")
    }

    override fun build(): Engine {
        return DefaultEngine(engineProperties)
    }
}

fun EngineBuilder(): EngineBuilder {
    return DefaultEngineBuilder()
}
