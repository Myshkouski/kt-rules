package io.github.myshkouski.kotlin.engine

import io.github.myshkouski.kotlin.Parameters
import io.github.myshkouski.kotlin.fact.Fact
import io.github.myshkouski.kotlin.rule.RuleContext
import io.github.myshkouski.kotlin.storage.Storage
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName

interface Engine {
    suspend fun run(facts: Storage<out Fact>): Parameters
}

internal class DefaultEngine(
    private val context: EngineProperties
) : Engine {
    override suspend fun run(facts: Storage<out Fact>): Parameters {
        val parameters: Parameters = Storage()

        val ruleContext = RuleContext(facts, context.operators, trace = null)

        context.rules.toList().find {
            !it.evaluate(ruleContext)
        }

        return parameters
    }
}

@OptIn(ExperimentalJsExport::class)
@JsExport.Ignore
@JsName("createEngine")
fun Engine(): Engine {
    return DefaultEngine(context = DefaultEngineProperties())
}
