package com.andronerds.quotes.utils

import platform.UIKit.UIActivityViewController
import platform.UIKit.UIApplication
import platform.UIKit.UIPasteboard

actual fun copyToClipboard(text: String) {
    UIPasteboard.generalPasteboard.string = text
}

actual fun shareText(text: String, subject: String) {
    val activityViewController = UIActivityViewController(
        activityItems = listOf(text),
        applicationActivities = null
    )

    // Get the top-most view controller to present from
    val currentViewController = UIApplication.sharedApplication.keyWindow?.rootViewController
    currentViewController?.presentViewController(activityViewController, animated = true, completion = null)
}
