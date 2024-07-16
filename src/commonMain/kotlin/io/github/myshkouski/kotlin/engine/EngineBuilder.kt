@file:OptIn(ExperimentalJsExport::class)

package io.github.myshkouski.kotlin.engine

import io.github.myshkouski.kotlin.Builder
import io.github.myshkouski.kotlin.operator.Operator
import io.github.myshkouski.kotlin.rule.Rule
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface EngineBuilder: MutableEngineOperations, Builder<Engine>

class DefaultEngineBuilder: EngineBuilder {
    private val engineProperties: EngineProperties = DefaultEngineProperties()

    override fun addRule(rule: Rule): EngineBuilder {
        engineProperties.rules += rule
        return this
    }

    override fun addOperator(name: String, operator: Operator): EngineBuilder {
        engineProperties.operators.set(name, operator)
        return this
    }

    override fun build(): Engine {
        return DefaultEngine(engineProperties)
    }
}

@JsExport
@JsName("createEnginBuilder")
fun EngineBuilder(): EngineBuilder {
    return DefaultEngineBuilder()
}
