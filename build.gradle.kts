plugins {
    id("java")
    id("software.amazon.smithy.gradle.smithy-base").version("1.2.0")
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

sourceSets {
    main {
        smithy {
            srcDir("includes")
        }
    }
}