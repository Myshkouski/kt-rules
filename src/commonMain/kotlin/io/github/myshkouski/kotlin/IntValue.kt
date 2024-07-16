package io.github.myshkouski.kotlin

import kotlin.jvm.JvmInline

@JvmInline
value class IntValue(
    override val value: Int
) : JsonTypedValue<Int>
