/*
 * Copyright 2024 Vonage
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
        privateKeyPath(VONAGE_APPLICATION_PRIVATE_KEY_PATH)
    }

    val messageId = client.messages.send(
        rcsCustom {
            to(TO_NUMBER)
            from(RCS_SENDER_ID)
            custom(mapOf(
                "contentMessage" to mapOf(
                    "carouselCard" to mapOf(
                        "cardWidth" to "MEDIUM",
                        "cardContents" to listOf(
                            mapOf(
                                "title" to "Option 1: Photo",
                                "description" to "Do you prefer this photo?",
                                "suggestions" to listOf(
                                    mapOf(
                                        "reply" to mapOf(
                                            "text" to "Option 1",
                                            "postbackData" to "card_1"
                                        )
                                    )
                                ),
                                "media" to mapOf(
                                    "height" to "MEDIUM",
                                    "contentInfo" to mapOf(
                                        "fileUrl" to IMAGE_URL,
                                        "forceRefresh" to "false"
                                    )
                                )
                            ),
                            mapOf(
                                "title" to "Option 2: Video",
                                "description" to "Or this video?",
                                "suggestions" to listOf(
                                    mapOf(
                                        "reply" to mapOf(
                                            "text" to "Option 2",
                                            "postbackData" to "card_2"
                                        )
                                    )
                                ),
                                "media" to mapOf(
                                    "height" to "MEDIUM",
                                    "contentInfo" to mapOf(
                                        "fileUrl" to VIDEO_URL,
                                        "forceRefresh" to "false"
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
