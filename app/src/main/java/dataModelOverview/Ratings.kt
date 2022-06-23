package dataModelOverview

data class Ratings(val canRate: Boolean = false,
                   val rating: Double = 0.0,
                   val ratingCount: Int = 0,
                   val topRank: Int = 0)