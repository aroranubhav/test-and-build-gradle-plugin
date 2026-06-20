plugins {
    `kotlin-dsl`
    `maven-publish`
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("testAndBuild") {
            id = "io.maxi.testandbuild"
            implementationClass = "io.maxi.testandbuild.TestAndBuildPlugin"
        }
    }
}