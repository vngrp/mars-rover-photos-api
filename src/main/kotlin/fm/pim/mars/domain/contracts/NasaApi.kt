package fm.pim.mars.domain.contracts

import fm.pim.mars.domain.Photo
import fm.pim.mars.domain.Rover

interface NasaApi {
    suspend fun getAllPhotos(): List<Photo>
    suspend fun getPhotos(rover: String): List<Photo>
    suspend fun getRovers(): List<Rover>
}