package com.lumec.challenge.mercadolibre.ui

import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.lumec.challenge.data.ProductRemoteDataSource
import com.lumec.challenge.mercadolibre.MainActivity
import com.lumec.challenge.mercadolibre.data.server.CoroutinesTestRule
import com.lumec.challenge.mercadolibre.data.server.MockWebServerRule
import com.lumec.challenge.mercadolibre.data.server.SuccessDispatcher
import com.lumec.challenge.mercadolibre.ui.theme.MercadoLibreTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import timber.log.Timber
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
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var remoteDataSource: ProductRemoteDataSource

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @Before
    fun setUp() {
        hiltRule.inject()

        IdlingRegistry.getInstance().resources.forEach {
            Timber.e("resource ${it.name}    idleNow: ${it.isIdleNow}")
            if (it.name == "Compose-Espresso link") {
                IdlingRegistry.getInstance().unregister(it)
            }
        }
    }

    @Test
    fun getProductsByNameResult_sentRequest_receivedExpected() = runTest {
        mockWebServerRule.server.dispatcher = SuccessDispatcher()

        testDispatcher.advanceUntilIdle()
        val productList = remoteDataSource.getProductsByName("Motorola G6")

        productList.fold({ throw Exception(it.toString()) }) {
            Assert.assertEquals("MLA810645375", it[0].id)
            Assert.assertEquals("MLA805330648", it[1].id)
        }
    }

    @Test
    fun getProductDetailsById_sentRequest_receivedExpected() = runBlocking {
        val productId = "MLA805330648"
        mockWebServerRule.server.dispatcher = SuccessDispatcher(productId)

        val details = remoteDataSource.getProductDetailsById(productId)

        details.fold({ throw Exception(it.toString()) }) {
            Assert.assertEquals(productId, it.id)
            Assert.assertEquals(15999, it.price)
            Assert.assertTrue(it.description.contains("1,8 GHz y 3 GB de RAM"))
        }
    }


    @Test
    fun verify_app_initialization() {
        mockWebServerRule.server.dispatcher = SuccessDispatcher()

        composeTestRule.clearAndSetContent{
            MercadoLibreTheme {
                Navigation()
            }
        }

        composeTestRule
            .onNodeWithContentDescription("search bar", useUnmergedTree = true)
            .assertIsDisplayed()

    }

    /*

    It seems there is a problem with performing text input in my SearchBar composable
    because I'm getting the error:
    "java.lang.NullPointerException: null cannot be cast to non-null
    type androidx.compose.ui.platform.ViewRootForTest"

    I've been trying everything but I haven't been able to solve it

    @Test
    fun test() = runTest {
        mockWebServerRule.server.dispatcher = SuccessDispatcher()

        composeTestRule.clearAndSetContent{
            MercadoLibreTheme {
                Navigation()
            }
        }

        composeTestRule
            .onNodeWithContentDescription("search bar", useUnmergedTree = true)
            .performTextInput("hola")
    }

    @Test
    fun test2() = runTest {
        mockWebServerRule.server.dispatcher = SuccessDispatcher()

        composeTestRule.clearAndSetContent{
            MercadoLibreTheme {
                Navigation()
            }
        }

        composeTestRule
            .onNodeWithContentDescription("search bar", useUnmergedTree = true)
            .onChild()
            .performTextInput("hola")
    }


    @Test
    fun test3() = runTest {
        mockWebServerRule.server.dispatcher = SuccessDispatcher()

        composeTestRule.clearAndSetContent{
            MercadoLibreTheme {
                Navigation()

            }
        }

        composeTestRule
            .onNodeWithContentDescription("search bar", useUnmergedTree = true)
            .performClick()

        composeTestRule
            .onNodeWithContentDescription("search bar", useUnmergedTree = true)
            .performImeAction()
    }


    @Test
    fun test4() = runTest {
        mockWebServerRule.server.dispatcher = SuccessDispatcher()

        composeTestRule.clearAndSetContent{
            MercadoLibreTheme {
                Navigation()
            }
        }

        composeTestRule
            .onNodeWithContentDescription("search bar", useUnmergedTree = true)
            .onSiblings()[0]
            .performTextInput("hola")
    }

    */
}

fun AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>
        .clearAndSetContent(content: @Composable () -> Unit) {
    (this.activity.findViewById<ViewGroup>(android.R.id.content)
        ?.getChildAt(0) as? ComposeView)?.setContent(content)
        ?: this.setContent(content)
}