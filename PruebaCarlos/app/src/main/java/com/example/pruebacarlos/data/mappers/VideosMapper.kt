package com.example.pruebacarlos.data.mappers

import com.example.pruebacarlos.core.Mapper
import com.example.pruebacarlos.data.model.ResultsItem
import com.example.pruebacarlos.data.model.VideosLocal

class VideosMapper : Mapper<List<ResultsItem?>?, List<VideosLocal>> {

    override suspend fun map(input: List<ResultsItem?>?): List<VideosLocal> {
        return input?.map {
            it?.toVideoLocal() ?: VideosLocal("", "", "", false, "", "")
        } ?: listOf()
    }
}

private fun ResultsItem.toVideoLocal(): VideosLocal {
    return VideosLocal(
        id = this.id ?: "",
        name = this.name ?: "",
        type = this.type ?: "",
        oficial = this.official ?: false,
        url = this.key ?: "",
        site = this.site ?: ""
    )
}
