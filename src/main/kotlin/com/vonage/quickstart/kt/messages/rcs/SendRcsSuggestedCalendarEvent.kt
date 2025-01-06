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
        privateKeyPath(VONAGE_APPLICATION_PRIVATE_KEY_PATH)
    }

    val messageId = client.messages.send(
        rcsCustom {
            to(TO_NUMBER)
            from(RCS_SENDER_ID)
            custom(mapOf(
                "contentMessage" to mapOf(
                    "text" to "Product Launch: Save the date!",
                    "suggestions" to listOf(
                        mapOf(
                            "action" to mapOf(
                                "text" to "Save to calendar",
                                "postbackData" to "postback_data_1234",
                                "fallbackUrl" to "https://www.google.com/calendar",
                                "createCalendarEventAction" to mapOf(
                                    "startTime" to "2024-06-28T19:00:00Z",
                                    "endTime" to "2024-06-28T20:00:00Z",
                                    "title" to "Vonage API Product Launch",
                                    "description" to "Event to demo Vonage's new and exciting API product"
                                )
                            )
                        )
                    )
                )
            ))
        }
    )
}
