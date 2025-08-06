plugins {
    kotlin("jvm") version "2.1.0"
    id("earth.terrarium.cloche") version "0.11.0"
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
version = "3.1.1"

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
        author("Ekansh (Texture Artist)")
        author("skxlor (Texture Artist)")
        author("bebebea_loste (Texture Artist)")
    }

    singleTarget {
        fabric {
            loaderVersion = "0.16.13"
            minecraftVersion = "1.21.6"
            mixins.from(file("src/main/mortar.mixins.json"))

            dependencies {
                fabricApi("0.128.0")
                modRuntimeOnly("maven.modrinth:lithostitched:1.4.8-fabric-1.21.5")
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
                        required = true
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