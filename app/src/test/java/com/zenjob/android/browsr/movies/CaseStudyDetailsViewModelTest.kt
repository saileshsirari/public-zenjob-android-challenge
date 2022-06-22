package com.zenjob.android.browsr.movies

import com.zenjob.android.browsr.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@RunWith(org.mockito.junit.MockitoJUnitRunner::class)
class CaseStudyDetailsViewModelTest {

    @get:Rule
    val mainCoroutineRule = TestCoroutineRule()


    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
    }


}