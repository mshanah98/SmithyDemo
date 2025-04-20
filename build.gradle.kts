plugins {
    id("java")
    id("software.amazon.smithy.gradle.smithy-base").version("1.2.0")
    id("maven-publish")
}

group = "org.mshanah"
version = "1.0-SNAPSHOT"

smithy {}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    // Core Smithy dependency
    implementation("software.amazon.smithy:smithy-model:1.56.0")

    // TypeScript code generation
    implementation("software.amazon.smithy.typescript:smithy-typescript-codegen:0.29.0")

    // AWS API Gateway and Lambda integration
    implementation("software.amazon.smithy:smithy-aws-traits:1.56.0")
    implementation("software.amazon.smithy:smithy-aws-apigateway-traits:1.56.0")

    // OpenAPI generator - needed for the 'openapi' plugin in smithy-build.json
    implementation("software.amazon.smithy:smithy-openapi:1.56.0")
}

tasks.register<Jar>("openApiJar") {
    archiveBaseName.set("openapi-spec")
    from("build/smithyprojections/SmithyDemo/openapi") {
        include("openapi.yaml")
        into("openapi") // Puts it in `openapi/openapi.yaml` in the jar
    }
}

publishing {
    publications {
        create<MavenPublication>("openapiSpec") {
            artifact(tasks.named("openApiJar"))
            groupId = "com.mshanah98"
            artifactId = "openapi-spec"
            version = "1.0.0-SNAPSHOT"
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/mshanah98/SmithyDemo")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

sourceSets {
    main {
        smithy {
            srcDir("includes")
        }
    }
}