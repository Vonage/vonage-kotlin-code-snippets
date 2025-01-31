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
package com.vonage.quickstart.kt

import com.vonage.client.numbers.Feature
import com.vonage.client.numbers.SearchPattern
import com.vonage.client.numbers.Type
import com.vonage.client.numbers.UpdateNumberRequest
import com.vonage.client.verify.VerifyRequest

fun envVar(name: String): String =
    System.getenv(name) ?: throw IllegalStateException("Environment variable $name not set.")

val VONAGE_API_KEY = envVar("VONAGE_API_KEY")
val VONAGE_API_SECRET = envVar("VONAGE_API_SECRET")
val VONAGE_SIGNATURE_SECRET = envVar("VONAGE_SIGNATURE_SECRET")
val VONAGE_APPLICATION_ID = envVar("VONAGE_APPLICATION_ID")
val VONAGE_PRIVATE_KEY_PATH = envVar("VONAGE_PRIVATE_KEY_PATH")
val VONAGE_APPLICATION_PRIVATE_KEY_PATH = envVar("VONAGE_APPLICATION_PRIVATE_KEY_PATH")

val VONAGE_NUMBER = envVar("VONAGE_NUMBER")
val TO_NUMBER = envVar("TO_NUMBER")
val VONAGE_FROM_NUMBER = envVar("VONAGE_FROM_NUMBER")
val VONAGE_BRAND_NAME = envVar("VONAGE_BRAND_NAME")
val RCS_SENDER_ID = envVar("RCS_SENDER_ID")
val VONAGE_WHATSAPP_NUMBER = envVar("VONAGE_WHATSAPP_NUMBER")
val WHATSAPP_BUSINESS_NUMBER = envVar("WHATSAPP_BUSINESS_NUMBER")
val VONAGE_VIBER_SERVICE_MESSAGE_ID = envVar("VONAGE_VIBER_SERVICE_MESSAGE_ID")
val FB_RECIPIENT_ID = envVar("FB_RECIPIENT_ID")
val VONAGE_FB_SENDER_ID = envVar("VONAGE_FB_SENDER_ID")
val MESSAGES_SANDBOX_VIBER_SERVICE_ID = envVar("MESSAGES_SANDBOX_VIBER_SERVICE_ID")
val MESSAGES_SANDBOX_ALLOW_LISTED_TO_NUMBER = envVar("MESSAGES_SANDBOX_ALLOW_LISTED_TO_NUMBER")
val MESSAGES_SANDBOX_FB_ID = envVar("MESSAGES_SANDBOX_FB_ID")
val MESSAGES_SANDBOX_ALLOW_LISTED_FB_RECIPIENT_ID = envVar("MESSAGES_SANDBOX_ALLOW_LISTED_FB_RECIPIENT_ID")
val MESSAGES_SANDBOX_WHATSAPP_NUMBER = envVar("MESSAGES_SANDBOX_WHATSAPP_NUMBER")

val VONAGE_REDACT_ID = envVar("VONAGE_REDACT_ID")
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

val PSTN = envVar("PSTN")
val USER_ID = envVar("USER_ID")
val USER_NAME = envVar("USER_NAME")
val USER_DISPLAY_NAME = envVar("USER_DISPLAY_NAME")
val USER_NEW_NAME = envVar("USER_NEW_NAME")
val USER_NEW_DISPLAY_NAME = envVar("USER_NEW_DISPLAY_NAME")
val WEBSOCKET_URI = envVar("WEBSOCKET_URI")
val SIP_URI = envVar("SIP_URI")
val SIP_SECURE_URI = envVar("SIP_SECURE_URI")
val SIP_USERNAME = envVar("SIP_USERNAME")
val SIP_PASSWORD = envVar("SIP_PASSWORD")
val VBC_EXTENSION = envVar("VBC_EXTENSION").toInt()

val SUBACCOUNT_KEY = envVar("SUBACCOUNT_KEY")
val NEW_SUBACCOUNT_NAME = envVar("NEW_SUBACCOUNT_NAME")
val NEW_SUBACCOUNT_SECRET = envVar("NEW_SUBACCOUNT_SECRET")
val AMOUNT = envVar("AMOUNT").toDouble()
val COUNTRY_CODE = envVar("COUNTRY_CODE")
val DIAL_PREFIX = envVar("DIAL_PREFIX")
val VONAGE_NUMBER_TYPE = Type.fromString(envVar("VONAGE_NUMBER_TYPE"))
val VONAGE_NUMBER_FEATURES = envVar("VONAGE_NUMBER_FEATURES").split(",")
    .map { Feature.fromString(it) }.toTypedArray()
val SMS_CALLBACK_URL = envVar("SMS_CALLBACK_URL")
val VOICE_CALLBACK_TYPE = UpdateNumberRequest.CallbackType.fromString(envVar("VOICE_CALLBACK_TYPE"))
val VOICE_CALLBACK_VALUE = envVar("VOICE_CALLBACK_VALUE")
val VOICE_STATUS_URL = envVar("VOICE_STATUS_URL")
val SECRET_ID = envVar("SECRET_ID")
val NEW_SECRET = envVar("NEW_SECRET")
val NUMBER_SEARCH_CRITERIA = envVar("NUMBER_SEARCH_CRITERIA")
val NUMBER_SEARCH_PATTERN = SearchPattern.entries[envVar("NUMBER_SEARCH_PATTERN").toInt()]
val INSIGHT_NUMBER = envVar("INSIGHT_NUMBER")
val INSIGHT_CALLBACK_URL = envVar("INSIGHT_CALLBACK_URL")
val REQUEST_ID = envVar("REQUEST_ID")
val CODE = envVar("CODE")
val RECIPIENT_NUMBER = envVar("RECIPIENT_NUMBER")
val BRAND_NAME = envVar("BRAND_NAME")
val PAYEE_NAME = envVar("PAYEE_NAME")
val WORKFLOW_ID = VerifyRequest.Workflow.entries[(envVar("WORKFLOW_ID").toInt()) - 1]
val TO_EMAIL = envVar("TO_EMAIL")
val TEMPLATE_ID = envVar("TEMPLATE_ID")
val TEMPLATE_FRAGMENT_ID = envVar("TEMPLATE_FRAGMENT_ID")


val ANSWER_URL = envVar("ANSWER_URL")
val YOUR_SECOND_NUMBER = envVar("YOUR_SECOND_NUMBER")
val CALL_UUID = envVar("CALL_UUID")
val DIGITS = envVar("DIGITS")
val TEXT = envVar("TEXT")
val CONF_NAME = envVar("CONF_NAME")
val NCCO_URL = envVar("NCCO_URL")
val RECORDING_URL = envVar("RECORDING_URL")
