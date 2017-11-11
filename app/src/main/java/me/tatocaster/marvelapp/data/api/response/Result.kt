package me.tatocaster.marvelapp.data.api.response

data class Result(
        val characters: InnerData,
        val collectedIssues: List<Any>,
        val collections: List<Any>,
        val creators: InnerData,

        val dates: List<Date>,
        val description: String,
        val diamondCode: String,
        val digitalId: Int,
        val ean: String,
        val events: InnerData,

        val format: String,
        val id: Int,
        val images: List<Thumbnail>,
        val isbn: String,
        val issn: String,
        val issueNumber: Int,
        val modified: String,
        val pageCount: Int,
        val prices: List<Price>,
        val resourceURI: String,
        val series: Series,
        val stories: InnerData,
        val textObjects: List<Any>,
        val thumbnail: Thumbnail,
        val title: String,
        val upc: String,
        val urls: List<Url>,
        val valiantDescription: String,
        val valiants: List<Any>
)