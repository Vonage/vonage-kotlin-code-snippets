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
package com.vonage.quickstart.kt.messages.whatsapp

import com.vonage.client.kt.*
import com.vonage.client.common.MessageType
import com.vonage.client.messages.whatsapp.*
import com.vonage.quickstart.kt.*

fun main() {
    val client = Vonage {
        applicationId(VONAGE_APPLICATION_ID)
        privateKeyPath(VONAGE_PRIVATE_KEY_PATH)
    }

    val messageId = client.messages.send(
        whatsappCustom {
            to(MESSAGES_TO_NUMBER)
            from(WHATSAPP_SENDER_ID)
            custom(mapOf(
                "type" to MessageType.TEMPLATE,
                "template" to mapOf(
                    "namespace" to WHATSAPP_TEMPLATE_NAMESPACE,
                    "name" to WHATSAPP_TEMPLATE_NAME,
                    "language" to mapOf(
                        "code" to Locale.ENGLISH,
                        "policy" to Policy.DETERMINISTIC
                    ),
                    "components" to listOf(
                        mapOf(
                            "type" to "header",
                            "parameters" to listOf(
                                mapOf(
                                    "type" to MessageType.IMAGE,
                                    "image" to mapOf(
                                        "link" to WHATSAPP_HEADER_IMAGE_URL
                                    )
                                )
                            )
                        ),
                        mapOf(
                            "type" to "body",
                            "parameters" to listOf(
                                mapOf(
                                    "type" to MessageType.TEXT,
                                    "text" to "Anand"
                                ),
                                mapOf(
                                    "type" to MessageType.TEXT,
                                    "text" to "Quest"
                                ),
                                mapOf(
                                    "type" to MessageType.TEXT,
                                    "text" to "113-0921387"
                                ),
                                mapOf(
                                    "type" to MessageType.TEXT,
                                    "text" to "23rd Nov 2019"
                                )
                            )
                        ),
                        mapOf(
                            "type" to MessageType.BUTTON,
                            "index" to 0,
                            "sub_type" to "url",
                            "parameters" to listOf(
                                mapOf(
                                    "type" to MessageType.TEXT,
                                    "text" to "1Z999AA10123456784"
                                )
                            )
                        )
                    )
                )
            ))
        }
    )
}
