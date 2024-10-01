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
package com.vonage.quickstart.kt.messages.viber

import com.vonage.client.kt.Vonage
import com.vonage.client.kt.viberText
import com.vonage.quickstart.kt.envVar

val VONAGE_APPLICATION_ID = envVar("VONAGE_APPLICATION_ID")
val VONAGE_APPLICATION_PRIVATE_KEY_PATH = envVar("VONAGE_PRIVATE_KEY_PATH")
val TO_NUMBER = envVar("TO_NUMBER")
val VONAGE_VIBER_SERVICE_MESSAGE_ID = envVar("VONAGE_VIBER_SERVICE_MESSAGE_ID")

fun main() {
    val client = Vonage {
        applicationId(VONAGE_APPLICATION_ID)
        privateKeyPath(VONAGE_APPLICATION_PRIVATE_KEY_PATH)
    }

    val messageId = client.messages.send(viberText {
        to(TO_NUMBER); from(VONAGE_VIBER_SERVICE_MESSAGE_ID)
        text("This is a Viber text message sent using the Messages API")
    })
}
