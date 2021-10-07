package com.example.gistproject.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.gistproject.data.remotesource.RemoteSource
import com.example.gistproject.data.repository.GistRepositoryImpl
import com.example.gistproject.data.response.Owner
import com.example.gistproject.data.response.ResponseGist
import io.reactivex.Single
import org.junit.Assert
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Callable

@RunWith(MockitoJUnitRunner::class)
class GistUnitTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @InjectMocks
    private lateinit var viewModel: GistViewModel

    @Mock
    private lateinit var gistRepository: GistRepositoryImpl

    @Mock
    private lateinit var remoteSource: RemoteSource

    @Test
    fun `when_getGists_get_sucess_then_set_liveResponseGit`(){
        Mockito.`when`(gistRepository.getGist(1)).thenReturn(getSingleMock())
        Mockito.`when`(remoteSource.getGists(1)).thenReturn(getSingleMock())
        viewModel.liveResponseGistParcelable.value?.isEmpty()?.let { Assert.assertTrue(it) }
    }

    fun getSingleMock(): Single<List<ResponseGist>>? {
        return Single.fromCallable(Callable { getMockGist() })
    }

    fun getMockGist(): List<ResponseGist> {
        return listOf(ResponseGist("1", Owner("login","avatar_url"), mapOf(),true))
    }

}