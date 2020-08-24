package com.shagalalab.constitution.data.models

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
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
        const val MAX_WORDS = 5
    }

    fun getShortenedDescription(keyword: String): SpannableStringBuilder {
        val shortenedDescription = cutDescription(description, keyword)
        return formatDescription(shortenedDescription, keyword)
    }

    fun highlightKeyword(word: String): SpannableStringBuilder {
        val spannedString = SpannableStringBuilder()
        description = description.replace("\\n", "\n")
        spannedString.append(description)
        val index = spannedString.indexOf(word, 0, true)
        spannedString.setSpan(
            BackgroundColorSpan(Color.parseColor("#ffd600")),
            index,
            index + word.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannedString
    }

    private fun cutDescription(originalDescription: String, keyword: String): String {
        var needsStartEllipsize = false
        var needsEndEllipsize = false
        var modifiedDescription = originalDescription.replace("\\n", " ").trim()
        val index = modifiedDescription.indexOf(keyword, 0, true)

        var startCount = 0
        var startIndex = modifiedDescription.lastIndexOf(" ", index)
        while (startCount < MAX_WORDS && startIndex > -1) {
            startCount++
            startIndex = modifiedDescription.lastIndexOf(" ", startIndex - 1)
        }

        var endCount = 0
        var endIndex = modifiedDescription.indexOf(" ", index)
        while (endCount < MAX_WORDS && endIndex > -1) {
            endCount++
            endIndex = modifiedDescription.indexOf(" ", endIndex + 1)
        }

        if (startIndex > -1) {
            needsStartEllipsize = true
        } else {
            startIndex = 0
        }
        if (endIndex == -1) {
            endIndex = modifiedDescription.length
        } else {
            needsEndEllipsize = true
        }
        modifiedDescription = modifiedDescription.substring(startIndex, endIndex).trim()

        if (needsStartEllipsize) {
            modifiedDescription = "... $modifiedDescription"
        }
        if (needsEndEllipsize) {
            modifiedDescription = "$modifiedDescription ..."
        }

        return modifiedDescription
    }

    private fun formatDescription(
        shortenedDescription: String,
        keyword: String
    ): SpannableStringBuilder {
        val spannableStringBuilder = SpannableStringBuilder()
        spannableStringBuilder.append(shortenedDescription)
        val index = shortenedDescription.indexOf(keyword, 0, true)

        spannableStringBuilder.setSpan(
            StyleSpan(Typeface.BOLD),
            index,
            index + keyword.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannableStringBuilder.setSpan(
            ForegroundColorSpan(Color.BLACK),
            index,
            index + keyword.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannableStringBuilder
    }
}
