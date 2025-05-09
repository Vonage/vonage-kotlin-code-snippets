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

import com.vonage.client.sms.MessageEvent
import io.ktor.server.application.call
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.request.contentType
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

fun main() {
    embeddedServer(Netty, port = 8000) {
        routing {
            route("/webhooks/inbound-sms") {
                handle {
                    if (call.request.contentType().equals("application/x-www-form-urlencoded")) {
                        println("msisdn: ${call.request.queryParameters["msisdn"]}")
                        println("messageId: ${call.request.queryParameters["messageId"]}")
                        println("text: ${call.request.queryParameters["text"]}")
                        println("type: ${call.request.queryParameters["type"]}")
                        println("keyword: ${call.request.queryParameters["keyword"]}")
                        println("messageTimestamp: ${call.request.queryParameters["messageTimestamp"]}")
                    }
                    else {
                        val messageEvent = MessageEvent.fromJson(call.receive())
                        println(messageEvent.toJson())
                    }
                    call.respond(204)
                }
            }
        }
    }.start(wait = true)
}
