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
package com.vonage.quickstart.kt.jwt

import com.vonage.jwt.Jwt
import java.time.ZonedDateTime

fun main() {
    val token : String = Jwt.builder()
        .applicationId("aaaaaaaa-bbbb-cccc-dddd-0123456789ab")
        .privateKeyPath("/path/to/private.key")
        .issuedAt(ZonedDateTime.now())
        .subject("alice")
        .addClaim("acl", mapOf(
            "paths" to mapOf(
                "/*/rtc/**" to mapOf(),
                "/*/users/**" to mapOf<String, Any>(),
                "/*/conversations/**" to mapOf(),
                "/*/sessions/**" to mapOf(),
                "/*/devices/**" to mapOf(),
                "/*/image/**" to mapOf(),
                "/*/media/**" to mapOf(),
                "/*/knocking/**" to mapOf(),
                "/*/legs/**" to mapOf()
            )
        ))
        .build().generate()
}
