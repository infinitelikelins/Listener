package com.bearya.mobile.ext

import androidx.paging.*
import kotlinx.coroutines.CoroutineScope

@ExperimentalPagingApi
fun <Key : Any, Value : Any> buildMorePagerFlow(scope: CoroutineScope, remoteMediator: RemoteMediator<Key, Value>? = null, source: () -> PagingSource<Key, Value>) =
        Pager(config = PagingConfig(pageSize = 5, prefetchDistance = 2, initialLoadSize = 5, enablePlaceholders = false),
                pagingSourceFactory = source, remoteMediator = remoteMediator).flow.cachedIn(scope)

@ExperimentalPagingApi
fun <Key : Any, Value : Any> buildMorePagerLiveData(scope: CoroutineScope, remoteMediator: RemoteMediator<Key, Value>? = null, source: () -> PagingSource<Key, Value>) =
        Pager(config = PagingConfig(pageSize = 5, prefetchDistance = 2, initialLoadSize = 5, enablePlaceholders = false),
                pagingSourceFactory = source, remoteMediator = remoteMediator).liveData.cachedIn(scope)

fun <Key : Any, Value : Any> buildPagerFlow(scope: CoroutineScope, source: () -> PagingSource<Key, Value>) =
        Pager(config = PagingConfig(pageSize = 5, prefetchDistance = 2, initialLoadSize = 5, enablePlaceholders = false),
              pagingSourceFactory = source).flow.cachedIn(scope)

fun <Key : Any, Value : Any> buildPagerLiveData(scope: CoroutineScope, source: () -> PagingSource<Key, Value>) =
        Pager(config = PagingConfig(pageSize = 5, prefetchDistance = 2, initialLoadSize = 5, enablePlaceholders = false),
              pagingSourceFactory = source).liveData.cachedIn(scope)