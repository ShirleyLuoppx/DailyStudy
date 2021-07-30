package com.ppx.dailystudy

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ppx.dailystudy.test.utils.DataAndFileUtils

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private val TAG = "UtilTestActivity"

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.ppx.dailystudy", appContext.packageName)
    }

    @Test
    fun testInt2ByteArray1() {
        Log.d(TAG, "testInt2ByteArray1: ${DataAndFileUtils.intToByteArray1(1)}")
    }
}