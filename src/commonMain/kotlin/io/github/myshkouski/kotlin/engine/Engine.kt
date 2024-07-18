package io.github.myshkouski.kotlin.engine

import io.github.myshkouski.kotlin.fact.Fact
import io.github.myshkouski.kotlin.rule.RuleContext
import io.github.myshkouski.kotlin.storage.Storage
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName

interface Engine {
    suspend fun run(facts: Storage<out Fact>): Storage<Boolean>
}

internal class DefaultEngine(
    private val context: EngineProperties
) : Engine {
    override suspend fun run(facts: Storage<out Fact>): Storage<Boolean> {
        val results = Storage<Boolean>()

        val ruleContext = RuleContext(facts, context.operators, trace = null)

        for ((name, rule) in context.rules.entries()) {
            val result = try {
                rule.evaluate(ruleContext)
            } catch (e: Throwable) {
                false
            }

            results.set(name, result)

            if (!result) {
                break
            }
        }

        return results
    }
}

@OptIn(ExperimentalJsExport::class)
@JsExport.Ignore
@JsName("createEngine")
fun Engine(): Engine {
    return DefaultEngine(context = DefaultEngineProperties())
}
