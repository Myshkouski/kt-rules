package io.github.myshkouski.kotlin

@JvmInline
value class LongValue(
    override val value: Long
) : JsonTypedValue<Long>
