@file:OptIn(ExperimentalJsExport::class)

package io.github.myshkouski.kotlin.engine

import io.github.myshkouski.kotlin.Optional
import io.github.myshkouski.kotlin.Parameters
import io.github.myshkouski.kotlin.fact.ValueProvider
import io.github.myshkouski.kotlin.storage.Storage
import js.promise.Promise
import js.promise.promise
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope

@JsExport
@JsName("Engine")
interface JsEngine : Engine {
    @OptIn(DelicateCoroutinesApi::class)
    @JsName("run")
    fun jsRun(facts: Storage<out JsValueProvider<*>>): Promise<Parameters> {
        return GlobalScope.promise {
           run(facts)
        }
    }
}

@JsExport
@JsName("ValueProvider")
interface JsValueProvider<out T> : ValueProvider<Any?> {
    @OptIn(DelicateCoroutinesApi::class)
    @JsName("run")
    fun jsProvide(): Promise<Optional<out Any?>> {
        return GlobalScope.promise {
            provide()
        }
    }
}
