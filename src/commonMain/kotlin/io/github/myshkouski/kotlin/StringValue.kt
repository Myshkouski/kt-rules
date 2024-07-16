package io.github.myshkouski.kotlin

@JvmInline
value class StringValue(
    override val value: String
) : JsonTypedValue<String>