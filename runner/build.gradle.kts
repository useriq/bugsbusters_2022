tasks.register<Test>("integrationTest") {

    testClassesDirs = files("/testClasses")

    classpath =
        files("/testClasses") + files("/resources") + files("/testResources") + fileTree("/app/libs") + fileTree(
            "/testRuntimeClasspath"
        )

    println(files(testClassesDirs))

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

/*doFirst {
    logger.debug("start task integrationTest - tests from Docker")
    logger.debug("classpath" + classpath.files)
    logger.debug("testClassesDirs" + testClassesDirs.files)
}*/

/*useJUnitPlatform {
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
}*/


