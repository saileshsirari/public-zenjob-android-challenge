package com.zenjob.android.browsr.movies


import androidx.paging.PagingSource
import com.zenjob.android.browsr.TestCoroutineRule
import com.zenjob.android.browsr.data.MoviePagingSource
import com.zenjob.android.browsr.api.MovieRepository
import com.zenjob.android.browsr.util.TestUtil
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@RunWith(org.mockito.junit.MockitoJUnitRunner::class)
class MoviesViewModelTest {

    @get:Rule
    val mainCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var repo: MovieRepository

    private lateinit var usecase: MovieUseCase
    private lateinit var viewModel: MoviesViewModel
    private val mockApi = FakeMovieApi()


    @Before
    fun init() {
        MockitoAnnotations.openMocks(this)
        usecase = MovieUseCase(repo)
        viewModel = MoviesViewModel(usecase)
    }

    @Test
// Since load is a suspend function, runTest is used to ensure that it
// runs on the test thread.
    fun loadReturnsPageWhenOnSuccessfulLoadOfData() = runTest {
        val fakeData = TestUtil.getMovies()
        val pagingSource = MoviePagingSource(mockApi)
        val expected = PagingSource.LoadResult.Page(
            data = fakeData.results,
            prevKey = null,
            nextKey = 2
        )
        val actual = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = MovieRepository.NETWORK_PAGE_SIZE,
                placeholdersEnabled = false
            )
        )
        Assert.assertEquals(expected, actual)
    }
}