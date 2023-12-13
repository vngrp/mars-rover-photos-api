package fm.pim.mars.infrastructure.dto


import fm.pim.mars.domain.Camera as DomainCamera
import fm.pim.mars.domain.Photo as DomainPhoto
import fm.pim.mars.domain.Rover as DomainRover
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FullPhotosResponse(
    @SerialName("photos")
    val photos: List<Photo>
) {
    fun toDomainPhotos() = photos.map { photo ->
        DomainPhoto(
            id = photo.id,
            rover = DomainRover(
                name = photo.rover.name,
                cameras = photo.rover.cameras.map { camera ->
                    DomainCamera(
                        name = camera.name
                    )
                }
            ),
            imageUrl = photo.imgSrc
        )
        }
    }

    @Serializable
    data class Photo(
        @SerialName("camera")
        val camera: Camera,
        @SerialName("earth_date")
        val earthDate: String,
        @SerialName("id")
        val id: Int,
        @SerialName("img_src")
        val imgSrc: String,
        @SerialName("rover")
        val rover: Rover,
        @SerialName("sol")
        val sol: Int
    ) {
        @Serializable
        data class Camera(
            @SerialName("full_name")
            val fullName: String,
            @SerialName("id")
            val id: Int,
            @SerialName("name")
            val name: String,
            @SerialName("rover_id")
            val roverId: Int
        )

        @Serializable
        data class Rover(
            @SerialName("cameras")
            val cameras: List<Camera>,
            @SerialName("id")
            val id: Int,
            @SerialName("landing_date")
            val landingDate: String,
            @SerialName("launch_date")
            val launchDate: String,
            @SerialName("max_date")
            val maxDate: String,
            @SerialName("max_sol")
            val maxSol: Int,
            @SerialName("name")
            val name: String,
            @SerialName("status")
            val status: String,
            @SerialName("total_photos")
            val totalPhotos: Int
        ) {
            @Serializable
            data class Camera(
                @SerialName("full_name")
                val fullName: String,
                @SerialName("name")
                val name: String
            )
        }
    }
