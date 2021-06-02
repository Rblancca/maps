package com.maps.contest.myapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import arrow.core.left
import arrow.core.right
import com.maps.contest.data.entities.ErrorResponse
import com.maps.contest.data.entities.Routers
import com.maps.contest.data.usecase.GetRouters
import com.maps.contest.view.map.MapViewModel
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MapViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Mock
    private lateinit var useCase: GetRouters

    private lateinit var viewModel: MapViewModel

    @Before
    fun setUp() {
        viewModel = MapViewModel(getRouters = useCase)
    }

    @Test
    fun `given repository default error response, when posts is called, then error is received`() {
        runBlocking {
            whenever(
                useCase.invoke(.0, .0, .0, .0)
            ).thenReturn(ErrorResponse("").left())
        }

        viewModel.loadMarkets(.0, .0, .0, .0)

        Assert.assertTrue(viewModel.error.value != null)
    }

    @Test
    fun `given repository success response, when getPosts is called, then content is received`() {
        runBlocking {
            whenever(
                useCase.invoke(.0, .0, .0, .0)
            ).thenReturn(
                listOf(Routers("", "", .0, .0, 0, .0, .0, 0, 0f)).right()
            )
        }

        viewModel.loadMarkets(.0, .0, .0, .0)

        Assert.assertTrue(
            viewModel.routers.value == listOf(
                Routers(
                    "",
                    "",
                    .0,
                    .0,
                    0,
                    .0,
                    .0,
                    0,
                    0f
                )
            )
        )

    }

}