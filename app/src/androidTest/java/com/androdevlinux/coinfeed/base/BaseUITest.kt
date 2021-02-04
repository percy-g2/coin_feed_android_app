package com.androdevlinux.coinfeed.base

import androidx.test.platform.app.InstrumentationRegistry
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import java.io.BufferedReader
import java.io.Reader

abstract class BaseUITest : KoinTest {

    /**
     * For MockWebServer instance
     */
    private lateinit var mockServer: MockWebServer


    /**
     * Default, let server be shut down
     */
    private var mShouldStart = false

    @Before
    open fun setUp(){
        true.startMockServer()
    }

    /**
     * Helps to read input file returns the respective data in mocked call
     */
    fun mockNetworkResponseWithFileContent(fileName: String, responseCode: Int) = mockServer.enqueue(
        MockResponse()
            .setResponseCode(responseCode)
            .setBody(getJson(fileName)))

    /**
     * Reads input file and converts to json
     */
    private fun getJson(path : String) : String {
        var content: String
        val testContext = InstrumentationRegistry.getInstrumentation().context
            val inputStream = testContext.assets.open(path)
            val reader = BufferedReader(inputStream.reader() as Reader?)
            reader.use {
                content = it.readText()
            }
        return content
    }

    /**
     * Start Mockwebserver
     */
    private fun Boolean.startMockServer() {
        if (this){
            mShouldStart = this
            mockServer = MockWebServer()
            mockServer.start()
        }
    }

    /**
     * Set Mockwebserver url
     */
    fun getMockWebServerUrl() = mockServer.url("/").toString()

    /**
     * Stop Mockwebserver
     */
    private fun stopMockServer() {
        if (mShouldStart){
            mockServer.shutdown()
        }
    }

    @After
    open fun tearDown(){
        //Stop Mock server
        stopMockServer()
        //Stop Koin as well
        stopKoin()
    }
}