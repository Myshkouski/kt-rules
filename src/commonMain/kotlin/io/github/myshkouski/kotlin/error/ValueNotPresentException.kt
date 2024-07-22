package io.github.myshkouski.kotlin.error

sealed class EvaluationError(message: String, cause: Throwable?) : RuntimeException(message, cause)

class ValueNotPresentException(cause: Throwable? = null) : EvaluationError("Value is not present.", cause)

class RuleEvaluationError(name: String, cause: Throwable?) : EvaluationError("Cannot evaluate rule '$name'.", cause)
