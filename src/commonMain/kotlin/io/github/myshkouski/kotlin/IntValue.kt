package io.github.myshkouski.kotlin

@JvmInline
value class IntValue(
    override val value: Int
) : JsonTypedValue<Int>
