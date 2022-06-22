
package com.zenjob.android.browsr

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.MainCoroutineDispatcher

interface DispatcherProvider {
    val io: CoroutineDispatcher
    val main: MainCoroutineDispatcher
}