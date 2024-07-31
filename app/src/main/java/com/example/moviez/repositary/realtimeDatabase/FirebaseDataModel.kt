package com.example.moviez.repositary.realtimeDatabase

data class StorageModel(
    val name: String = "",
    val list: List<StorageItem> = emptyList()
)
data class StorageItem(
    val type: String = "",
    val id: Int = 0,
    val posterPath: String = ""
)
{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as StorageItem

        if (type != other.type) return false
        if (id != other.id) return false
        if (posterPath != other.posterPath) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + id
        return result
    }
}

data class StorageKeyword(
    val list: List<String> = emptyList()
)