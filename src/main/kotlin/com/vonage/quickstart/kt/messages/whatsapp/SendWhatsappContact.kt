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
import com.vonage.client.messages.whatsapp.*
import com.vonage.quickstart.kt.*

fun main() {
    val client = Vonage {
        applicationId(VONAGE_APPLICATION_ID)
        privateKeyPath(VONAGE_APPLICATION_PRIVATE_KEY_PATH)
    }

    val messageId = client.messages.send(
        whatsappCustom {
            to(TO_NUMBER)
            from(VONAGE_WHATSAPP_NUMBER)
            custom(mapOf(
                "type" to "contacts",
                "contacts" to listOf(
                    mapOf(
                        "addresses" to listOf(
                            mapOf(
                                "city" to "Menlo Park",
                                "country" to "United States",
                                "state" to "CA",
                                "country_code" to "us",
                                "street" to "1 Hacker Way",
                                "type" to "HOME",
                                "zip" to "94025"
                            ),
                            mapOf(
                                "city" to "Menlo Park",
                                "country" to "United States",
                                "state" to "CA",
                                "country_code" to "us",
                                "street" to "200 Jefferson Dr",
                                "type" to "WORK",
                                "zip" to "94025"
                            )
                        ),
                        "birthday" to "2012-08-18",
                        "emails" to listOf(
                            mapOf(
                                "email" to "test@fb.com",
                                "type" to "WORK"
                            ),
                            mapOf(
                                "email" to "test@whatsapp.com",
                                "type" to "WORK"
                            )
                        ),
                        "name" to mapOf(
                            "first_name" to "Jayden",
                            "last_name" to "Smith",
                            "formatted_name" to "J. Smith"
                        ),
                        "org" to mapOf(
                            "company" to "WhatsApp",
                            "department" to "Design",
                            "title" to "Manager"
                        ),
                        "phones" to listOf(
                            mapOf(
                                "phone" to "+1 (940) 555-1234",
                                "type" to "HOME"
                            ),
                            mapOf(
                                "phone" to "+1 (650) 555-1234",
                                "type" to "WORK",
                                "wa_id" to "16505551234"
                            )
                        ),
                        "urls" to listOf(
                            mapOf(
                                "url" to "https://www.facebook.com",
                                "type" to "WORK"
                            )
                        )
                    )
                )
            ))
        }
    )
}
