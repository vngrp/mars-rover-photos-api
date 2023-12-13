package fm.pim.mars

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import org.junit.jupiter.api.Test

class HexagonalArchitectureTest {
    @Test
    fun `hexagonal architecture layers have correct dependencies`() {
        Konsist
            .scopeFromProduction()
            .assertArchitecture {
                val domain = Layer("Domain", "fm.pim.mars.domain..")
                val application = Layer("Application", "fm.pim.mars.application..")
                val infrastructure = Layer("Infrastructure", "fm.pim.mars.infrastructure..")

                domain.dependsOnNothing()
                application.dependsOn(domain)
                infrastructure.dependsOn(application, domain)
            }
    }
}