package com.andronerds.quotes.utils

import java.awt.Desktop
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.net.URLEncoder

actual fun copyToClipboard(text: String) {
    val stringSelection = StringSelection(text)
    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
    clipboard.setContents(stringSelection, null)
}

actual fun shareText(text: String, subject: String) {
    try {
        val encodedSubject = URLEncoder.encode(subject, "UTF-8")
        val encodedBody = URLEncoder.encode(text, "UTF-8")
        val mailtoUrl = "mailto:?subject=$encodedSubject&body=$encodedBody"

        Desktop.getDesktop().browse(java.net.URI(mailtoUrl))
    } catch (e: Exception) {
        // Fallback to copying to clipboard if opening mail client fails or is not supported
        System.err.println("Failed to open email client for sharing: ${e.message}. Copying to clipboard instead.")
        copyToClipboard(text)
    }
}
