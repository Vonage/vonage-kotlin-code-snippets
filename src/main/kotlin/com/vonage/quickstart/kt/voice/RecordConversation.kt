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

import com.vonage.client.kt.conversationAction
import com.vonage.client.voice.EventWebhook
import com.vonage.client.voice.ncco.EventMethod
import com.vonage.client.voice.ncco.Ncco
import com.vonage.quickstart.kt.VOICE_CONFERENCE_NAME
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
                        conversationAction(VOICE_CONFERENCE_NAME) {
                            record(true)
                            eventMethod(EventMethod.POST)
                            eventUrl(call.request.path().replace("answer", "recordings"))
                        },
                    ).toJson()
                )
            }
            post("/webhooks/recordings") {
                val event = EventWebhook.fromJson(call.receive())
                println("Recording URL: ${event.recordingUrl}")
                call.respond(204)
            }
        }
    }.start(wait = true)
}
