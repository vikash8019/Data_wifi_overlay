package com.vikash.nettoggle

import java.io.BufferedReader
import java.io.InputStreamReader

object RootUtils {

    /** Runs a shell command as root. Returns true if it executed without error. */
    fun runAsRoot(command: String): Boolean {
        return try {
            val process = Runtime.getRuntime().exec("su")
            val os = process.outputStream
            os.write((command + "\n").toByteArray())
            os.write("exit\n".toByteArray())
            os.flush()
            os.close()
            process.waitFor()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /** Runs a shell command as root and returns its stdout output (trimmed). */
    fun runAsRootWithOutput(command: String): String {
        return try {
            val process = Runtime.getRuntime().exec("su")
            val os = process.outputStream
            os.write((command + "\n").toByteArray())
            os.write("exit\n".toByteArray())
            os.flush()
            os.close()
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            val output = reader.readText()
            process.waitFor()
            output.trim()
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

    fun setWifiEnabled(enable: Boolean) {
        runAsRoot("svc wifi ${if (enable) "enable" else "disable"}")
    }

    fun setMobileDataEnabled(enable: Boolean) {
        runAsRoot("svc data ${if (enable) "enable" else "disable"}")
    }

    /** Reads mobile data on/off state via settings table (needs root, works on all OEMs). */
    fun isMobileDataEnabled(): Boolean {
        val result = runAsRootWithOutput("settings get global mobile_data")
        return result.trim() == "1"
    }
}
