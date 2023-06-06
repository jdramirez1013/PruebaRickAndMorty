package com.jdr.rickandmorty.data.paginator

import com.jdr.rickandmorty.data.remote.result.Result

class DefaultPaginator<Key, Item>(
    private val initialKey: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> Result<List<Item>>,
    private inline val getNextKey: suspend (List<Item>) -> Key,
    private inline val onError: suspend (Throwable?) -> Unit,
    private inline val onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit
): Paginator<Key, Item> {

    private var currentKey = initialKey
    private var isMakingRequest = false

    override suspend fun loadNextItems() {
        if(isMakingRequest) {
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)
        val result = onRequest(currentKey)
        isMakingRequest = false
        when(result){
            is Result.Error -> {
                onError(result.exception)
                onLoadUpdated(false)
            }
            is Result.Success -> {
                val items = result.data
                currentKey = getNextKey(items)
                onSuccess(items, currentKey)
                onLoadUpdated(false)
            }
        }

    }

    override fun reset() {
        currentKey = initialKey
    }
}