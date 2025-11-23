package com.devvillar.resourl.core.logging

import timber.log.Timber

class DebugTreeWithLine : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String {
        return "(${element.fileName}:${element.lineNumber})#${element.methodName}"
    }
}