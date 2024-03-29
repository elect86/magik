/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package magik

import java.io.File
import org.gradle.testkit.runner.GradleRunner
import kotlin.test.Test
import kotlin.test.assertTrue

/**
 * A simple functional test for the 'magik.greeting' plugin.
 */
class MagikPluginFunctionalTest {
    @Test fun `can run task`() {
        // Setup the test build
        val projectDir = File("build/functionalTest").apply {
            mkdirs()
            resolve("settings.gradle").writeText("")
            resolve("build.gradle.kts").writeText("""
                import magik.createGithubPublication
                import magik.github
                import org.gradle.kotlin.dsl.`maven-publish`
                
                plugins {
                    id("elect86.magik")
                    `maven-publish`
                    `java-library`
                }
                
                version = "0.1"
                group = "groupTest"
                
                publishing {
                    publications.createGithubPublication {
                        artifactId = "artifactTest"
                        //suppressPomMetadataWarningsFor("apiElements")
                    }
                    repositories {
                        github {
                            domain = "elect86/magik-test"
                        }
                    }
                }""")
        }

        // Run the build
        val runner = GradleRunner.create()
        runner.forwardOutput()
        runner.withPluginClasspath()
        runner.withArguments("tasks")
        runner.withProjectDir(projectDir)
        val result = runner.build()

        // Verify the result
        //        assertTrue(result.output.contains("Hello from plugin 'magik.greeting'"))
    }
}
