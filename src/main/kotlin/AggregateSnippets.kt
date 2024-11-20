import java.io.File
import java.nio.charset.Charset
import java.nio.file.Paths
import java.nio.file.StandardOpenOption.*
import kotlin.io.path.deleteIfExists
import kotlin.io.path.writeText

fun main() {
    val repoRoot = Paths.get("").toAbsolutePath()
    val snippetsSrc = repoRoot.resolve("src/main/kotlin/com/vonage/quickstart/kt").toFile()

    val contentBuilder = StringBuilder(8192).appendLine("# Vonage Kotlin Code Snippets").appendLine()

    snippetsSrc.listFiles().sortedBy { !it.isInitialize() }.forEach {
        it.appendSnippetContent(contentBuilder)
    }

    val destFileName = "SNIPPETS.md"
    val destPath = repoRoot.resolve(destFileName)
    destPath.deleteIfExists()
    destPath.writeText(contentBuilder.toString(), Charset.defaultCharset(), CREATE_NEW, WRITE)
}

fun File.isInitialize(): Boolean = name.endsWith("initialize")

fun File.appendSnippetContent(contentBuilder: StringBuilder, level: Int = 2) {
    if (name == "EnvironmentVariables.kt") return

    contentBuilder.append("#".repeat(level)).append(' ')
        .appendLine(nameWithoutExtension.toHeadingTitle())

    if (isDirectory) {
        listFiles()?.forEach { it.appendSnippetContent(contentBuilder, level + 1) }
    }
    else if (level > 2) {
        val fileContent = readText()
        val nugget = fileContent.substringAfter(
            if (!parentFile.isInitialize() && fileContent.contains("val client = Vonage {")) '}' else '{'
        ).substringBeforeLast('}').trim().lines().joinToString("\n") {
            it.removePrefix("    ")
        }
        contentBuilder.appendLine("```kotlin").appendLine(nugget).appendLine("```").appendLine()
    }
}

fun String.toHeadingTitle(): String {
    var result = this.replaceFirstChar { it.uppercase() }
        .replace(Regex("(?<!^)([A-Z])"), " $1")
        .replace("numberinsight", "Number Insight", true)
        .replace("whatsapp", "WhatsApp", true)

    for (acronym in arrayOf("jwt", "url", "id", "sms", "rcs", "mms", "psd2", "dtmf", "asr", "tts", "ncco", "rtc")) {
        result = result.replace(acronym, acronym.uppercase(), true)
    }
    return result
}
