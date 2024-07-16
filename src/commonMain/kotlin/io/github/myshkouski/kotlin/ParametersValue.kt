package io.github.myshkouski.kotlin

@JvmInline
value class ParametersValue(
    override val value: Parameters
) : JsonTypedValue<Parameters>