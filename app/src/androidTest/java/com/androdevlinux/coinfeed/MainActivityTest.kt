package com.androdevlinux.coinfeed

import android.os.SystemClock
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.androdevlinux.coinfeed.base.BaseUITest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.net.HttpURLConnection


@RunWith(AndroidJUnit4::class)
class MainActivityTest: BaseUITest() {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var mActivityTestRule: ActivityScenarioRule<*>? = ActivityScenarioRule(MainActivity::class.java)



    @Test
    fun test_recyclerview_elements_for_expected_response() {
        mockNetworkResponseWithFileContent("success_resp_list.json", HttpURLConnection.HTTP_OK)

        //Wait for MockWebServer to get back with response
        SystemClock.sleep(1000)
    }
}