package com.devvillar.resourl.core.validator.base

interface FieldValidator<T> {
    fun validate(value: T?): FieldValidationState
}