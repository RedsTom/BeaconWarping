import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java-library")
    id("io.papermc.paperweight.userdev") version "1.4.1"
    id("xyz.jpenilla.run-paper") version "2.0.1"
    id("io.freefair.lombok") version "6.6.1"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "me.redstom"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.codemc.io/repository/maven-snapshots/")
}

dependencies {
    paperweightDevelopmentBundle("io.papermc.paper:dev-bundle:1.20.1-R0.1-SNAPSHOT")
    implementation("com.github.stefvanschie.inventoryframework:IF:0.10.11")

    compileOnly("org.hibernate.orm:hibernate-core:6.2.7.Final")
    compileOnly("org.hibernate.orm:hibernate-community-dialects:6.2.7.Final")
    compileOnly("org.postgresql:postgresql:42.6.0")

    compileOnly("com.google.inject:guice:7.0.0")
}

tasks {
    assemble {
        dependsOn("reobJar")
    }

    compileJava {
        options.encoding = "UTF-8"
        options.release.set(17)
    }

    javadoc {
        options.encoding = "UTF-8"
    }

    processResources {
        filteringCharset = "UTF-8"
    }
}

tasks.withType<ShadowJar> {
    relocate("com.github.stefvanschie.inventoryframework", "me.redstom.inventoryframework")
}