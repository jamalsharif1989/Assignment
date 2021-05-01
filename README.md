
# Android MVVM Architecture using Kotlin and Architectural Components

# Overview

1. MVVM Architectural pattern
2. Navigation component for fragment transactions
3. Dagger2 for Dependency injection
4. Gradle scripts for running sonarqube static code analysis, code coverage, etc.
5. Unit test demonstration using JUnit and Mockito
6. UI unit test demonstartion using Espresso


# Architecture
![alt text](https://s3.ap-south-1.amazonaws.com/mindorks-server-uploads/mvvm.png)

This Application has been designed using **Android Architecture components** and **MVVM Architectural Pattern**
The main reason of using MVVM, Its recommended by google and there is no two way dependency between ViewModel and Model unlike MVP. Here the view can observe the data changes in the viewmodel as we are using LiveData which is lifecycle aware. The viewmodel to view communication is achieved through observer pattern (basically observing the state changes of the data in the viewmodel).

# Programming Practices Followed
a) Android Architectural Components <br/>
b) Dagger 2 for Dependency Injection <br/>
c) MVVM <br/>
d) Retrofit with Okhttp and LiveData<br/>
e) JUnit and espresso for Unit testing <br/>
f) Repository pattern <br/>
g) JSoup for HTML Parsing

## Structure

* `build.gradle` root gradle config file
* `app` project main folder
* `app/build.gradle` project gradle config file
* `app/com/android/articleapp/common` contains application constants
* `app/com/android/articleapp/data` contains information about sources, models and implementation using Repository pattern
* `app/com/android/articleapp/di` contains all Dependency Injection classes, implemented using dagger2
* `app/com/android/articleapp/view` contains all android framework related implementation, using MVVM with databinding
* `app/com/android/articleapp/utils` contains utility classes

# Screenshots
<img src="/screenshots/ArticleList.png" width="346" height="615" alt="Home"/>
<img src="/screenshots/Search.png" width="346" height="615" alt="Home"/>
<img src="/screenshots/ArticleDetail.png" width="346" height="615" alt="Home"/>

# How to build ?

Open terminal and type the below command to generate debug build <br/>

``` ./gradlew assembleDebug ```

Open terminal and type the below command to generate release build <br/>

``` ./gradlew assembleRelease ```

# Sonarqube Report

<img src="/sonarqube/sonarqube.png" width="936" height="410" alt="Home"/>

<br/>

# How to generate Sonarqube report ?

Install and configure sonarqube server locally.
Open gradle.properties and update the below line with the sonarqube server url

```systemProp.sonar.host.url=http://localhost:9000```

Before running the sonarqube job, make sure the project version has been updated in the build.gradle. On every run, increment the version by 1.<br/>

```
               property "sonar.projectName", "ArticleApplication" // Name of your project
               property "sonar.projectKey", "assignment123" // Name of your project
               property "sonar.projectVersion", "1.0.0" // Version of your project
               property "sonar.projectDescription", "Assignment"
               property "sonar.sources", "src/main/java" // add which ever you want
               property "sonar.binaries", "build/intermediates/classes/debug"
               property "sonar.tests", "src/test/java, src/androidTest/java"
               property "sonar.coverage.jacoco.xmlReportPaths", "${project.buildDir}/reports/jacoco/createTestReport/createTestReport.xml"
               property "sonar.jacoco.reportPaths", "${project.buildDir}/jacoco/testDevelopmentDebugUnitTest.exec"
               property "sonar.java.coveragePlugin", "jacoco"
               property "sonar.junit.reportsPath", "build/test-results/testDevelopmentDebugUnitTest"
               property "sonar.host.url", "http://localhost:9000" // Define Port Address
               property "sonar.login", "5a86ac40650ce2518c57807abb531e55cd0a437e" // update login key
```

For running the sonarqube job, type the below command in the terminal. <br/>

```./gradlew sonarqube assembleDebug```

<br/>

# Sample Code Coverage

Wrote basic test cases using Junit and espresso for classes as <br/>

1. ArticleList<br/>
2. Api
