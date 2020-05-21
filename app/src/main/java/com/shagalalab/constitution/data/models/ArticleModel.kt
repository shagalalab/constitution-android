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
        if (title.isNotEmpty()) {
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
        spannedString.append("\n\n")
        return spannedString
    }

    companion object {
        const val maxWords = 5
    }

    fun foundArticle(word: String): SpannableStringBuilder {
        val spannedString = SpannableStringBuilder()
        description = description.replace("\\n", "\n")
        val index = description.indexOf(word, 0, true)
        var count = 0
        var startIndex = description.lastIndexOf(" ", index)
        var endIndex = description.indexOf(" ", index)
        while (count != maxWords && startIndex != -1) {
            count += 1
            startIndex = description.lastIndexOf(" ", startIndex - 1)
        }
        count = 0
        while (count != maxWords && endIndex != -1) {
            count += 1
            endIndex = description.indexOf(" ", endIndex + 1)
        }
        if (endIndex == -1) endIndex = description.length
        if (startIndex == -1) startIndex = 0
        spannedString.append("...${description.substring(startIndex, endIndex)}...")
        return spannedString
    }
}

