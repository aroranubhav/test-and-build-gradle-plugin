package io.maxi.testandbuild

import com.android.build.api.variant.AndroidComponentsExtension
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project

class TestAndBuildPlugin: Plugin<Project> {
    override fun apply(target: Project) {

        val androidComponents = target.extensions.findByType(
            AndroidComponentsExtension::class.java
        )

        if (androidComponents == null) {
            throw GradleException(
                "[testandbuild] No AndroidComponentsExtension found on " +
                        "'${target.path}'. Make sure an Android plugin " +
                        "(com.android.application or com.android.library) is " +
                        "applied BEFORE io.maxi.testandbuild in the plugins {} block."
            )
        }

        androidComponents.onVariants { variant ->

            val variantName = variant.name.replaceFirstChar {
                it.uppercase()
            }
            val taskName = "testAndAssemble$variantName"

            target.tasks.register(taskName) {
                group = "build"
                description = "Runs unit tests and assembles the $variantName variant in one step."

                val unitTestTask = "test${variantName}UniTest"
                val assembleTask = "assemble${variantName}"

                if (target.tasks.names.contains(unitTestTask)) {
                    dependsOn(target.tasks.named(unitTestTask))
                } else {
                    target.logger.warn(
                        "[testandbuild] '$unitTestTask' not found — " +
                                "unit tests may be disabled for the '$variantName' " +
                                "variant. '$taskName' will only assemble, not test."
                    )
                }

                if (target.tasks.names.contains(assembleTask)) {
                    dependsOn(target.tasks.named(assembleTask))
                } else {
                    target.logger.warn(
                        "[testandbuild] '$assembleTask' not found — " +
                                "skipping assemble step for the '$variantName' variant."
                    )
                }
            }
        }
    }
}