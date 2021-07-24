package com.majesticreader.presentation.util

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Typeface
import android.text.InputFilter
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide

fun Activity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun View.visibleExt() {
    visibility = View.VISIBLE
}

fun View.invisibleExt() {
    visibility = View.INVISIBLE
}

fun View.goneExt() {
    visibility = View.GONE
}

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

fun ViewGroup.inflateExt(@LayoutRes layoutId: Int): View =
    (LayoutInflater.from(context).inflate(layoutId, this, false))

fun Context.getColorExt(@ColorRes id: Int) = ContextCompat.getColor(this, id)

fun Bitmap.rotate(degrees: Float): Bitmap {
    val matrix = Matrix().also {
        it.setRotate(degrees, this.width.toFloat(), this.height.toFloat())
    }
    return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
}

fun TextView.setMaxLength(max: Int) {
    val filterArray = arrayOfNulls<InputFilter>(1)
    filterArray[0] = InputFilter.LengthFilter(max)
    this.filters = filterArray
}

fun TextView.setFontSize(fontSize: Int) {
    setTextSize(
        TypedValue.COMPLEX_UNIT_PX,
        resources.getDimension(fontSize)
    )
}

fun Activity.getRootView(): View {
    return findViewById(android.R.id.content)
}

fun Context.convertDpToPx(dp: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        this.resources.displayMetrics
    )
}

fun EditText.enableEditText(isEnable: Boolean) {
    isFocusable = isEnable
    isEnabled = isEnable
    isCursorVisible = isEnable
    isFocusableInTouchMode = isEnable
    isClickable = isEnable

    if (!isEnable) {
        keyListener = null
        setTextColor(Color.GRAY)
        setBackgroundColor(Color.TRANSPARENT)
    }
}

fun ImageView.setCircleImageBitmap(bitmap: Bitmap) {
    Glide.with(context).load(bitmap).circleCrop().into(this)
}

fun SpannableStringBuilder.setTextBold(textParent: String, textNeedChange: String) {
    setSpan(StyleSpan(Typeface.BOLD), textParent.indexOf(textNeedChange), textParent.length, 0)
}

fun SpannableStringBuilder.setTextColor(
    context: Context,
    resColor: Int,
    textParent: String,
    textNeedChange: String
) {
    setSpan(
        ForegroundColorSpan(ContextCompat.getColor(context, resColor)),
        textParent.indexOf(textNeedChange),
        textParent.indexOf(textNeedChange) + textNeedChange.length,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
}

fun SpannableStringBuilder.setClickSpan(
    textParent: String,
    textNeedChange: String,
    click: () -> Unit
) {
    val clickableSpan: ClickableSpan = object : ClickableSpan() {
        override fun onClick(view: View) {
            click.invoke()
        }

        override fun updateDrawState(ds: TextPaint) = Unit
    }

    setSpan(
        clickableSpan,
        textParent.indexOf(textNeedChange),
        textParent.indexOf(textNeedChange) + textNeedChange.length,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
}
