package me.tatocaster.marvelapp.data.api.response

data class Result(
        val comics: InnerData,
        val description: String,
        val events: InnerData,
        val id: Int,
        val modified: String,
        val name: String,
        val resourceURI: String,
        val series: InnerData,
        val stories: InnerData,
        val thumbnail: Thumbnail,
        val urls: List<Url>
)