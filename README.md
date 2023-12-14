Hello!

For my API, I have chosen the NASA Mars photos. There are 855 photos of Mars. Each photo contains information about the Rover, and each Rover has information about the cameras on board. I want to make this available with REST routes for each rover and each camera.

This will allow for retrieving groups, for example, all photos from the Curiosity rover.

## Installation
After initializing the app, I added the following dependencies myself:
```kotlin
implementation("io.arrow-kt:arrow-core:1.2.0")

implementation("io.ktor:ktor-client-core:2.3.7")
implementation("io.ktor:ktor-client-cio:2.3.7")

testImplementation("io.kotest:kotest-assertions-core:5.7.2")
testImplementation("io.kotest:kotest-runner-junit5:5.7.2")
implementation("io.kotest.extensions:kotest-assertions-arrow:1.4.0")

testImplementation("com.lemonappdev:konsist:0.12.1")
```

I chose Arrow for its excellent functional programming capabilities, which might be useful. Ktor as the client for the NASA API, and Kotest for testing, with the Arrow extension for Arrow-specific tests.

## On-camera
You can view the first hour of programming this project here:
- [Part 1](https://drive.google.com/file/d/1C1cdlVcHIz8AsOqlq0SZl18VUHWmA-np/view)
- [Part 2](https://drive.google.com/file/d/1EB-Rhe4ERNd7080eToTmEHPP8HTLV_cW/view)

## Off-camera
After one hour of programming, I spent an additional 30-40 minutes to:
- Implement a Hexagonal architecture structure, along with an ArchUnit test to validate this architecture
- Quickly write the other controller routes
- Write a few tests for the controller
- Refactor everything to tidy it up a bit.

## Limitations
- Rover names are still case-sensitive, which makes the API a bit less user-friendly
- Network errors and related errors are not handled, so the API is not production-ready
- Missing API documentation and tests for all routes
- The fakeApi only works for the Curiosity rover data, as I initially thought this was all the data.

## "API Docs"
The API can be accessed via the following routes:
- GET `/` -> Provides a list of all available routes
- GET `/mars/rovers` -> Provides a list of all rover names
- GET `/mars/rovers/{rover}` -> Provides a list of all photos taken by a rover
- GET `/mars/cameras` -> Provides a list of camera types on board all rovers, grouped by rover
- GET `/mars/cameras/{camera}` -> Provides a list of all photos taken by a camera type

## Testing
The tests can be found in the `test` directory. The tests are written with Kotest and Konsist (for architecture).
'FHAZ' is a camera type present on 3 of the rovers, in case you want to test the last route.

## Running
The application can be run with the `./gradlew run` command. The application is accessible at `localhost:8080`.
