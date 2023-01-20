package com.lumec.challenge.mercadolibre.ui

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.lumec.challenge.data.ProductRemoteDataSource
import com.lumec.challenge.mercadolibre.MainActivity
import com.lumec.challenge.mercadolibre.data.server.CoroutinesTestRule
import com.lumec.challenge.mercadolibre.data.server.MockWebServerRule
import com.lumec.challenge.mercadolibre.data.server.fromJson
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import org.junit.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltAndroidTest
class MainInstrumentationTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val testDispatcher = CoroutinesTestRule()

    @get:Rule(order = 2)
    val mockWebServerRule = MockWebServerRule()

    @get:Rule(order = 3)
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Inject
    lateinit var remoteDataSource: ProductRemoteDataSource

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun getProductsByNameResult_sentRequest_receivedExpected() = runTest {
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(200)
        mockWebServerRule.server.enqueue(mockResponse.fromJson("product_list.json"))

        val productList = remoteDataSource.getProductsByName("Motorola G6")

        productList.fold({ throw Exception(it.toString()) }) {
            Assert.assertEquals("MLA805330648", it[1].id)
        }
    }

    @Test
    fun getProductDetailsById_sentRequest_receivedExpected() = runBlocking{
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(200)
        mockWebServerRule.server.enqueue(mockResponse.fromJson("details.json"))
        mockWebServerRule.server.enqueue(mockResponse.fromJson("description.json"))
        val details = remoteDataSource.getProductDetailsById("MLA805330648")

        details.fold({ throw Exception(it.toString()) }) {
            Assert.assertEquals(15999, it.price)
            Assert.assertTrue(it.description.contains("1,8 GHz y 3 GB de RAM"))
        }
    }

}