package me.tatocaster.marvelapp.data.api.response


data class InnerData(
        var available: Int,
        var collectionURI: String,
        var items: List<Item>,
        var returned: Int
)
