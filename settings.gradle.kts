pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "CoffeeGo"
include(":app")
include(":feature:home:presentation")
include(":feature:detail:presentation")
include(":core:navigation")
include(":core:data")
include(":core:ui")
include(":core:designsystem")
include(":lab")
include(":feature:home:data")
include(":feature:home:domain")
include(":core:domain")
include(":feature:splash:presentation")
include(":feature:search:presentation")
include(":feature:search:data")
include(":feature:search:domain")
include(":feature:dashboard:presentation")
include(":feature:favorite:presentation")
include(":feature:favorite:data")
include(":feature:favorite:domain")
include(":feature:setting:presentation")
