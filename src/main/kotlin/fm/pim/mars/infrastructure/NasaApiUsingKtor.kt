package fm.pim.mars.infrastructure

import arrow.fx.coroutines.parMap
import fm.pim.mars.domain.contracts.NasaApi
import fm.pim.mars.infrastructure.dto.FullPhotosResponse
import fm.pim.mars.infrastructure.dto.FullRoversResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import kotlinx.serialization.json.Json
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder.json
import org.springframework.stereotype.Service

@Service
object NasaApiUsingKtor : NasaApi {
    // NOTICE: Properly securing keys outside the scope of this demo.
    private const val KEY = "6f0viHHaub6yAJNuAUeyWxwc3E2Gm6p7zOTpCdgf"

    private val api by lazy {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json()
            }
        }
    }

    /**
     * Design tradeoff: Inefficient to getRovers name by Api call,
     *                  but it keeps working when a new rover lands on Mars.
     *
     *                  Getting photo's of each rover in parallel with parMap.
     */
    override suspend fun getAllPhotos() = getRovers()
        .map { it.name }
        .parMap { getPhotos(it) }
        .flatten()

    override suspend fun getPhotos(rover: String) = api
        .get("https://api.nasa.gov/mars-photos/api/v1/rovers/$rover/photos?sol=1000&api_key=$KEY")
        .body<String>()
        .let { Json.decodeFromString<FullPhotosResponse>(it).toDomainPhotos() }

    override suspend fun getRovers() = api
        .get("https://api.nasa.gov/mars-photos/api/v1/rovers?api_key=$KEY")
        .body<String>()
        .let { Json.decodeFromString<FullRoversResponse>(it).toDomainRovers() }
}