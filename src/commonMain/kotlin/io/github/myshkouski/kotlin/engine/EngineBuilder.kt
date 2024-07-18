@file:OptIn(ExperimentalJsExport::class)

package io.github.myshkouski.kotlin.engine

import io.github.myshkouski.kotlin.Builder
import io.github.myshkouski.kotlin.operator.TypedOperator
import io.github.myshkouski.kotlin.rule.Rule
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface EngineBuilder: MutableEngineOperations, Builder<Engine>

class DefaultEngineBuilder: EngineBuilder {
    private val engineProperties: EngineProperties = DefaultEngineProperties()

    override fun setRule(name: String, rule: Rule): EngineBuilder {
        engineProperties.rules.set(name, rule)
        return this
    }

    override fun setOperator(name: String, operator: TypedOperator<Any?, Any?>): EngineBuilder {
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
