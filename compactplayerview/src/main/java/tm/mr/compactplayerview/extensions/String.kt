package tm.mr.compactplayerview.extensions

import android.content.Context
import java.lang.Exception

fun String.getRawIdOrNull(context: Context): Int? {
    return try {
        if (this.contains("R.raw.")) {
            val key = this.replace("R.raw.", "").trim()
            val resId = context.resources.getIdentifier(key, "raw", context.packageName)
            resId
        } else
            null
    } catch (e: Exception) {
        null
    }
}