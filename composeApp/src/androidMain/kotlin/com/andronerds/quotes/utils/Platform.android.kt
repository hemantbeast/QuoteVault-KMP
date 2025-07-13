package com.andronerds.quotes.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import org.koin.java.KoinJavaComponent.inject

actual fun copyToClipboard(text: String) {
    val context: Context by inject(Context::class.java)
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    val clip = ClipData.newPlainText("quote", text)
    clipboard.setPrimaryClip(clip)
}

actual fun shareText(text: String, subject: String) {
    val context: Context by inject(Context::class.java)
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text)
        putExtra(Intent.EXTRA_SUBJECT, subject)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)
    shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) // Required if called from non-Activity context
    context.startActivity(shareIntent)
}
