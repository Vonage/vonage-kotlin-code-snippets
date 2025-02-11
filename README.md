# Vonage Quickstart Examples for Kotlin

![SLOC](https://sloc.xyz/github/Vonage/vonage-kotlin-code-snippets)
[![Contributor Covenant](https://img.shields.io/badge/Contributor%20Covenant-2.1-4baaaa.svg)](CODE_OF_CONDUCT.md)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Community Slack](https://img.shields.io/badge/Slack-4A154B?style=flat&logo=slack&logoColor=white)](https://developer.vonage.com/community/slack)

Kotlin code samples for [Vonage APIs](https://developer.vonage.com/en/api) using
the [Kotlin Server SDK](https://github.com/Vonage/vonage-kotlin-sdk).
These are used in the [Vonage developer portal](https://developer.vonage.com/en/documentation).

Quickstarts also available for:
- [cURL](https://github.com/Vonage/vonage-curl-code-snippets)
- [Java](https://github.com/Vonage/vonage-java-code-snippets)
- [Python](https://github.com/Vonage/vonage-python-code-snippets)
- [.NET](https://github.com/Vonage/vonage-dotnet-code-snippets)
- [Node.js](https://github.com/Vonage/vonage-node-code-snippets)
- [PHP](https://github.com/Vonage/vonage-php-code-snippets)
- [Ruby](https://github.com/Vonage/vonage-ruby-code-snippets)

## Setup
**See the [Getting Started guide](https://developer.vonage.com/en/getting-started/overview)
if you are new to Vonage for an overview.**

To use this sample you will first need a [Vonage account](https://dashboard.nexmo.com/sign-up?utm_source=DEV_REL&utm_medium=github&utm_campaign=java-client-library).
For some of the examples you may need to [buy a number](https://dashboard.nexmo.com/buy-numbers).
Most APIs will require you to [create an application](https://dashboard.nexmo.com/applications).

## Running The Examples
1. Clone or download the repo (see the green `Code` button above).
2. Open the project in your IDE - IntelliJ IDEA or Visual Studio Code are recommended.
3. Run `gradlew assemble` to build the project. 
   1. To work with a local copy of the SDK or any of its dependencies, run `mvn install` from the respective repo.
   2. To work with a different / specific version of the SDK or any dependencies, edit the `build.gradle.kts` file.
4. Copy `.env-example` to `.env` and edit the values. The [EnvironmentVariables.kt](src/main/kotlin/com/vonage/quickstart/kt/EnvironmentVariables.kt) file is where they're loaded.
5. Use your IDE to run the examples you are interested in.

## Searchable Examples
See [SNIPPETS.md](SNIPPETS.md) for an aggregated list of all the code in this repository. You can then
Ctrl+F search this file to find what you're looking for. 
