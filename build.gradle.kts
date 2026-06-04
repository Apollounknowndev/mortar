plugins {
    kotlin("jvm") version "2.1.0"
    id("earth.terrarium.cloche") version "0.18.10"
}

repositories {
    cloche {
        mavenNeoforgedMeta()
        mavenNeoforged()
        mavenForge()
        mavenFabric()
        mavenParchment()
        librariesMinecraft()
        main()
    }
    mavenCentral()
    maven("https://api.modrinth.com/maven")
}

group = "dev.worldgen.mortar"
version = "3.1.3"

cloche {
    mappings {
        official()
    }

    metadata {
        modId = "mortar"
        name = "Mortar"
        description = "A relatively lightweight building mod that aims to fix some of the gaps in the vanilla block sets."
        license = "MIT"
        icon = "assets/mortar/icon.png"

        author("Apollo")
        author("DawnKiro (Texture Artist)")
        author("Kyrius (Texture Artist)")
        author("Ekansh (Texture Artist)")
        author("skxlor (Texture Artist)")
        author("bebebea_loste (Texture Artist)")
    }

    singleTarget {
        fabric {
            loaderVersion = "0.19.0"
            minecraftVersion = "26.1"
            mixins.from(file("src/main/mortar.mixins.json"))

            dependencies {
                fabricApi("0.144.3")
                modRuntimeOnly("maven.modrinth:lithostitched:1.6.4-fabric-26.1")
            }

            includedClient()
            runs {
                client()
                server()
            }

            metadata {
                dependencies {
                    dependency {
                        modId = "lithostitched"
                    }
                }

                entrypoint("main") {
                    value = "dev.worldgen.mortar.Mortar"
                }

                entrypoint("client") {
                    value = "dev.worldgen.mortar.MortarClient"
                }
            }
        }
    }
}