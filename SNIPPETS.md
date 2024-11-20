# Vonage Kotlin Code Snippets

## Initialize
### Full Auth
```kotlin
val client = Vonage {
    apiKey(VONAGE_API_KEY)
    apiSecret(VONAGE_API_SECRET)
    applicationId(VONAGE_APPLICATION_ID)
    privateKeyPath(VONAGE_APPLICATION_PRIVATE_KEY_PATH)
}
```

## JWT
### Validate Inbound JWT
```kotlin
embeddedServer(Netty, port = 8000) {
    routing {
        get("/webhooks/validatejwt") {
            val token = call.request.header("Authorization")?.substring(7)
            if (Jwt.verifySignature(token, VONAGE_SIGNATURE_SECRET)) {
                call.respond(HttpStatusCode.NoContent, "Signature is valid.")
            }
            else {
                call.respond(HttpStatusCode.Unauthorized, "Invalid signature.")
            }
        }
    }
}.start(wait = true)
```

### Generate JWT
```kotlin
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
```

## Subaccounts
### Get Subaccount
```kotlin
val subaccount = client.subaccounts.subaccount(SUBACCOUNT_KEY).get()
```

### Transfer Number
```kotlin
val receipt = client.subaccounts.transferNumber(
    from = VONAGE_API_KEY,
    to = SUBACCOUNT_KEY,
    number = VONAGE_FROM_NUMBER,
    country = COUNTRY_CODE
)
```

### List Credit Transfers
```kotlin
val transfers = client.subaccounts.listCreditTransfers()
```

### Transfer Balance
```kotlin
val receipt = client.subaccounts.transferBalance(
    from = VONAGE_API_KEY,
    to = SUBACCOUNT_KEY,
    amount = AMOUNT
)
```

### Transfer Credit
```kotlin
val receipt = client.subaccounts.transferCredit(
    from = VONAGE_API_KEY,
    to = SUBACCOUNT_KEY,
    amount = AMOUNT
)
```

### Reactivate Subaccount
```kotlin
val subaccount = client.subaccounts.subaccount(SUBACCOUNT_KEY).suspended(false)
```

### Suspend Subaccount
```kotlin
val subaccount = client.subaccounts.subaccount(SUBACCOUNT_KEY).suspended(true)
```

### Create Subaccount
```kotlin
val subaccount = client.subaccounts.createSubaccount(
    name = NEW_SUBACCOUNT_NAME,
    secret = NEW_SUBACCOUNT_SECRET
)
```

### List Subaccounts
```kotlin
val subaccounts = client.subaccounts.listSubaccounts()
```

### List Balance Transfers
```kotlin
val transfers = client.subaccounts.listBalanceTransfers()
```

## Messages
### Incoming Message
```kotlin
embeddedServer(Netty, port = 8000) {
    routing {
        post ("/webhooks/inbound-message") {
            val messageDetails = InboundMessage.fromJson(call.receive())
            println("Message ID "+messageDetails.getMessageUuid()+" of type " +
                    messageDetails.getMessageType()+" was sent from " +
                    messageDetails.getFrom()+" to "+messageDetails.getTo()+" via "+
                    messageDetails.getChannel()+" at "+messageDetails.getTimestamp()
            )
            call.respondText("OK")
        }
    }
}.start(wait = true)
```

### SMS
#### Send SMS Text
```kotlin
val messageId = client.messages.send(
    smsText {
        to(TO_NUMBER)
        from(VONAGE_BRAND_NAME)
        text("This is an SMS text message sent using the Messages API")
    }
)
```

### Message Status Webhook
```kotlin
embeddedServer(Netty, port = 8000) {
    routing {
        post ("/webhooks/message-status") {
            val messageDetails = MessageStatus.fromJson(call.receive())
            println(
                "Message ID ${messageDetails.getMessageUuid()}" +
                "(status ${messageDetails.getStatus()}) was sent at" +
                "${messageDetails.getTimestamp()} from ${messageDetails.getFrom()} " +
                "to ${messageDetails.getTo()} via ${messageDetails.getChannel()} " +
                "using ${messageDetails.getChannel()}."
            )
            call.respondText("OK")
        }
    }
}.start(wait = true)
```

### RCS
#### Send RCS Suggested Reply
```kotlin
val messageId = client.messages.send(
    rcsCustom {
        to(TO_NUMBER)
        from(RCS_SENDER_ID)
        custom(mapOf(
            "contentMessage" to mapOf(
                "text" to "What do you think of Vonage APIs?",
                "suggestions" to listOf(
                    mapOf(
                        "reply" to mapOf(
                            "text" to "They're great!",
                            "postbackData" to "suggestion_1"
                        )
                    ),
                    mapOf(
                        "reply" to mapOf(
                            "text" to "They're awesome!",
                            "postbackData" to "suggestion_2"
                        )
                    )
                )
            )
        ))
    }
)
```

#### Send RCS Suggested Share Location
```kotlin
val messageId = client.messages.send(
    rcsCustom {
        to(TO_NUMBER)
        from(RCS_SENDER_ID)
        custom(mapOf(
            "contentMessage" to mapOf(
                "text" to "Your driver will come and meet you at your specified location.",
                "suggestions" to listOf(
                    mapOf(
                        "action" to mapOf(
                            "text" to "Share a location",
                            "postbackData" to "postback_data_1234",
                            "shareLocationAction" to emptyMap<String, Any>()
                        )
                    )
                )
            )
        ))
    }
)
```

#### Send RCS Suggested Dial Number
```kotlin
val messageId = client.messages.send(
    rcsCustom {
        to(TO_NUMBER)
        from(RCS_SENDER_ID)
        custom(mapOf(
            "contentMessage" to mapOf(
                "text" to "Call us to claim your free gift!",
                "suggestions" to listOf(
                    mapOf(
                        "action" to mapOf(
                            "text" to "Call now!",
                            "postbackData" to "postback_data_1234",
                            "fallbackUrl" to "https://www.example.com/contact/",
                            "dialAction" to mapOf(
                                "phoneNumber" to "+447900000000"
                            )
                        )
                    )
                )
            )
        ))
    }
)
```

#### Send RCS Rich Card Carousel
```kotlin
val messageId = client.messages.send(
    rcsCustom {
        to(TO_NUMBER)
        from(RCS_SENDER_ID)
        custom(mapOf(
            "contentMessage" to mapOf(
                "carouselCard" to mapOf(
                    "cardWidth" to "MEDIUM",
                    "cardContents" to listOf(
                        mapOf(
                            "title" to "Option 1: Photo",
                            "description" to "Do you prefer this photo?",
                            "suggestions" to listOf(
                                mapOf(
                                    "reply" to mapOf(
                                        "text" to "Option 1",
                                        "postbackData" to "card_1"
                                    )
                                )
                            ),
                            "media" to mapOf(
                                "height" to "MEDIUM",
                                "contentInfo" to mapOf(
                                    "fileUrl" to IMAGE_URL,
                                    "forceRefresh" to "false"
                                )
                            )
                        ),
                        mapOf(
                            "title" to "Option 2: Video",
                            "description" to "Or this video?",
                            "suggestions" to listOf(
                                mapOf(
                                    "reply" to mapOf(
                                        "text" to "Option 2",
                                        "postbackData" to "card_2"
                                    )
                                )
                            ),
                            "media" to mapOf(
                                "height" to "MEDIUM",
                                "contentInfo" to mapOf(
                                    "fileUrl" to VIDEO_URL,
                                    "forceRefresh" to "false"
                                )
                            )
                        )
                    )
                )
            )
        ))
    }
)
```

#### Send RCS Suggested Open URL
```kotlin
val messageId = client.messages.send(
    rcsCustom {
        to(TO_NUMBER)
        from(RCS_SENDER_ID)
        custom(mapOf(
            "contentMessage" to mapOf(
                "text" to "Check out our latest offers!",
                "suggestions" to listOf(
                    mapOf(
                        "action" to mapOf(
                            "text" to "Open product page",
                            "postbackData" to "postback_data_1234",
                            "openUrlAction" to mapOf(
                                "url" to "https://example.com/product"
                            )
                        )
                    )
                )
            )
        ))
    }
)
```

#### Send RCS File
```kotlin
val messageId = client.messages.send(
    rcsFile {
        to(TO_NUMBER)
        from(RCS_SENDER_ID)
        url(FILE_URL)
    }
)
```

#### Send RCS Suggested Multiple Actions
```kotlin
val messageId = client.messages.send(
    rcsCustom {
        to(TO_NUMBER)
        from(RCS_SENDER_ID)
        custom(mapOf(
            "contentMessage" to mapOf(
                "text" to "Need some help? Call us now or visit our website for more information.",
                "suggestions" to listOf(
                    mapOf(
                        "action" to mapOf(
                            "text" to "Call us",
                            "postbackData" to "postback_data_1234",
                            "fallbackUrl" to "https://www.example.com/contact/",
                            "dialAction" to mapOf(
                                "phoneNumber" to "+447900000000"
                            )
                        )
                    ),
                    mapOf(
                        "action" to mapOf(
                            "text" to "Visit site",
                            "postbackData" to "postback_data_1234",
                            "openUrlAction" to mapOf(
                                "url" to "http://example.com/"
                            )
                        )
                    )
                )
            )
        ))
    }
)
```

#### Revoke Message
```kotlin
client.messages.existingMessage(MESSAGE_UUID, ApiRegion.API_US).revoke()
```

#### Send RCS Text
```kotlin
val messageId = client.messages.send(
    rcsText {
        to(TO_NUMBER)
        from(RCS_SENDER_ID)
        text("This is an RCS text message sent using the Messages API")
    }
)
```

#### Send RCS Suggested View Location
```kotlin
val messageId = client.messages.send(
    rcsCustom {
        to(TO_NUMBER)
        from(RCS_SENDER_ID)
        custom(mapOf(
            "contentMessage" to mapOf(
                "text" to "Drop by our office!",
                "suggestions" to listOf(
                    mapOf(
                        "action" to mapOf(
                            "text" to "View map",
                            "postbackData" to "postback_data_1234",
                            "fallbackUrl" to "https://www.google.com/maps/place/Vonage/@51.5230371,-0.0852492,15z",
                            "viewLocationAction" to mapOf(
                                "latLong" to mapOf(
                                    "latitude" to 51.5230371,
                                    "longitude" to -0.0852492
                                ),
                                "label" to "Vonage London Office"
                            )
                        )
                    )
                )
            )
        ))
    }
)
```

#### Send RCS Rich Card
```kotlin
val messageId = client.messages.send(
    rcsCustom {
        to(TO_NUMBER)
        from(RCS_SENDER_ID)
        custom(mapOf(
            "contentMessage" to mapOf(
                "richCard" to mapOf(
                    "standaloneCard" to mapOf(
                        "thumbnailImageAlignment" to "RIGHT",
                        "cardOrientation" to "VERTICAL",
                        "cardContent" to mapOf(
                            "title" to "Quick question",
                            "description" to "Do you like this picture?",
                            "media" to mapOf(
                                "height" to "TALL",
                                "contentInfo" to mapOf(
                                    "fileUrl" to IMAGE_URL,
                                    "forceRefresh" to "false"
                                )
                            ),
                            "suggestions" to listOf(
                                mapOf(
                                    "reply" to mapOf(
                                        "text" to "Yes",
                                        "postbackData" to "suggestion_1"
                                    )
                                ),
                                mapOf(
                                    "reply" to mapOf(
                                        "text" to "I love it!",
                                        "postbackData" to "suggestion_2"
                                    )
                                )
                            )
                        )
                    )
                )
            )
        ))
    }
)
```

#### Send RCS Video
```kotlin
val messageId = client.messages.send(
    rcsVideo {
        to(TO_NUMBER)
        from(RCS_SENDER_ID)
        url(VIDEO_URL)
    }
)
```

#### Send RCS Image
```kotlin
val messageId = client.messages.send(
    rcsImage {
        to(TO_NUMBER)
        from(RCS_SENDER_ID)
        url(IMAGE_URL)
    }
)
```

#### Send RCS Suggested Calendar Event
```kotlin
val messageId = client.messages.send(
    rcsCustom {
        to(TO_NUMBER)
        from(RCS_SENDER_ID)
        custom(mapOf(
            "contentMessage" to mapOf(
                "text" to "Product Launch: Save the date!",
                "suggestions" to listOf(
                    mapOf(
                        "action" to mapOf(
                            "text" to "Save to calendar",
                            "postbackData" to "postback_data_1234",
                            "fallbackUrl" to "https://www.google.com/calendar",
                            "createCalendarEventAction" to mapOf(
                                "startTime" to "2024-06-28T19:00:00Z",
                                "endTime" to "2024-06-28T20:00:00Z",
                                "title" to "Vonage API Product Launch",
                                "description" to "Event to demo Vonage's new and exciting API product"
                            )
                        )
                    )
                )
            )
        ))
    }
)
```

### MMS
#### Send MMS Video
```kotlin
val messageId = client.messages.send(
    mmsVideo {
        to(TO_NUMBER)
        from(VONAGE_FROM_NUMBER)
        url(VIDEO_URL)
        caption(VIDEO_CAPTION)
    }
)
```

#### Send MMS Vcard
```kotlin
val messageId = client.messages.send(
    mmsVcard {
        to(TO_NUMBER)
        from(VONAGE_FROM_NUMBER)
        url(VCARD_URL)
        caption(VCARD_CAPTION)
    }
)
```

#### Send MMS Image
```kotlin
val messageId = client.messages.send(
    mmsImage {
        to(TO_NUMBER)
        from(VONAGE_FROM_NUMBER)
        url(IMAGE_URL)
        caption(IMAGE_CAPTION)
    }
)
```

#### Send MMS Audio
```kotlin
val messageId = client.messages.send(
    mmsAudio {
        to(TO_NUMBER)
        from(VONAGE_FROM_NUMBER)
        url(AUDIO_URL)
        caption(AUDIO_CAPTION)
    }
)
```

### Messenger
#### Send Messenger Audio
```kotlin
val messageId = client.messages.send(
    messengerAudio {
        to(FB_RECIPIENT_ID)
        from(VONAGE_FB_SENDER_ID)
        url(AUDIO_URL)
    }
)
```

#### Send Messenger Image
```kotlin
val messageId = client.messages.send(
    messengerImage {
        to(FB_RECIPIENT_ID)
        from(VONAGE_FB_SENDER_ID)
        url(IMAGE_URL)
    }
)
```

#### Send Messenger Video
```kotlin
val messageId = client.messages.send(
    messengerVideo {
        to(FB_RECIPIENT_ID)
        from(VONAGE_FB_SENDER_ID)
        url(VIDEO_URL)
    }
)
```

#### Send Messenger Text
```kotlin
val messageId = client.messages.send(
    messengerText {
        to(FB_RECIPIENT_ID)
        from(VONAGE_FB_SENDER_ID)
        text("This is a Facebook Messenger text message sent using the Messages API")
    }
)
```

#### Send Messenger File
```kotlin
val messageId = client.messages.send(
    messengerFile {
        to(FB_RECIPIENT_ID)
        from(VONAGE_FB_SENDER_ID)
        url(FILE_URL)
    }
)
```

### Viber
#### Send Viber File
```kotlin
val messageId = client.messages.send(
    viberFile {
        to(TO_NUMBER)
        from(VONAGE_VIBER_SERVICE_MESSAGE_ID)
        url(FILE_URL)
    }
)
```

#### Send Viber Image
```kotlin
val messageId = client.messages.send(
    viberImage {
        to(TO_NUMBER)
        from(VONAGE_VIBER_SERVICE_MESSAGE_ID)
        url(IMAGE_URL)
    }
)
```

#### Send Viber Video
```kotlin
val messageId = client.messages.send(
    viberVideo {
        to(TO_NUMBER)
        from(VONAGE_VIBER_SERVICE_MESSAGE_ID)
        url(VIDEO_URL)
        thumbUrl(THUMB_URL)
        category(Category.TRANSACTION)
        ttl(TTL)
        fileSize(FILE_SIZE)
        duration(VIDEO_DURATION)
    }
)
```

#### Send Viber Text
```kotlin
val messageId = client.messages.send(
    viberText {
        to(TO_NUMBER)
        from(VONAGE_VIBER_SERVICE_MESSAGE_ID)
        text("This is a Viber text message sent using the Messages API")
    }
)
```

### Sandbox
#### Messenger
##### Send Messenger Video
```kotlin
println(client.messages.send(
    messengerVideo {
        to(MESSAGES_SANDBOX_ALLOW_LISTED_FB_RECIPIENT_ID)
        from(MESSAGES_SANDBOX_FB_ID)
        url(VIDEO_URL)
    },
    sandbox = true
))
```

##### Send Messenger Text
```kotlin
println(client.messages.send(
    messengerText {
        to(MESSAGES_SANDBOX_ALLOW_LISTED_FB_RECIPIENT_ID)
        from(MESSAGES_SANDBOX_FB_ID)
        text("This is a Facebook Messenger text message sent using the Messages API")
    },
    sandbox = true
))
```

#### Viber
##### Send Viber Video
```kotlin
println(client.messages.send(
    viberVideo {
        to(MESSAGES_SANDBOX_ALLOW_LISTED_TO_NUMBER)
        from(MESSAGES_SANDBOX_VIBER_SERVICE_ID)
        category(Category.PROMOTION)
        duration(VIDEO_DURATION)
        fileSize(FILE_SIZE)
        thumbUrl(THUMB_URL)
        url(VIDEO_URL)
        caption(VIDEO_CAPTION)
    },
    sandbox = true
))
```

##### Send Viber Text
```kotlin
println(client.messages.send(
    viberText {
        to(MESSAGES_SANDBOX_ALLOW_LISTED_TO_NUMBER)
        from(MESSAGES_SANDBOX_VIBER_SERVICE_ID)
        text("Don't miss out on our latest offers!")
        category(Category.PROMOTION)
    },
    sandbox = true
))
```

#### WhatsApp
##### Send WhatsApp Sticker
```kotlin
println(client.messages.send(
    whatsappSticker {
        to(MESSAGES_SANDBOX_ALLOW_LISTED_TO_NUMBER)
        from(MESSAGES_SANDBOX_WHATSAPP_NUMBER)
        url(STICKER_URL)
    },
    sandbox = true
))
```

##### Send WhatsApp Reaction
```kotlin
println(client.messages.send(
    whatsappReaction {
        to(MESSAGES_SANDBOX_ALLOW_LISTED_TO_NUMBER)
        from(MESSAGES_SANDBOX_WHATSAPP_NUMBER)
        reaction(EMOJI)
    },
    sandbox = true
))
```

##### Send WhatsApp Text
```kotlin
println(client.messages.send(
    whatsappText {
        to(MESSAGES_SANDBOX_ALLOW_LISTED_TO_NUMBER)
        from(MESSAGES_SANDBOX_WHATSAPP_NUMBER)
        text("Hello from $VONAGE_BRAND_NAME!")
    },
    sandbox = true
))
```

##### Send WhatsApp Audio
```kotlin
println(client.messages.send(
    whatsappAudio {
        to(MESSAGES_SANDBOX_ALLOW_LISTED_TO_NUMBER)
        from(MESSAGES_SANDBOX_WHATSAPP_NUMBER)
        url(AUDIO_URL)
    },
    sandbox = true
))
```

##### Send WhatsApp Image
```kotlin
println(client.messages.send(
    whatsappImage {
        to(MESSAGES_SANDBOX_ALLOW_LISTED_TO_NUMBER)
        from(MESSAGES_SANDBOX_WHATSAPP_NUMBER)
        url(IMAGE_URL)
        caption(IMAGE_CAPTION)
    },
    sandbox = true
))
```

##### Send WhatsApp Video
```kotlin
println(client.messages.send(
    whatsappVideo {
        to(MESSAGES_SANDBOX_ALLOW_LISTED_TO_NUMBER)
        from(MESSAGES_SANDBOX_WHATSAPP_NUMBER)
        url(VIDEO_URL)
        caption(VIDEO_CAPTION)
    },
    sandbox = true
))
```

### WhatsApp
#### Send WhatsApp Authentication Template
```kotlin
val messageId = client.messages.send(
    whatsappCustom {
        to(TO_NUMBER)
        from(VONAGE_WHATSAPP_NUMBER)
        custom(mapOf(
            "type" to MessageType.TEMPLATE,
            "template" to mapOf(
                "name" to WHATSAPP_AUTH_TEMPLATE_NAME,
                "language" to mapOf(
                    "policy" to Policy.DETERMINISTIC,
                    "code" to Locale.ENGLISH
                ),
                "components" to listOf(
                    mapOf(
                        "type" to "body",
                        "parameters" to listOf(
                            mapOf(
                                "type" to MessageType.TEXT,
                                "text" to OTP
                            )
                        )
                    ),
                    mapOf(
                        "type" to MessageType.BUTTON,
                        "sub_type" to "url",
                        "index" to 0,
                        "parameters" to listOf(
                            mapOf(
                                "type" to MessageType.TEXT,
                                "text" to OTP
                            )
                        )
                    )
                )
            )
        ))
    }
)
```

#### Send WhatsApp Single Product
```kotlin
val messageId = client.messages.send(
    whatsappSingleProduct {
        to(TO_NUMBER)
        from(VONAGE_WHATSAPP_NUMBER)
        catalogId(CATALOG_ID)
        productRetailerId(PRODUCT_ID)
        bodyText("Check out this cool product")
        footerText("Sale now on!")
    }
)
```

#### Send WhatsApp Location
```kotlin
val messageId = client.messages.send(
    whatsappLocation {
        to(TO_NUMBER)
        from(VONAGE_WHATSAPP_NUMBER)
        longitude(-122.425332)
        latitude(37.758056)
        name("Facebook HQ")
        address("1 Hacker Way, Menlo Park, CA 94025")
    }
)
```

#### Send WhatsApp Template
```kotlin
val messageId = client.messages.send(
    whatsappTemplate {
        to(TO_NUMBER)
        from(VONAGE_WHATSAPP_NUMBER)
        policy(Policy.DETERMINISTIC)
        locale(Locale.ENGLISH_UK)
        name("$WHATSAPP_TEMPLATE_NAMESPACE:$WHATSAPP_TEMPLATE_NAME")
        parameters(listOf(
            "Vonage Verification",
            "64873",
            "10"
        ))
    }
)
```

#### Send WhatsApp Unreaction
```kotlin
val messageId = client.messages.send(
    whatsappReaction {
        to(TO_NUMBER)
        from(VONAGE_WHATSAPP_NUMBER)
        contextMessageId(MESSAGE_UUID)
        unreact()
    }
)
```

#### Send WhatsApp File
```kotlin
val messageId = client.messages.send(
    whatsappFile {
        to(TO_NUMBER)
        from(VONAGE_WHATSAPP_NUMBER)
        url(FILE_URL)
        caption(FILE_CAPTION)
    }
)
```

#### Send WhatsApp Reaction
```kotlin
val messageId = client.messages.send(
    whatsappReaction {
        to(TO_NUMBER)
        from(VONAGE_WHATSAPP_NUMBER)
        contextMessageId(MESSAGE_UUID)
        reaction(EMOJI)
    }
)
```

#### Send WhatsApp Text
```kotlin
val messageId = client.messages.send(
    whatsappText {
        to(TO_NUMBER)
        from(VONAGE_WHATSAPP_NUMBER)
        text("This is a WhatsApp text message sent using the Messages API")
    }
)
```

#### Mark As Read
```kotlin
client.messages.existingMessage(MESSAGE_UUID, ApiRegion.API_US).markAsRead()
```

#### Send WhatsApp Quick Reply Button
```kotlin
val messageId = client.messages.send(
    whatsappCustom {
        to(TO_NUMBER)
        from(VONAGE_WHATSAPP_NUMBER)
        custom(mapOf(
            "type" to MessageType.TEMPLATE,
            "template" to mapOf(
                "namespace" to WHATSAPP_TEMPLATE_NAMESPACE,
                "name" to WHATSAPP_TEMPLATE_NAME,
                "language" to mapOf(
                    "code" to Locale.ENGLISH,
                    "policy" to Policy.DETERMINISTIC
                ),
                "components" to listOf(
                    mapOf(
                        "type" to "header",
                        "parameters" to listOf(
                            mapOf(
                                "type" to MessageType.TEXT,
                                "text" to "12/26"
                            )
                        )
                    ),
                    mapOf(
                        "type" to "body",
                        "parameters" to listOf(
                            mapOf(
                                "type" to MessageType.TEXT,
                                "text" to "*Ski Trip*"
                            ),
                            mapOf(
                                "type" to MessageType.TEXT,
                                "text" to "2019-12-26"
                            ),
                            mapOf(
                                "type" to MessageType.TEXT,
                                "text" to "*Squaw Valley Ski Resort, Tahoe*"
                            )
                        )
                    ),
                    mapOf(
                        "type" to MessageType.BUTTON,
                        "sub_type" to "quick_reply",
                        "index" to 0,
                        "parameters" to listOf(
                            mapOf(
                                "type" to "payload",
                                "payload" to "Yes-Button-Payload"
                            )
                        )
                    ),
                    mapOf(
                        "type" to MessageType.BUTTON,
                        "sub_type" to "quick_reply",
                        "index" to 1,
                        "parameters" to listOf(
                            mapOf(
                                "type" to "payload",
                                "payload" to "No-Button-Payload"
                            )
                        )
                    )
                )
            )
        ))
    }
)
```

#### Send WhatsApp Audio
```kotlin
val messageId = client.messages.send(
    whatsappAudio {
        to(TO_NUMBER)
        from(VONAGE_WHATSAPP_NUMBER)
        url(AUDIO_URL)
    }
)
```

#### Send WhatsApp Image
```kotlin
val messageId = client.messages.send(
    whatsappImage {
        to(TO_NUMBER)
        from(VONAGE_WHATSAPP_NUMBER)
        url(IMAGE_URL)
        caption(IMAGE_CAPTION)
    }
)
```

#### Send WhatsApp Sticker URL
```kotlin
val messageId = client.messages.send(
    whatsappSticker {
        to(TO_NUMBER)
        from(VONAGE_WHATSAPP_NUMBER)
        url(STICKER_URL)
    }
)
```

#### Send WhatsApp Video
```kotlin
val messageId = client.messages.send(
    whatsappVideo {
        to(TO_NUMBER)
        from(VONAGE_WHATSAPP_NUMBER)
        url(VIDEO_URL)
        caption(VIDEO_CAPTION)
    }
)
```

#### Send WhatsApp Link Button
```kotlin
val messageId = client.messages.send(
    whatsappCustom {
        to(TO_NUMBER)
        from(VONAGE_WHATSAPP_NUMBER)
        custom(mapOf(
            "type" to MessageType.TEMPLATE,
            "template" to mapOf(
                "namespace" to WHATSAPP_TEMPLATE_NAMESPACE,
                "name" to WHATSAPP_TEMPLATE_NAME,
                "language" to mapOf(
                    "code" to Locale.ENGLISH,
                    "policy" to Policy.DETERMINISTIC
                ),
                "components" to listOf(
                    mapOf(
                        "type" to "header",
                        "parameters" to listOf(
                            mapOf(
                                "type" to MessageType.IMAGE,
                                "image" to mapOf(
                                    "link" to HEADER_IMAGE_URL
                                )
                            )
                        )
                    ),
                    mapOf(
                        "type" to "body",
                        "parameters" to listOf(
                            mapOf(
                                "type" to MessageType.TEXT,
                                "text" to "Anand"
                            ),
                            mapOf(
                                "type" to MessageType.TEXT,
                                "text" to "Quest"
                            ),
                            mapOf(
                                "type" to MessageType.TEXT,
                                "text" to "113-0921387"
                            ),
                            mapOf(
                                "type" to MessageType.TEXT,
                                "text" to "23rd Nov 2019"
                            )
                        )
                    ),
                    mapOf(
                        "type" to MessageType.BUTTON,
                        "index" to 0,
                        "sub_type" to "url",
                        "parameters" to listOf(
                            mapOf(
                                "type" to MessageType.TEXT,
                                "text" to "1Z999AA10123456784"
                            )
                        )
                    )
                )
            )
        ))
    }
)
```

#### Send WhatsApp Media Template
```kotlin
val messageId = client.messages.send(
    whatsappCustom {
        to(TO_NUMBER)
        from(VONAGE_WHATSAPP_NUMBER)
        custom(mapOf(
            "type" to MessageType.TEMPLATE,
            "template" to mapOf(
                "name" to WHATSAPP_TEMPLATE_NAME,
                "language" to mapOf(
                    "policy" to Policy.DETERMINISTIC,
                    "code" to Locale.ENGLISH
                ),
                "components" to listOf(
                    mapOf(
                        "type" to "header",
                        "parameters" to listOf(
                            mapOf(
                                "type" to MessageType.IMAGE,
                                "image" to mapOf(
                                    "link" to IMAGE_URL
                                )
                            )
                        )
                    ),
                    mapOf(
                        "type" to "body",
                        "parameters" to listOf(
                            mapOf(
                                "type" to MessageType.TEXT,
                                "text" to WHATSAPP_TEMPLATE_REPLACEMENT_TEXT
                            )
                        )
                    )
                )
            )
        ))
    }
)
```

#### Send WhatsApp Contact
```kotlin
val messageId = client.messages.send(
    whatsappCustom {
        to(TO_NUMBER)
        from(VONAGE_WHATSAPP_NUMBER)
        custom(mapOf(
            "type" to "contacts",
            "contacts" to listOf(
                mapOf(
                    "addresses" to listOf(
                        mapOf(
                            "city" to "Menlo Park",
                            "country" to "United States",
                            "state" to "CA",
                            "country_code" to "us",
                            "street" to "1 Hacker Way",
                            "type" to "HOME",
                            "zip" to "94025"
                        ),
                        mapOf(
                            "city" to "Menlo Park",
                            "country" to "United States",
                            "state" to "CA",
                            "country_code" to "us",
                            "street" to "200 Jefferson Dr",
                            "type" to "WORK",
                            "zip" to "94025"
                        )
                    ),
                    "birthday" to "2012-08-18",
                    "emails" to listOf(
                        mapOf(
                            "email" to "test@fb.com",
                            "type" to "WORK"
                        ),
                        mapOf(
                            "email" to "test@whatsapp.com",
                            "type" to "WORK"
                        )
                    ),
                    "name" to mapOf(
                        "first_name" to "Jayden",
                        "last_name" to "Smith",
                        "formatted_name" to "J. Smith"
                    ),
                    "org" to mapOf(
                        "company" to "WhatsApp",
                        "department" to "Design",
                        "title" to "Manager"
                    ),
                    "phones" to listOf(
                        mapOf(
                            "phone" to "+1 (940) 555-1234",
                            "type" to "HOME"
                        ),
                        mapOf(
                            "phone" to "+1 (650) 555-1234",
                            "type" to "WORK",
                            "wa_id" to "16505551234"
                        )
                    ),
                    "urls" to listOf(
                        mapOf(
                            "url" to "https://www.facebook.com",
                            "type" to "WORK"
                        )
                    )
                )
            )
        ))
    }
)
```

#### Send WhatsApp Sticker Id
```kotlin
val messageId = client.messages.send(
    whatsappSticker {
        to(TO_NUMBER)
        from(VONAGE_WHATSAPP_NUMBER)
        id(STICKER_ID)
    }
)
```

#### Send WhatsApp Multi Product
```kotlin
val messageId = client.messages.send(
    whatsappMultiProduct {
        to(TO_NUMBER)
        from(VONAGE_WHATSAPP_NUMBER)
        headerText("Our top products")
        bodyText("Check out these great products")
        footerText("Sale now on!")
        catalogId(CATALOG_ID)
        addProductsSection("Cool products", PRODUCT_ID, PRODUCT_ID)
        addProductsSection("Awesome products", PRODUCT_ID)
    }
)
```

## Numbers
### Buy Number
```kotlin
client.numbers.number(COUNTRY_CODE, VONAGE_NUMBER).buy()
```

### Cancel Number
```kotlin
client.numbers.number(COUNTRY_CODE, VONAGE_NUMBER).cancel()
```

### Search Available Numbers
```kotlin
val numbers = client.numbers.searchAvailable {
    country(COUNTRY_CODE)
    type(VONAGE_NUMBER_TYPE)
    features(*VONAGE_NUMBER_FEATURES)
    pattern(NUMBER_SEARCH_PATTERN, NUMBER_SEARCH_CRITERIA)
}
for (number in numbers) {
    println("""
        Tel: ${number.msisdn}
        Country: ${number.country}
        Type: ${number.type}
        Cost: ${number.cost}
        """.trimIndent()
    )
}
```

### Update Number
```kotlin
client.numbers.number(COUNTRY_CODE, VONAGE_NUMBER).update {
    moHttpUrl(SMS_CALLBACK_URL)
    voiceCallback(VOICE_CALLBACK_TYPE, VOICE_CALLBACK_VALUE)
    voiceStatusCallback(VOICE_STATUS_URL)
}
```

### List Owned Numbers
```kotlin
val numbers = client.numbers.listOwned {
    pattern(NUMBER_SEARCH_PATTERN, NUMBER_SEARCH_CRITERIA)
}
for (number in numbers) {
    println("""
        Tel: ${number.msisdn}
        Country: ${number.country}
        Type: ${number.type}
        """.trimIndent()
    )
}
```

## SMS
### Submit SMS Conversion
```kotlin
client.conversion.convertSms(
    messageId = MESSAGE_UUID,
    delivered = true,
    timestamp = Instant.now()
)
```

### Send Unicode Message
```kotlin
val response = client.sms.sendText(
    from = VONAGE_BRAND_NAME,
    to = TO_NUMBER,
    message = "こんにちは世界",
    unicode = true
)

if (response.wasSuccessfullySent()) {
    println("Message sent successfully.")
}
else {
    println("Message failed with error: ${response[0].errorText}")
}
```

### Receive Message
```kotlin
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
```

### Receive SMS Dlr
```kotlin
embeddedServer(Netty, port = 8000) {
    routing {
        route("/webhooks/delivery-receipt") {
            handle {
                if (call.request.queryParameters.isEmpty()) {
                    val json = call.receive<String>()
                    println(json)
                }
                else {
                    call.request.queryParameters.forEach { key, values ->
                        println("$key: ${values.first()}")
                    }
                }
                call.respond(HttpStatusCode.NoContent)
            }
        }
    }
}.start(wait = true)
```

### Send Signed SMS
```kotlin
val response = client.sms.sendText(
    from = VONAGE_BRAND_NAME,
    to = TO_NUMBER,
    message = "Hello from Vonage SMS API"
)

if (response.wasSuccessfullySent()) {
    println("Message sent successfully.")
}
else {
    println("Message failed with error: ${response[0].errorText}")
}
```

### Send Message
```kotlin
val response = client.sms.sendText(
    from = VONAGE_BRAND_NAME,
    to = TO_NUMBER,
    message = "Hello from Vonage SMS API"
)

println(
    if (response.wasSuccessfullySent())
        "Message sent successfully."
    else
        "Message failed with error: ${response[0].errorText}"
)
```

## Verify
### Send Verification Request Silent Auth
```kotlin
val response = client.verify.sendVerification(BRAND_NAME) {
    silentAuth(TO_NUMBER)
}
println("Verification sent: ${response.requestId}")
```

### Send Verification Request With Fallback
```kotlin
val response = client.verify.sendVerification(BRAND_NAME) {
    silentAuth(TO_NUMBER)
    email(TO_EMAIL)
}
println("Verification sent: ${response.requestId}")
```

### Send Verification Request SMS
```kotlin
val response = client.verify.sendVerification(BRAND_NAME) {
    sms(TO_NUMBER)
}
println("Verification sent: ${response.requestId}")
```

### Legacy
#### Start PSD2 Verification
```kotlin
val response = client.verifyLegacy.psd2Verify(RECIPIENT_NUMBER, AMOUNT, PAYEE_NAME)
if (response.status == VerifyStatus.OK) {
    println("Verification sent. Request ID: ${response.requestId}")
}
else {
    println("Error: ${response.errorText}")
}
```

#### Check Verification
```kotlin
val response = client.verifyLegacy.request(REQUEST_ID).check(CODE)
if (response.status == VerifyStatus.OK) {
    println("Code matched. Verification successful.")
}
else {
    println("Verification failed: ${response.errorText}")
}
```

#### Advance Verification
```kotlin
val response = client.verifyLegacy.request(REQUEST_ID).advance()
println(response.errorText ?: "Verification advanced to next stage!")
```

#### Search Verification
```kotlin
val response = client.verifyLegacy.request(REQUEST_ID).info()
if (response.status == VerifyStatus.OK) {
    response.verificationRequests.forEach {
        println(it)
    }
}
```

#### Start Verification
```kotlin
val response = client.verifyLegacy.verify(RECIPIENT_NUMBER, BRAND_NAME)
if (response.status == VerifyStatus.OK) {
    println("Verification sent. Request ID: ${response.requestId}")
}
else {
    println("Error: ${response.errorText}")
}
```

#### Start Verification With Workflow
```kotlin
val response = client.verifyLegacy.verify(RECIPIENT_NUMBER, BRAND_NAME) {
    workflow(VerifyRequest.Workflow.SMS_TTS)
}
if (response.status == VerifyStatus.OK) {
    println("Verification sent. Request ID: ${response.requestId}")
}
else {
    println("Error: ${response.errorText}")
}
```

#### Cancel Verification
```kotlin
val response = client.verifyLegacy.request(REQUEST_ID).cancel()
println(response.status)
```

#### Start PSD2 Verification With Workflow
```kotlin
val response = client.verifyLegacy.psd2Verify(RECIPIENT_NUMBER, AMOUNT, PAYEE_NAME) {
    workflow(Psd2Request.Workflow.SMS_TTS_TTS)
}
if (response.status == VerifyStatus.OK) {
    println("Verification sent. Request ID: ${response.requestId}")
}
else {
    println("Error: ${response.errorText}")
}
```

### Send Verification Request Email
```kotlin
val response = client.verify.sendVerification(BRAND_NAME) {
    email(TO_EMAIL)
}
println("Verification sent: ${response.requestId}")
```

### Send Verification Request Voice
```kotlin
val response = client.verify.sendVerification(BRAND_NAME) {
    voice(TO_NUMBER)
}
println("Verification sent: ${response.requestId}")
```

### Send Verification Request WhatsApp Interactive
```kotlin
val response = client.verify.sendVerification(BRAND_NAME) {
    whatsappCodeless(TO_NUMBER, WHATSAPP_BUSINESS_NUMBER)
}
println("Verification sent: ${response.requestId}")
```

### Cancel Verification Request
```kotlin
client.verify.request(REQUEST_ID).cancel()
```

### Templates
#### Delete Template
```kotlin
client.verify.template(TEMPLATE_ID).delete()
```

#### Create Template
```kotlin
val template = client.verify.createTemplate("My_template")
println(template.id)
```

#### Update Template
```kotlin
val existingTemplate = client.verify.template(TEMPLATE_ID)
val updatedTemplate = existingTemplate.update(
    name = "My_renamed_template", isDefault = false
)
println(updatedTemplate)
```

#### Fragments
##### Get Template Fragment
```kotlin
val existingTemplate = client.verify.template(TEMPLATE_ID)
val fragment = existingTemplate.fragment(TEMPLATE_FRAGMENT_ID).get()
println(fragment)
```

##### Update Template Fragment
```kotlin
val existingTemplate = client.verify.template(TEMPLATE_ID)
val existingFragment = existingTemplate.fragment(TEMPLATE_FRAGMENT_ID)
val updatedFragment = existingFragment.update(
    "The authentication code for your \${brand} is: \${code}",
)
println(updatedFragment)
```

##### Create Template Fragment
```kotlin
val existingTemplate = client.verify.template(TEMPLATE_ID)
val fragment = existingTemplate.createFragment(
    text = "The authentication code for your \${brand} is: \${code}",
    channel = FragmentChannel.SMS,
    locale = "en-us"
)
println(fragment.fragmentId)
```

##### List Template Fragments
```kotlin
val existingTemplate = client.verify.template(TEMPLATE_ID)
val fragments = existingTemplate.listFragments()
fragments.forEach { println(it.fragmentId) }
```

##### Delete Template Fragment
```kotlin
val existingTemplate = client.verify.template(TEMPLATE_ID)
existingTemplate.fragment(TEMPLATE_FRAGMENT_ID).delete()
```

#### List Templates
```kotlin
val templates = client.verify.listTemplates()
templates.forEach { println(it.id) }
```

#### Get Template
```kotlin
val template = client.verify.template(TEMPLATE_ID).get()
println(template)
```

### Check Verification Code
```kotlin
if (client.verify.request(REQUEST_ID).isValidVerificationCode(CODE)) {
    println("Code matches.")
}
```

### Send Verification Request WhatsApp
```kotlin
val response = client.verify.sendVerification(BRAND_NAME) {
    whatsapp(TO_NUMBER, WHATSAPP_BUSINESS_NUMBER)
}
println("Verification sent: ${response.requestId}")
```

## Redact
### Redact Insight
```kotlin
client.redact.redactInsight(VONAGE_REDACT_ID)
```

### Redact SMS
```kotlin
// Outbound
client.redact.redactSms(VONAGE_REDACT_ID)

// Inbound
client.redact.redactSms(VONAGE_REDACT_ID, RedactRequest.Type.INBOUND)
```

### Redact Verification
```kotlin
client.redact.redactVerification(VONAGE_REDACT_ID)
```

### Redact Call
```kotlin
// Outbound
client.redact.redactCall(VONAGE_REDACT_ID)

// Inbound
client.redact.redactCall(VONAGE_REDACT_ID, RedactRequest.Type.INBOUND)
```

### Redact Message
```kotlin
// Outbound
client.redact.redactMessage(MESSAGE_UUID)

// Inbound
client.redact.redactMessage(MESSAGE_UUID, RedactRequest.Type.INBOUND)
```

## Voice
### Outbound Text To Speech Call
```kotlin
val callEvent = client.voice.createCall {
    toPstn(TO_NUMBER)
    from(VONAGE_NUMBER)
    answerUrl(ANSWER_URL)
}
```

### Earmuff Call
```kotlin
val call = client.voice.call(CALL_UUID)
call.earmuff()
Thread.sleep(3000)
call.unearmuff()
```

### Handle User Input ASR
```kotlin
embeddedServer(Netty, port = 8000) {
    routing {
        get("/webhooks/answer") {
            call.response.header("Content-Type", "application/json")
            call.respond(
                Ncco(
                    talkAction("Please say something."),
                    inputAction {
                        eventUrl(call.request.path().replace("answer", "asr"))
                        speech {
                            language(SpeechSettings.Language.ENGLISH_UNITED_STATES)
                        }
                    }
                ).toJson()
            )
        }
        post("/webhooks/asr") {
            val event = EventWebhook.fromJson(call.receive())
            call.response.header("Content-Type", "application/json")
            call.respond(
                Ncco(
                    talkAction("You said: "+event.speech.results.first().text),
                ).toJson()
            )
        }
    }
}.start(wait = true)
```

### Transfer Call With NCCO
```kotlin
client.voice.call(CALL_UUID).transfer(
    talkAction("This is a transfer action using an inline NCCO.")
)
```

### Retrieve Info For All Calls
```kotlin
val now = Instant.now()
val yesterday = now.minus(Duration.ofDays(1))

val calls = client.voice.listCalls {
    dateStart(yesterday)
    dateEnd(now)
}

calls.embedded.callInfos.forEach {
    println(it.toJson())
}
```

### Mute Call
```kotlin
val call = client.voice.call(CALL_UUID)
call.mute()
Thread.sleep(3000)
call.unmute()
```

### Retrieve Call Info
```kotlin
val callDetails = client.voice.call(CALL_UUID).info()
```

### Download Recording
```kotlin
val destination = Paths.get("/Users/me123/Downloads")
client.voice.downloadRecording(RECORDING_URL, destination)
```

### Outbound Text To Speech Call With NCCO
```kotlin
val callEvent = client.voice.createCall {
    toPstn(TO_NUMBER)
    from(VONAGE_NUMBER)
    ncco(
        talkAction("This is a text to speech call from Vonage")
    )
}
```

### Connect Callers To Conference
```kotlin
embeddedServer(Netty, port = 8000) {
    routing {
        route("/webhooks/answer") {
            handle {
                call.response.header("Content-Type", "application/json")
                call.respond(
                    Ncco(
                        talkAction("Please wait while we connect you to the conference."),
                        conversationAction(CONF_NAME)
                    ).toJson()
                )
            }
        }
    }
}.start(wait = true)
```

### Record Call Split Audio
```kotlin
embeddedServer(Netty, port = 8000) {
    routing {
        get("/webhooks/answer") {
            call.response.header("Content-Type", "application/json")
            call.respond(
                Ncco(
                    recordAction {
                        eventUrl(call.request.path().replace("answer", "recordings"))
                        channels(2)
                        split(SplitRecording.CONVERSATION)
                    },
                    connectToPstn(TO_NUMBER) {
                        from(VONAGE_NUMBER)
                    }
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
```

### Play Text To Speech Into Call
```kotlin
val response = client.voice.call(CALL_UUID).startTalk(TEXT) {
    language(TextToSpeechLanguage.AMERICAN_ENGLISH)
}
```

### Track NCCO Progress
```kotlin
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
```

### Stream Audio Into Call
```kotlin
val call = client.voice.call(CALL_UUID)
var streamInfo = call.streamAudio(AUDIO_URL)
Thread.sleep(5000)
streamInfo = call.stopStream()
```

### Transfer Call
```kotlin
client.voice.call(CALL_UUID).transfer(NCCO_URL)
```

### Record Call
```kotlin
embeddedServer(Netty, port = 8000) {
    routing {
        get("/webhooks/answer") {
            call.response.header("Content-Type", "application/json")
            call.respond(
                Ncco(
                    recordAction {
                        eventUrl(call.request.path().replace("answer", "recordings"))
                    },
                    connectToPstn(TO_NUMBER) {
                        from(VONAGE_NUMBER)
                    }
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
```

### Connect Inbound Call
```kotlin
embeddedServer(Netty, port = 8000) {
    routing {
        route("/webhooks/answer") {
            handle {
                call.response.header("Content-Type", "application/json")
                call.respond(
                    Ncco(
                        connectToPstn(YOUR_SECOND_NUMBER) {
                            from(VONAGE_NUMBER)
                        }
                    ).toJson()
                )
            }
        }
    }
}.start(wait = true)
```

### Receive Inbound Call
```kotlin
embeddedServer(Netty, port = 8000) {
    routing {
        get("/webhooks/answer") {
            val from = call.request.queryParameters["from"]?.replace("", "")
            call.response.header("Content-Type", "application/json")
            call.respond(
                Ncco(
                    talkAction("Thank you for calling from $from")
                ).toJson()
            )
        }
    }
}.start(wait = true)
```

### Record Conversation
```kotlin
embeddedServer(Netty, port = 8000) {
    routing {
        get("/webhooks/answer") {
            call.response.header("Content-Type", "application/json")
            call.respond(
                Ncco(
                    conversationAction(CONF_NAME) {
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
```

### Play DTMF Into Call
```kotlin
val response = client.voice.call(CALL_UUID).sendDtmf(DIGITS)
```

### Record Message
```kotlin
embeddedServer(Netty, port = 8000) {
    routing {
        get("/webhooks/answer") {
            call.response.header("Content-Type", "application/json")
            call.respond(
                Ncco(
                    talkAction("Please leave a message after the tone, then press #."),
                    recordAction {
                        eventUrl(call.request.path().replace("answer", "recordings"))
                        beepStart(true)
                        endOnSilence(3)
                        endOnKey('#')
                    },
                    talkAction("Thank you for your message. Goodbye!")
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
```

### Handle User Input DTMF
```kotlin
embeddedServer(Netty, port = 8000) {
    routing {
        get("/webhooks/answer") {
            call.response.header("Content-Type", "application/json")
            call.respond(
                Ncco(
                    talkAction("Hello. Please press any key to continue."),
                    inputAction {
                        eventUrl(call.request.path().replace("answer", "dtmf"))
                        dtmf {
                            maxDigits(1)
                        }
                    }
                ).toJson()
            )
        }
        post("/webhooks/dtmf") {
            val event = EventWebhook.fromJson(call.receive())
            call.response.header("Content-Type", "application/json")
            call.respond(
                Ncco(
                    talkAction("You pressed ${event.dtmf.digits}, goodbye."),
                ).toJson()
            )
        }
    }
}.start(wait = true)
```

## Users
### Get User
```kotlin
val user = client.users.user(USER_ID).get()
```

### Delete User
```kotlin
client.users.user(USER_ID).delete()
```

### List Users
```kotlin
val users = client.users.list()
```

### Update User
```kotlin
val user = client.users.user(USER_ID).update {
    name(USER_NEW_NAME)
    displayName(USER_NEW_DISPLAY_NAME)
}
```

### Create User
```kotlin
val user = client.users.create {
    name(USER_NAME)
    displayName(USER_DISPLAY_NAME)
    imageUrl(IMAGE_URL)
    channels(
        Pstn(PSTN),
        Sms(TO_NUMBER),
        Viber(TO_NUMBER),
        Whatsapp(TO_NUMBER),
        Viber(TO_NUMBER),
        Messenger(FB_RECIPIENT_ID),
        Vbc(VBC_EXTENSION),
        Sip(SIP_SECURE_URI, SIP_USERNAME, SIP_PASSWORD),
        Websocket(WEBSOCKET_URI)
    )
}
```

## Number Insight
### Basic Insight
```kotlin
val response = client.numberInsight.basic(INSIGHT_NUMBER)
println(response)
```

### Standard Insight
```kotlin
val response = client.numberInsight.standard(INSIGHT_NUMBER)
println(response)
```

### Advanced Insight Webhook
```kotlin
embeddedServer(Netty, port = 8000) {
    routing {
        post ("/webhooks/insight") {
            val insightDetails = AdvancedInsightResponse.fromJson(call.receive())
            println(insightDetails)
            call.respond(204)
        }
    }
}.start(wait = true)
```

### Advanced Insight Sync
```kotlin
val response = client.numberInsight.advanced(INSIGHT_NUMBER, INSIGHT_CALLBACK_URL)
println(response)
```

### Advanced Insight Async
```kotlin
client.numberInsight.advancedAsync(INSIGHT_NUMBER, INSIGHT_CALLBACK_URL)
```

## Application
### Update Application
```kotlin
val application = client.application.application(VONAGE_APPLICATION_ID).update {
    name("New App Name")
    messages {
        inbound {
            address("https://example.com/webhooks/inbound")
            method(HttpMethod.POST)
        }
        status {
            address("https://example.com/webhooks/status")
            method(HttpMethod.POST)
        }
    }
    voice {
        answer {
            address("https://example.com/webhooks/answer")
            method(HttpMethod.GET)
        }
        event {
            address("https://example.com/webhooks/event")
            method(HttpMethod.POST)
        }
        rtc {
            event {
                address("https://example.com/webhooks/event")
                method(HttpMethod.POST)
            }
        }
        vbc()
    }
}
```

### List Applications
```kotlin
val applications = client.application.listAll()
```

### Get Application
```kotlin
client.application.application(VONAGE_APPLICATION_ID).get()
```

### Delete Application
```kotlin
client.application.application(VONAGE_APPLICATION_ID).delete()
```

### Create Application
```kotlin
val application = client.application.create {
    name("Code Snippets V2 Application")
    messages {
        inbound {
            address("https://example.com/webhooks/inbound")
            method(HttpMethod.POST)
        }
        status {
            address("https://example.com/webhooks/status")
            method(HttpMethod.POST)
        }
    }
    voice {
        answer {
            address("https://example.com/webhooks/answer")
            method(HttpMethod.GET)
        }
        fallbackAnswer {
            address("https://fallback.example.com/webhooks/answer")
            method(HttpMethod.GET)
        }
        event {
            address("https://example.com/webhooks/event")
            method(HttpMethod.POST)
        }
    }
    rtc {
        event {
            address("https://example.com/webhooks/event")
            method(HttpMethod.POST)
        }
    }
    verify {
        status {
            address("https://example.com/webhooks/verify")
            method(HttpMethod.POST)
        }
    }
    vbc()
}
```

## Account
### Revoke Secret
```kotlin
client.account.secrets().delete(SECRET_ID)
```

### Create Secret
```kotlin
val secret = client.account.secrets().create(NEW_SECRET)
println("ID: ${secret.id} created on: ${secret.created}")
```

### Get Balance
```kotlin
val balance = client.account.getBalance()
println("Balance: €${balance.value}")
```

### Get Secret
```kotlin
val secret = client.account.secrets().get(SECRET_ID)
println("ID: ${secret.id} created on: ${secret.created}")
```

### List Secrets
```kotlin
val secrets = client.account.secrets().list()
for (secret in secrets) {
    println("ID: ${secret.id} created on: ${secret.created}")
}
```

### Configure Account
```kotlin
val settings = client.account.updateSettings(incomingSmsUrl = SMS_CALLBACK_URL)
println("moCallBackUrl is now ${settings.incomingSmsUrl}")
```

## Pricing
### Get Outbound SMS Prefix Price
```kotlin
val response = client.pricing.getOutboundSmsPriceForPrefix(DIAL_PREFIX)
response.forEach { println(it.toJson()) }
```

### List Outbound SMS Prices
```kotlin
val prices = client.pricing.listOutboundSmsPrices()

prices.forEach { println(
    "It will cost ${it.defaultPrice} ${it.currency} to send an SMS to ${it.country.name}."
)}
```

### List Outbound Voice Prices
```kotlin
val prices = client.pricing.listOutboundVoicePrices()

prices.forEach { println(
    "It will cost ${it.defaultPrice} ${it.currency} per minute to call ${it.country.name}."
)}
```

### Get Outbound Voice Price
```kotlin
val response = client.pricing.getOutboundVoicePriceForCountry(COUNTRY_CODE)
println("${response.defaultPrice} ${response.currency} per minute.")
```

### Get Outbound SMS Price
```kotlin
val response = client.pricing.getOutboundSmsPrice(COUNTRY_CODE)
println("${response.defaultPrice} ${response.currency} per message.")
```

### Get Outbound Voice Prefix Price
```kotlin
val response = client.pricing.getOutboundVoicePriceForPrefix(DIAL_PREFIX)
response.forEach { println(it.toJson()) }
```

