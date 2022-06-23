package dataModelOverview

data class Overview(val certificates: Certificates,
                    val releaseDate: String = "",
                    val ratings: Ratings,
                    val genres: List<String>?,
                    val plotOutline: PlotOutline,
                    val plotSummary: PlotSummary,
                    val id: String = "",
                    val title: Title)