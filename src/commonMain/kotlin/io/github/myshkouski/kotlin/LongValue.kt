package io.github.myshkouski.kotlin

import kotlin.jvm.JvmInline

@JvmInline
value class LongValue(
    override val value: Long
) : JsonTypedValue<Long>
