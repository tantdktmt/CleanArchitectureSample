package com.majesticreader.presentation.util

import android.util.Log
import com.majesticreader.BuildConfig
import java.util.Formatter

object LogUtils {

    private const val MIN_STACK_OFFSET = 3

    private var defaultTag = "LogUtils"

    private const val V = Log.VERBOSE
    private const val D = Log.DEBUG
    private const val I = Log.INFO
    private const val W = Log.WARN
    private const val E = Log.ERROR
    const val A = Log.ASSERT

    private const val TOP_BORDER =
        "╔═════════════════════════════════════════════════════════════════════════════════════════"
    private const val LEFT_BORDER = "║ "
    private const val BOTTOM_BORDER =
        "╚═════════════════════════════════════════════════════════════════════════════════════════"
    private const val MAX_LEN = 1000
    var open = true

    private fun processTagAndHead(): String {
        val elements = Thread.currentThread().stackTrace
        val offset = getStackOffset(elements)
        val targetElement = elements[offset]
        val head = Formatter()
            .format(
                "%s [%s(%s:%d)]",
                "In Thread: " + Thread.currentThread().name,
                targetElement.methodName,
                targetElement.fileName,
                targetElement.lineNumber
            )

        return head.toString()
    }

    private fun processMsgBody(msg: String? = "", flag: Int, tag: String? = defaultTag) {
        if (BuildConfig.DEBUG) {
            printTop(flag, tag)
            // First print the call information
            printLog(flag, tag)

            val lineCount = (msg?.length)?.div(MAX_LEN)
            lineCount?.let {
                if (lineCount == 0) {
                    printLog(flag, tag, msg)
                } else {
                    var index = 0
                    var i = 0
                    while (true) {
                        printLog(flag, tag, msg.substring(index, index + MAX_LEN))
                        index += MAX_LEN
                        if ((++i) >= it) {
                            break
                        }
                    }
                }
            }
            printBottom(flag, tag)
        }
    }

    private fun getStackOffset(trace: Array<StackTraceElement>): Int {
        var i = MIN_STACK_OFFSET
        while (i < trace.size) {
            val e = trace[i]
            val name = e.className
            if (name != LogUtils::class.java.name) {
                return i
            }
            i++
        }
        return 2
    }

    fun v(msg: String?) {
        v(defaultTag, msg)
    }

    fun i(msg: String?) {
        i(defaultTag, msg)
    }

    fun d(msg: String?) {
        d(defaultTag, msg)
    }

    fun w(msg: String?) {
        w(defaultTag, msg)
    }

    fun e(msg: String?) {
        e(defaultTag, msg)
    }

    fun v(tag: String?, msg: String?) {
        if (!open) {
            return
        }
        processMsgBody(msg, V, tag)
    }

    fun i(tag: String?, msg: String?) {
        if (!open) {
            return
        }
        processMsgBody(msg, I, tag)
    }

    fun d(tag: String?, msg: String?) {
        if (!open) {
            return
        }
        processMsgBody(msg, D, tag)
    }

    private fun w(tag: String?, msg: String?) {
        if (!open) {
            return
        }
        processMsgBody(msg, W, tag)
    }

    fun e(tag: String?, msg: String?) {
        if (!open) {
            return
        }
        processMsgBody(msg, E, tag)
    }

    private fun printLog(flag: Int, tag: String? = "", msg: String? = processTagAndHead()) {
        Log.println(flag, tag, LEFT_BORDER + msg)
    }

    private fun printBottom(flag: Int, tag: String? = "") {
        Log.println(flag, tag, BOTTOM_BORDER)
    }

    private fun printTop(flag: Int, tag: String? = "") {
        Log.println(flag, tag, TOP_BORDER)
    }

    fun closeLog() {
        open = false
    }
}
