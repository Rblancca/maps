package com.maps.contest.data

import arrow.core.left
import arrow.core.right
import com.maps.contest.data.entities.ErrorResponse
import com.maps.contest.data.entities.Routers
import com.maps.contest.data.repository.MapsDataSource
import com.maps.contest.data.repository.MapsRepositoryImpl
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MapsRepositoryTest {

    @Mock
    lateinit var dataSource: MapsDataSource

    @Mock
    private lateinit var repository: MapsRepositoryImpl

    @Before
    fun setUp() {
        repository = MapsRepositoryImpl(dataSource)
    }

    @Test
    fun `when getPosts are asked, an error is received`() {
        runBlocking {
            //Given
            val expectedError = ErrorResponse("")
            whenever(dataSource.getRouters(.0, .0, .0, .0)).thenReturn(expectedError.left())

            //When
            val expected = ErrorResponse("").left()
            val actual = repository.getRouters(.0, .0, .0, .0)

            //Then
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `when getPosts are asked, an success is received`() {
        runBlocking {
            //Given
            val expectedResponse = listOf(Routers("", "", .0, .0, 0, .0, .0, 0, 0f))
            whenever(dataSource.getRouters(.0, .0, .0, .0)).thenReturn(expectedResponse.right())

            //When
            val expected = listOf(Routers("", "", .0, .0, 0, .0, .0, 0, 0f)).right()
            val actual = repository.getRouters(.0, .0, .0, .0)

            //Then
            assertEquals(expected, actual)
        }
    }
}
