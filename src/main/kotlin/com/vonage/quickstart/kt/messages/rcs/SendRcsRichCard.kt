/*
 * Copyright 2025 Vonage
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.vonage.quickstart.kt.messages.rcs

import com.vonage.client.kt.*
import com.vonage.quickstart.kt.*

fun main() {
    val client = Vonage {
        applicationId(VONAGE_APPLICATION_ID)
        privateKeyPath(VONAGE_PRIVATE_KEY_PATH)
    }

    val messageId = client.messages.send(
        rcsCustom {
            to(MESSAGES_TO_NUMBER)
            from(RCS_SENDER_ID)
            custom(mapOf(
                "contentMessage" to mapOf(
                    "richCard" to mapOf(
                        "standaloneCard" to mapOf(
                            "thumbnailImageAlignment" to "RIGHT",
                            "cardOrientation" to "VERTICAL",
                            "cardContent" to mapOf(
                                "title" to "Quick question",
                                "description" to "Do you like this picture?",
                                "media" to mapOf(
                                    "height" to "TALL",
                                    "contentInfo" to mapOf(
                                        "fileUrl" to MESSAGES_IMAGE_URL,
                                        "forceRefresh" to "false"
                                    )
                                ),
                                "suggestions" to listOf(
                                    mapOf(
                                        "reply" to mapOf(
                                            "text" to "Yes",
                                            "postbackData" to "suggestion_1"
                                        )
                                    ),
                                    mapOf(
                                        "reply" to mapOf(
                                            "text" to "I love it!",
                                            "postbackData" to "suggestion_2"
                                        )
                                    )
                                )
                            )
                        )
                    )
                )
            ))
        }
    )
}
