package fm.pim.mars

import java.io.File
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MarsRoverPhotosApplication

fun main(args: Array<String>) {
	runApplication<MarsRoverPhotosApplication>(*args)
}

fun String.read() = File("src/test/kotlin/fm/pim/mars/$this").readText()