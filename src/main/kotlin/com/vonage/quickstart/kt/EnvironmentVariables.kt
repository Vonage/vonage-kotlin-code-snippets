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

import com.vonage.client.ApiRegion
import com.vonage.client.numbers.CallbackType
import com.vonage.client.numbers.Feature
import com.vonage.client.numbers.SearchPattern
import com.vonage.client.numbers.Type
import com.vonage.client.numbers.UpdateNumberRequest
import com.vonage.client.verify.Psd2Request
import com.vonage.client.verify.VerifyRequest
import com.vonage.client.verify.Workflow
import com.vonage.client.voice.TextToSpeechLanguage
import java.time.Instant
import io.github.cdimascio.dotenv.Dotenv

private val dotenv = Dotenv.load()

fun envVar(name: String): String =
    dotenv[name] ?: throw IllegalStateException("Environment variable $name not set.")

// Auth / General
val VONAGE_API_KEY = envVar("VONAGE_API_KEY")
val VONAGE_API_SECRET = envVar("VONAGE_API_SECRET")
val VONAGE_SIGNATURE_SECRET = envVar("VONAGE_SIGNATURE_SECRET")
val VONAGE_APPLICATION_ID = envVar("VONAGE_APPLICATION_ID")
val VONAGE_PRIVATE_KEY_PATH = envVar("VONAGE_PRIVATE_KEY_PATH")
val VONAGE_VIRTUAL_NUMBER = envVar("VONAGE_VIRTUAL_NUMBER")

// Account
val ACCOUNT_ID = envVar("ACCOUNT_ID")
val ACCOUNT_SECRET = envVar("ACCOUNT_SECRET")
val ACCOUNT_SECRET_ID = envVar("ACCOUNT_SECRET_ID")
val ACCOUNT_SMS_CALLBACK_URL = envVar("ACCOUNT_SMS_CALLBACK_URL")

// Application
val APPLICATION_NAME = envVar("APPLICATION_NAME")

// Messages
val MESSAGES_GEOSPECIFIC_API_HOST: ApiRegion = ApiRegion.fromString(envVar("MESSAGES_GEOSPECIFIC_API_HOST"))
val MESSAGES_TO_NUMBER = envVar("MESSAGES_TO_NUMBER")
val MESSAGES_MESSAGE_ID = envVar("MESSAGES_MESSAGE_ID")
val MESSAGES_IMAGE_URL = envVar("MESSAGES_IMAGE_URL")
val MESSAGES_AUDIO_URL = envVar("MESSAGES_AUDIO_URL")
val MESSAGES_VIDEO_URL = envVar("MESSAGES_VIDEO_URL")
val MESSAGES_FILE_URL = envVar("MESSAGES_FILE_URL")
val MESSAGES_VCARD_URL = envVar("MESSAGES_VCARD_URL")
val MESSAGES_EMOJI = envVar("MESSAGES_EMOJI")
val MESSAGES_CAPTION = envVar("MESSAGES_CAPTION")

val SMS_SENDER_ID = envVar("SMS_SENDER_ID")
val MMS_SENDER_ID = envVar("MMS_SENDER_ID")
val RCS_SENDER_ID = envVar("RCS_SENDER_ID")
val WHATSAPP_SENDER_ID = envVar("WHATSAPP_SENDER_ID")
val VIBER_SENDER_ID = envVar("VIBER_SENDER_ID")
val MESSENGER_SENDER_ID = envVar("MESSENGER_SENDER_ID")
val MESSENGER_RECIPIENT_ID = envVar("MESSENGER_RECIPIENT_ID")

val WHATSAPP_TEMPLATE_NAME = envVar("WHATSAPP_TEMPLATE_NAME")
val WHATSAPP_OTP = envVar("WHATSAPP_OTP")
val WHATSAPP_CATALOG_ID = envVar("WHATSAPP_CATALOG_ID")
val WHATSAPP_PRODUCT_ID = envVar("WHATSAPP_PRODUCT_ID")
val WHATSAPP_STICKER_ID = envVar("WHATSAPP_STICKER_ID")
val WHATSAPP_STICKER_URL = envVar("WHATSAPP_STICKER_URL")
val WHATSAPP_HEADER_IMAGE_URL = envVar("WHATSAPP_HEADER_IMAGE_URL")
val WHATSAPP_TEMPLATE_NAMESPACE = envVar("WHATSAPP_TEMPLATE_NAMESPACE")
val WHATSAPP_AUTH_TEMPLATE_NAME = envVar("WHATSAPP_AUTH_TEMPLATE_NAME")
val WHATSAPP_TEMPLATE_REPLACEMENT_TEXT = envVar("WHATSAPP_TEMPLATE_REPLACEMENT_TEXT")

val VIBER_VIDEO_DURATION = envVar("VIBER_VIDEO_DURATION").toInt()
val VIBER_THUMB_URL = envVar("VIBER_THUMB_URL")
val VIBER_VIDEO_FILE_SIZE = envVar("VIBER_VIDEO_FILE_SIZE").toInt()
val VIBER_VIDEO_TTL = envVar("VIBER_VIDEO_TTL").toInt()

val MESSAGES_SANDBOX_VIBER_SERVICE_ID = envVar("MESSAGES_SANDBOX_VIBER_SERVICE_ID")
val MESSAGES_SANDBOX_ALLOW_LISTED_TO_NUMBER = envVar("MESSAGES_SANDBOX_ALLOW_LISTED_TO_NUMBER")
val MESSAGES_SANDBOX_FB_ID = envVar("MESSAGES_SANDBOX_FB_ID")
val MESSAGES_SANDBOX_ALLOW_LISTED_FB_RECIPIENT_ID = envVar("MESSAGES_SANDBOX_ALLOW_LISTED_FB_RECIPIENT_ID")
val MESSAGES_SANDBOX_WHATSAPP_NUMBER = envVar("MESSAGES_SANDBOX_WHATSAPP_NUMBER")

// Network APIs
val NV_MSISDN = envVar("NV_MSISDN")
val NV_REDIRECT_URI = envVar("NV_REDIRECT_URI")
val SIMSWAP_MSISDN = envVar("SIMSWAP_MSISDN")
val SIMSWAP_MAX_AGE = envVar("SIMSWAP_MAX_AGE").toInt()

// Number Insight
val INSIGHT_NUMBER = envVar("INSIGHT_NUMBER")
val INSIGHT_CALLBACK_URL = envVar("INSIGHT_CALLBACK_URL")

// Numbers
val NUMBER_MSISDN = envVar("NUMBER_MSISDN")
val NUMBER_COUNTRY_CODE = envVar("NUMBER_COUNTRY_CODE")
val NUMBER_TYPE = Type.fromString(envVar("NUMBER_TYPE"))
val NUMBER_FEATURES = envVar("NUMBER_FEATURES").split(",").map { Feature.fromString(it) }.toTypedArray()
val NUMBER_SEARCH_CRITERIA = envVar("NUMBER_SEARCH_CRITERIA")
val NUMBER_SEARCH_PATTERN = SearchPattern.entries[envVar("NUMBER_SEARCH_PATTERN").toInt()]
val NUMBER_SMS_CALLBACK_URL = envVar("NUMBER_SMS_CALLBACK_URL")
val NUMBER_VOICE_CALLBACK_URL = envVar("NUMBER_VOICE_CALLBACK_URL")
val NUMBER_VOICE_CALLBACK_TYPE = CallbackType.fromString(envVar("NUMBER_VOICE_CALLBACK_TYPE"))
val NUMBER_VOICE_STATUS_CALLBACK_URL = envVar("NUMBER_VOICE_STATUS_CALLBACK_URL")

// Pricing
val PRICING_COUNTRY_CODE = envVar("PRICING_COUNTRY_CODE")
val PRICING_DIAL_PREFIX = envVar("PRICING_DIAL_PREFIX")

// Redact
val VONAGE_REDACT_ID = envVar("VONAGE_REDACT_ID")

// SMS
val SMS_TO_NUMBER = envVar("SMS_TO_NUMBER")

// Subaccounts
val SUBACCOUNT_KEY = envVar("SUBACCOUNT_KEY")
val SUBACCOUNT_NAME = envVar("SUBACCOUNT_NAME")
val SUBACCOUNT_SECRET = envVar("SUBACCOUNT_SECRET")
val SUBACCOUNT_BALANCE_AMOUNT = envVar("SUBACCOUNT_BALANCE_AMOUNT").toDouble()
val SUBACCOUNT_CREDIT_AMOUNT = envVar("SUBACCOUNT_CREDIT_AMOUNT").toDouble()
val SUBACCOUNT_START_DATE = Instant.parse(envVar("SUBACCOUNT_START_DATE"))

// Users
val USER_ID = envVar("USER_ID")
val USER_NAME = envVar("USER_NAME")
val USER_DISPLAY_NAME = envVar("USER_DISPLAY_NAME")
val USER_NEW_NAME = envVar("USER_NEW_NAME")
val USER_NEW_DISPLAY_NAME = envVar("USER_NEW_DISPLAY_NAME")
val WEBSOCKET_URI = envVar("WEBSOCKET_URI")
val SIP_SECURE_URI = envVar("SIP_SECURE_URI")
val SIP_USERNAME = envVar("SIP_USERNAME")
val SIP_PASSWORD = envVar("SIP_PASSWORD")
val VBC_EXTENSION = envVar("VBC_EXTENSION").toInt()

// Verify
val VERIFY_NUMBER = envVar("VERIFY_NUMBER")
val VERIFY_BRAND_NAME = envVar("VERIFY_BRAND_NAME")
val VERIFY_PAYEE_NAME = envVar("VERIFY_PAYEE_NAME")
val VERIFY_AMOUNT = envVar("VERIFY_AMOUNT").toDouble()
val VERIFY_REQUEST_ID = envVar("VERIFY_REQUEST_ID")
val VERIFY_CODE = envVar("VERIFY_CODE")
val VERIFY_WORKFLOW_ID = Workflow.entries.get(envVar("VERIFY_WORKFLOW_ID").toInt() + 1)
val VERIFY_PSD2_WORKFLOW_ID = Workflow.entries.get(envVar("VERIFY_WORKFLOW_ID").toInt() + 1)
val VERIFY_TO_EMAIL = envVar("VERIFY_TO_EMAIL")
val VERIFY_FROM_EMAIL = envVar("VERIFY_FROM_EMAIL")
val VERIFY_WHATSAPP_NUMBER = envVar("VERIFY_WHATSAPP_NUMBER")
val VERIFY_TEMPLATE_NAME = envVar("VERIFY_TEMPLATE_NAME")
val VERIFY_TEMPLATE_ID = envVar("VERIFY_TEMPLATE_ID")
val VERIFY_TEMPLATE_FRAGMENT_ID = envVar("VERIFY_TEMPLATE_FRAGMENT_ID")

// Voice
val VOICE_CALL_ID = envVar("VOICE_CALL_ID")
val VOICE_TO_NUMBER = envVar("VOICE_TO_NUMBER")
val VOICE_TEXT = envVar("VOICE_TEXT")
val VOICE_LANGUAGE = TextToSpeechLanguage.valueOf(envVar("VOICE_LANGUAGE")) // TODO: Replace with fromString
val VOICE_DTMF_DIGITS = envVar("VOICE_DTMF_DIGITS")
val VOICE_CONFERENCE_NAME = envVar("VOICE_CONFERENCE_NAME")
val VOICE_NCCO_URL = envVar("VOICE_NCCO_URL")
val VOICE_ANSWER_URL = envVar("VOICE_ANSWER_URL")
val VOICE_STREAM_URL = envVar("VOICE_STREAM_URL")
val VOICE_RECORDING_URL = envVar("VOICE_RECORDING_URL")
