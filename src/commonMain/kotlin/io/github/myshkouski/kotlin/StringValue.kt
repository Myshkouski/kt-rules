package io.github.myshkouski.kotlin

import kotlin.jvm.JvmInline

@JvmInline
value class StringValue(
    override val value: String
) : JsonTypedValue<String>