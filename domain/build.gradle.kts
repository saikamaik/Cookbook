plugins {
    id("java-library")
    id("kotlin")
    id("kotlin-kapt")
    id("io.realm.kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_18
    targetCompatibility = JavaVersion.VERSION_18
}

dependencies {
    implementation("io.realm.kotlin:library-base:1.11.0")
}