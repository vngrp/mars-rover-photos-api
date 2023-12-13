Hoi!

Als API heb ik de Nasa Mars photo's gekozen. Er zijn 855 foto's van
Mars gemaakt. Elke foto heeft informatie over de Rover en elke Rover heeft informatie over de camera's 
aan boord. Ik wil dit beschikbaar stellen met Rest routes per rover en per camera. 

Om zo telkens een groepering terug te krijgen, bijvoorbeeld alle foto's van de Curiosity rover.

## Installatie
De volgende dependencies heb ik zelf toegevoegd na het initialiseren van de app:
```kotlin
	implementation("io.arrow-kt:arrow-core:1.2.0")

	implementation("io.ktor:ktor-client-core:2.3.7")
	implementation("io.ktor:ktor-client-cio:2.3.7")

	testImplementation("io.kotest:kotest-assertions-core:5.7.2")
	testImplementation("io.kotest:kotest-runner-junit5:5.7.2")
	implementation("io.kotest.extensions:kotest-assertions-arrow:1.4.0")

    testImplementation("com.lemonappdev:konsist:0.12.1")
```

Arrow omdat dit mooie functioneel programmeer mogelijkheden biedt. Wellicht komt dit van pas.
Ktor als client voor de NASA API. En Kotest voor de tests, met de Arrow extensie voor Arrow specifieke tests.

## Off-camera
Na het 1 uur programmeren heb ik nog ongeveer 30-40 min gebruikt om de volgende dingen te doen:
- Een Hexagonal architecture structuur aanbrengen, samen met een ArchUnit test om deze architectuur te valideren
- De controller routes snel verder geschreven
- Een paar tests geschreven voor de controller
- Alles een beetje netjes refactoren.

## Limitations
- Rover names are still case-sensitive, which makes the API a bit less user-friendly
- Network errors and related errors are not handled, so the API is not production ready
- Missing API doc and tests for all routes
- Delen van deze documentatie zijn engels en andere delen Engelstalig. Dit is niet consistent.
- The fakeApi only works for the Curiosity rover data, because I initially thought these were all rovers.

## "API Docs"
De API is te benaderen via de volgende routes:
- GET `/` -> Geeft een lijst van alle beschikbare routes
- GET `/mars/rovers` -> Geeft een lijst van alle rover namen
- GET `/mars/rovers/{roverName}` -> Geeft een lijst van alle foto's gemaakt door een rover
- GET `/mars/cameras` -> Geeft een lijst van camera types aan boord van alle rovers, gegroepeerd per rover
- GET `/mars/cameras/{cameraName}` -> Geeft een lijst van alle foto's gemaakt door een camera type

## Testen
De tests zijn te vinden in de `test` map. De tests zijn geschreven met Kotest en Konsist (voor architectuur).
'FHAZ' is een camera type die op 3 van de rovers aanwezig is voor als je de laatste route wilt testen.

## Runnen
De applicatie is te runnen met de `./gradlew run` commando. De applicatie is te benaderen op `localhost:8080`.