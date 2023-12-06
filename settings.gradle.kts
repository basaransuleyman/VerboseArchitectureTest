pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Satellites"
include(":app")
include(":data")
include(":domain")
include(":presentation")
include(":core")
include(":data:api")
include(":data:domain-impl")
include(":data:persistence")
