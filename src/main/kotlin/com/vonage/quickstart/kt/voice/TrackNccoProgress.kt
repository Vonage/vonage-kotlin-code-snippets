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
package com.vonage.quickstart.kt.voice

import com.vonage.client.kt.notifyAction
import com.vonage.client.kt.talkAction
import com.vonage.client.voice.EventWebhook
import com.vonage.client.voice.ncco.Ncco
import io.ktor.server.application.call
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.request.path
import io.ktor.server.request.receive
import io.ktor.server.response.header
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing

fun main() {
    embeddedServer(Netty, port = 8000) {
        routing {
            get("/webhooks/answer") {
                call.response.header("Content-Type", "application/json")
                call.respond(
                    Ncco(
                        talkAction("Thanks for calling the notification line."),
                        notifyAction(
                            call.request.path().replace("answer", "notification"),
                            mapOf("foo" to "bar")
                        ),
                        talkAction("You will never hear me as the notification URL will return an NCCO")
                    ).toJson()
                )
            }
            post("/webhooks/notification") {
                val event = EventWebhook.fromJson(call.receive())
                call.response.header("Content-Type", "application/json")
                call.respond(
                    Ncco(
                        talkAction("Your notification has been received, loud and clear."),
                    ).toJson()
                )
            }
        }
    }.start(wait = true)
}
