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
package com.vonage.quickstart.kt

fun envVar(name: String): String =
    System.getenv(name) ?: throw IllegalStateException("Environment variable $name not set.")

val VONAGE_API_KEY = envVar("VONAGE_API_KEY")
val VONAGE_API_SECRET = envVar("VONAGE_API_SECRET")
val VONAGE_APPLICATION_ID = envVar("VONAGE_APPLICATION_ID")
val VONAGE_APPLICATION_PRIVATE_KEY_PATH = envVar("VONAGE_PRIVATE_KEY_PATH")

val TO_NUMBER = envVar("TO_NUMBER")
val VONAGE_FROM_NUMBER = envVar("VONAGE_FROM_NUMBER")
val VONAGE_BRAND_NAME = envVar("VONAGE_BRAND_NAME")
val RCS_SENDER_ID = envVar("RCS_SENDER_ID")
val VONAGE_WHATSAPP_NUMBER = envVar("VONAGE_WHATSAPP_NUMBER")
val VONAGE_VIBER_SERVICE_MESSAGE_ID = envVar("VONAGE_VIBER_SERVICE_MESSAGE_ID")
val FB_RECIPIENT_ID = envVar("FB_RECIPIENT_ID")
val VONAGE_FB_SENDER_ID = envVar("VONAGE_FB_SENDER_ID")
val MESSAGES_SANDBOX_VIBER_SERVICE_ID = envVar("MESSAGES_SANDBOX_VIBER_SERVICE_ID")
val MESSAGES_SANDBOX_ALLOW_LISTED_TO_NUMBER = envVar("MESSAGES_SANDBOX_ALLOW_LISTED_TO_NUMBER")
val MESSAGES_SANDBOX_FB_ID = envVar("MESSAGES_SANDBOX_FB_ID")
val MESSAGES_SANDBOX_ALLOW_LISTED_FB_RECIPIENT_ID = envVar("MESSAGES_SANDBOX_ALLOW_LISTED_FB_RECIPIENT_ID")
val MESSAGES_SANDBOX_WHATSAPP_NUMBER = envVar("MESSAGES_SANDBOX_WHATSAPP_NUMBER")

val MESSAGE_UUID = envVar("MESSAGE_UUID")
val VCARD_URL = envVar("VCARD_URL")
val VCARD_CAPTION = envVar("VCARD_CAPTION")
val IMAGE_URL = envVar("IMAGE_URL")
val IMAGE_CAPTION = envVar("IMAGE_CAPTION")
val AUDIO_URL = envVar("AUDIO_URL")
val AUDIO_CAPTION = envVar("AUDIO_CAPTION")
val VIDEO_URL = envVar("VIDEO_URL")
val VIDEO_CAPTION = envVar("VIDEO_CAPTION")
val VIDEO_DURATION = envVar("DURATION").toInt()
val THUMB_URL = envVar("THUMB_URL")
val FILE_URL = envVar("FILE_URL")
val FILE_CAPTION = envVar("FILE_CAPTION")
val FILE_SIZE = envVar("FILE_SIZE").toInt()
val STICKER_URL = envVar("STICKER_URL")
val STICKER_ID = envVar("STICKER_ID")
val EMOJI = envVar("EMOJI")
val OTP = envVar("OTP")
val HEADER_IMAGE_URL = envVar("HEADER_IMAGE_URL")
val WHATSAPP_TEMPLATE_NAMESPACE = envVar("WHATSAPP_TEMPLATE_NAMESPACE")
val WHATSAPP_TEMPLATE_NAME = envVar("WHATSAPP_TEMPLATE_NAME")
val WHATSAPP_AUTH_TEMPLATE_NAME = envVar("WHATSAPP_AUTH_TEMPLATE_NAME")
val WHATSAPP_TEMPLATE_REPLACEMENT_TEXT = envVar("WHATSAPP_TEMPLATE_REPLACEMENT_TEXT")
val CATALOG_ID = envVar("CATALOG_ID")
val PRODUCT_ID = envVar("PRODUCT_ID")
val TTL = envVar("TTL").toInt()

