package fm.pim.mars.application

import fm.pim.mars.domain.contracts.NasaApi
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class MarsPhotosController(private val api: NasaApi) {

    @GetMapping("/mars/rovers")
    suspend fun getRovers() = api.getRovers().map { it.name }

    @GetMapping("/mars/rovers/{rover}")
    suspend fun getRover(@PathVariable rover: String) = api
        .getPhotos(rover)
        .groupBy { it.rover.name }
        .mapValues { (_, photos) ->
            photos.map { it.imageUrl }
        }

    @GetMapping("/mars/cameras")
    suspend fun getCameras() = api
        .getAllPhotos()
        .flatMap { it.rover.cameras }
        .distinctBy { it.name }

    @GetMapping("/mars/cameras/{camera}")
    suspend fun getCamera(@PathVariable camera: String) = api
        .getAllPhotos()
        .filter { photo -> photo.rover.cameras.any { it.name == camera } }
        .groupBy { it.rover.name }
        .mapValues { (_, photos) ->
            photos.map { it.imageUrl }
        }

    @GetMapping("/")
    suspend fun availableRoutes() = mapOf(
        "GET /mars/rovers" to "Get all rovers",
        "GET /mars/rovers/{rover}" to "Get all photos of a rover",
        "GET /mars/cameras" to "Get all cameras",
        "GET /mars/cameras/{camera}" to "Get all photos of a camera"
    )
}

