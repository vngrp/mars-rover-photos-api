package fm.pim.mars.infrastructure.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FullRoversResponse(
    @SerialName("rovers")
    val rovers: List<Rover>
) {
    fun toDomainRovers() = rovers.map { rover ->
        fm.pim.mars.domain.Rover(
            name = rover.name,
            cameras = rover.cameras.map { camera ->
                fm.pim.mars.domain.Camera(
                    name = camera.name
                )
            }
        )
        }
    }

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
