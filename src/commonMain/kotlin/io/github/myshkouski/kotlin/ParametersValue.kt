package io.github.myshkouski.kotlin

import kotlin.jvm.JvmInline

@JvmInline
value class ParametersValue(
    override val value: Parameters
) : JsonTypedValue<Parameters>