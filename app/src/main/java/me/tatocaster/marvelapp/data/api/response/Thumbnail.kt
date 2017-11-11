package me.tatocaster.marvelapp.data.api.response


data class Thumbnail(
        val extension: String,
        val path: String
) {
    fun getFullUrl(): String = path + "." + extension
}
