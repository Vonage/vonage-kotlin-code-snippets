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

internal val VONAGE_API_KEY = envVar("VONAGE_API_KEY")
internal val VONAGE_API_SECRET = envVar("VONAGE_API_SECRET")
internal val VONAGE_APPLICATION_ID = envVar("VONAGE_APPLICATION_ID")
internal val VONAGE_APPLICATION_PRIVATE_KEY_PATH = envVar("VONAGE_PRIVATE_KEY_PATH")

internal val TO_NUMBER = envVar("TO_NUMBER")
internal val VONAGE_FROM_NUMBER = envVar("VONAGE_FROM_NUMBER")
internal val VONAGE_BRAND_NAME = envVar("VONAGE_BRAND_NAME")
internal val RCS_SENDER_ID = envVar("RCS_SENDER_ID")
internal val VONAGE_WHATSAPP_NUMBER = envVar("VONAGE_WHATSAPP_NUMBER")
internal val VONAGE_VIBER_SERVICE_MESSAGE_ID = envVar("VONAGE_VIBER_SERVICE_MESSAGE_ID")
internal val FB_RECIPIENT_ID = envVar("FB_RECIPIENT_ID")
internal val VONAGE_FB_SENDER_ID = envVar("VONAGE_FB_SENDER_ID")

internal val MESSAGE_UUID = envVar("MESSAGE_UUID")
internal val VCARD_URL = envVar("VCARD_URL")
internal val VCARD_CAPTION = envVar("VCARD_CAPTION")
internal val IMAGE_URL = envVar("IMAGE_URL")
internal val IMAGE_CAPTION = envVar("IMAGE_CAPTION")
internal val AUDIO_URL = envVar("AUDIO_URL")
internal val AUDIO_CAPTION = envVar("AUDIO_CAPTION")
internal val VIDEO_URL = envVar("VIDEO_URL")
internal val VIDEO_CAPTION = envVar("VIDEO_CAPTION")
internal val THUMB_URL = envVar("THUMB_URL")
internal val FILE_URL = envVar("FILE_URL")
internal val FILE_CAPTION = envVar("FILE_CAPTION")
internal val STICKER_URL = envVar("STICKER_URL")
internal val STICKER_ID = envVar("STICKER_ID")
internal val EMOJI = envVar("EMOJI")
internal val OTP = envVar("OTP")
internal val HEADER_IMAGE_URL = envVar("HEADER_IMAGE_URL")
internal val WHATSAPP_TEMPLATE_NAMESPACE = envVar("WHATSAPP_TEMPLATE_NAMESPACE")
internal val WHATSAPP_TEMPLATE_NAME = envVar("WHATSAPP_TEMPLATE_NAME")
internal val WHATSAPP_AUTH_TEMPLATE_NAME = envVar("WHATSAPP_AUTH_TEMPLATE_NAME")
internal val WHATSAPP_TEMPLATE_REPLACEMENT_TEXT = envVar("WHATSAPP_TEMPLATE_REPLACEMENT_TEXT")
internal val CATALOG_ID = envVar("CATALOG_ID")
internal val PRODUCT_ID = envVar("PRODUCT_ID")
internal val FILE_SIZE = envVar("FILE_SIZE").toInt()
internal val DURATION = envVar("DURATION").toInt()
internal val TTL = envVar("TTL").toInt()


