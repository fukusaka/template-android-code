package com.example.android.codesampleapp.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.example.android.codesampleapp.R

class FixedAspectImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : AppCompatImageView(context, attrs, defStyle) {

    private var aspectRatio = 0f

    init {
        val a = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.FixedAspectImageView,
            0, 0
        )

        try {
            aspectRatio = a.getFloat(R.styleable.FixedAspectImageView_aspectRatio, 0.0f)
            require(aspectRatio >= 0.0f) { "aspectRatio must be bigger than 0." }
        } finally {
            a.recycle()
        }
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        // val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val heightSize = (widthSize * aspectRatio).toInt()

        setMeasuredDimension(widthSize, heightSize)

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        val newWidthMeasureSpec = MeasureSpec.makeMeasureSpec(widthSize, widthMode)
        val newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize, heightMode)

        super.onMeasure(newWidthMeasureSpec, newHeightMeasureSpec)
    }
}
