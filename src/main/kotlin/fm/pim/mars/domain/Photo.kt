package fm.pim.mars.domain

data class Photo(
    val id: Int,
    val rover: Rover,
    val imageUrl: String
)