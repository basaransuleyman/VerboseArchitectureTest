# tech

![CI Status](https://github.com/basaransuleyman/tech/actions/workflows/ci.yml/badge.svg)


## Architecture
Working on 

## Data Flow with Reactive Programming

It uses Kotlin Flows to manage data flow by applying reactive programming principles, including State & Shared flow and Cold flow.

## CI Workflow

Each push action or pull request to the master branch is used as a trigger.
The following processes are carried out on the Ubuntu-Latest working environment:
    -The repository is pulled.
    -Java JDK 18 is installed.
    -The project is compiled with Gradle.
    -Tests are run with Gradle.

## Performance Improvements
### Careful Context Usage
Attention has been paid to the use of Context within the application, avoiding unnecessary Context connections. This prevents unnecessary memory consumption and ensures the application runs faster.

### Strong References Control
The use of strong references is considered to prevent unnecessary memory leaks. Critical data structures and objects are managed better by avoiding unnecessary strong references and are defined as nullable where needed.

### Operations According to Fragment Lifecycle
 Attention has been paid to the lifecycle of Fragments and appropriate nullable operations have been performed. This reduces the likelihood of errors during transitions between Fragments and ensures more stable operation of the application.

## Dependency Catalog

A list and versions of dependencies used in the project are compiled in a TOML file named libs.versions.toml. This file contains the dependencies and their versions used in the project and is managed from a single point.

## Configuration Change

This project uses View Models for data management and lifecycle management of your Android application. View Models are used to effectively store your data and share it between activities or fragments. They also help preserve your data during configuration changes (e.g., screen rotation).

## Security Keys

Sensitive information in the project (e.g., API keys) is stored in the BUILD_CONFIG file.
This file is not added to the git repository to increase the security of the project and prevent leakage of sensitive information.
Below, you can find an example overview of the BUILD_CONFIG file:

```kotlin
object BuildConfig {
    const val BASE_URL = "https://xxx/"
}
 
