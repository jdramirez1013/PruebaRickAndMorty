package com.jdr.rickandmorty.data.paginator

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}