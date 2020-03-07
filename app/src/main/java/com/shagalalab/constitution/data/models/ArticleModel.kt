package com.shagalalab.constitution.data.models

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleModel(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "number")
    var number: Int = 0,

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "description")
    var description: String = "",

    @ColumnInfo(name = "part_id")
    var partId: Int = 0,

    @ColumnInfo(name = "chapter_id")
    var chapterId: Int = 0,

    @ColumnInfo(name = "lang_id")
    var langId: Int = 0

) {
    fun normalizedDescription(): SpannableStringBuilder {
        val spannedString = SpannableStringBuilder()
        if (title != "") {
            spannedString.append(title)
            spannedString.setSpan(
                StyleSpan(Typeface.BOLD),
                spannedString.length - title.length,
                spannedString.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannedString.append(". ")
        }
        spannedString.append(description.replace("\\n", "\n"))
        spannedString.append("\n")
        return spannedString
    }
}
