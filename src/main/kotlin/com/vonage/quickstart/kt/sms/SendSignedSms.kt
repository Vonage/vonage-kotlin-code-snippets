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
package com.vonage.quickstart.kt.sms

import com.vonage.client.auth.hashutils.HashUtil
import com.vonage.client.kt.*
import com.vonage.quickstart.kt.*

fun main() {
    val client = Vonage {
        apiKey(VONAGE_API_KEY)
        signatureSecret(VONAGE_SIGNATURE_SECRET)
        hashType(HashUtil.HashType.HMAC_SHA256)
    }

    val response = client.sms.sendText(
        from = SMS_SENDER_ID,
        to = SMS_TO_NUMBER,
        message = "Hello from Vonage SMS API"
    )

    if (response.wasSuccessfullySent()) {
        println("Message sent successfully.")
    }
    else {
        println("Message failed with error: ${response[0].errorText}")
    }
}
