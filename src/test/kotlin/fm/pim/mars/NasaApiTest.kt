package fm.pim.mars

import fm.pim.mars.application.MarsPhotosController
import fm.pim.mars.domain.Photo
import fm.pim.mars.domain.Rover
import fm.pim.mars.domain.contracts.NasaApi
import fm.pim.mars.infrastructure.dto.FullPhotosResponse
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test

class NasaApiTest {
    @Test
    fun `get rover names`(): Unit = runBlocking {
        val controller = MarsPhotosController(fakeApi)

        controller.getRovers() shouldBe listOf("Curiosity", "Spirit", "Opportunity", "Perseverance")
    }
}

val fakeApi = object : NasaApi {
    override suspend fun getAllPhotos(): List<Photo> {
        TODO("Not yet implemented")
    }

    override suspend fun getPhotos(rover: String): List<Photo> {
        assert(rover == "Curiosity") { "Only Curiosity is supported in this test" }
        "TestData.json".read().let { json ->
            return Json
                .decodeFromString<FullPhotosResponse>(json)
                .toDomainPhotos()
        }
    }

    override suspend fun getRovers() = listOf("Curiosity", "Spirit", "Opportunity", "Perseverance")
        .map { name -> Rover(name, emptyList()) }
}