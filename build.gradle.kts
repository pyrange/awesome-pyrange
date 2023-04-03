plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.12.0"
}

group = "com.pyrange"
version = "1.2"

repositories {
    mavenCentral()
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2020.1.2")
//    version.set("2021.1")
    type.set("IC") // Target IDE Platform

    plugins.set(listOf(/* Plugin Dependencies */))
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }

    patchPluginXml {
        sinceBuild.set("201")
        untilBuild.set("213.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}

dependencies {
    implementation("org.freemarker:freemarker:2.3.31")
    implementation("mysql:mysql-connector-java:8.0.31")
    implementation("mysql:mysql-connector-java:5.1.49")
    implementation("com.google.guava:guava:22.0")
}
