plugins {
    kotlin("jvm") version "1.5.10"
}

group = "ru.yoomoney"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.register<Copy>("testRuntimeClasspath") {
    from(configurations.testRuntimeClasspath)
    into("$buildDir/testRuntimeClasspath")
}

tasks.register<Copy>("runtimeClasspath") {
    from(configurations.runtimeClasspath)
    into("$buildDir/runtimeClasspath")
}

tasks.withType<Test> {
    useJUnitPlatform {
        includeEngines("junit-jupiter")
        excludeEngines("junit-vintage")


        isScanForTestClasses = true
        setTestNameIncludePatterns(listOf("*Test"))
        failFast = false

        testLogging {
            events("passed", "skipped", "failed")
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
            showStandardStreams = true
        }

        reports {
            junitXml.isEnabled = false
            html.isEnabled = false
        }
        binaryResultsDirectory.set(File("${project.buildDir}/binaryResult"))

        outputs.upToDateWhen { false } //отключаем кеширование тестовых результатов
    }
}
