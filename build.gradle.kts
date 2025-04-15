plugins {
    id("fabric-loom") version "1.10-SNAPSHOT"
    `maven-publish`
}

version = "1.0.0"
group = "dev.isxander"

base.archivesName = property("archives_base_name")!!.toString()

dependencies {
    // To change the versions see the gradle.properties file
    minecraft("com.mojang:minecraft:25w16a")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:0.16.13")
}

tasks.processResources {
    val props = mapOf(
        "version" to project.version,
    )

    inputs.properties(props)
    filesMatching("fabric.mod.json") {
        expand(props)
    }
}

publishing {
    repositories {
        val username = property("XANDER_MAVEN_USER")?.toString()
        val password = property("XANDER_MAVEN_PASS")?.toString()
        if (username != null && password != null) {
            maven(url = "https://maven.isxander.dev/releases") {
                name = "XanderReleases"
                credentials {
                    this.username = username
                    this.password = password
                }
            }
        } else {
            logger.warn("Xander Maven credentials not satisfied.")
        }
    }

    publications {
        register<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = "dev.isxander"
            artifactId = "mc296750-fix"
        }
    }
}

