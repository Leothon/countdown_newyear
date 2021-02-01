package com.leon.springfestivalcountdown

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.CountDownTimer
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.View
import android.webkit.MimeTypeMap
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.bumptech.glide.signature.MediaStoreSignature
import com.leon.springfestivalcountdown.databinding.ActivityMainBinding
import jp.wasabeef.blurry.Blurry
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private var mTimer: CountDownTimer? = null

    companion object{
        val BEAUTY_URL = "http://api.nmb.show/xiaojiejie1.php"
        val HOLIDAT_TIME = 1612778400000L
        val HOME_TIME = 1612927140000L
        val SPRING_TIME = 1613059200000L
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        window.statusBarColor = Color.TRANSPARENT
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        changeBackground()
        numberChange()
        start()
        mBinding.backImg.setOnClickListener{
            changeBackground()
        }
    }

    private fun changeBackground() {
        val tail: String = BEAUTY_URL.substring(BEAUTY_URL.lastIndexOf(".") + 1).toLowerCase()
        val type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(tail)
        Glide.with(this)
                .asBitmap()
                .load(BEAUTY_URL)
                .signature(MediaStoreSignature(type, System.currentTimeMillis(), 0))
                .into(object : SimpleTarget<Bitmap?>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap?>?
                    ) {
                        //mBinding.backImg.setImageBitmap(resource)
                        Blurry.with(this@MainActivity).radius(30).from(resource)
                            .into(mBinding.backImg)
                    }
                })
    }

    private fun numberChange() {
        with(mBinding) {
            nowTime.typeface = getNumberTypeFace()
            setNowTime()
            holidayTime.typeface = getNumberTypeFace()
            setHolidayTime()
            goHomeTime.typeface = getNumberTypeFace()
            setHomeTime()
            springTime.typeface = getNumberTypeFace()
            setSpringTime()
        }

    }

    private fun setNowTime() {
        val spannableString = SpannableStringBuilder(String.format(
            getString(R.string.now_time_tips), formatTime(
                System.currentTimeMillis(),
                true
            )
        ))
        spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.purple_500)), 5, 9, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.purple_500)), 10, 12, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.purple_500)), 13, 15, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(RelativeSizeSpan(2f), 5, 9, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(RelativeSizeSpan(2f), 10, 12, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(RelativeSizeSpan(2f), 13, 15, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)

        mBinding.nowTime.text = spannableString
    }

    private fun setHolidayTime() {
        val spannableString = SpannableStringBuilder(String.format(
            getString(R.string.holi_time_tips), getCountDown(
                HOLIDAT_TIME
            )
        ))
        spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.purple_500)), 7, 9, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.purple_500)), 10, 12, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.purple_500)), 14, 16, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.purple_500)), 17, 19, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)

        spannableString.setSpan(RelativeSizeSpan(1.8f), 7, 9, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(RelativeSizeSpan(1.8f), 10, 12, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(RelativeSizeSpan(1.8f), 14, 16, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(RelativeSizeSpan(1.8f), 17, 19, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)

        mBinding.holidayTime.text = spannableString
    }

    private fun setHomeTime() {
        val spannableString = SpannableStringBuilder(String.format(
            getString(R.string.go_time_tips), getCountDown(
                HOME_TIME
            )
        ))
        spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.purple_500)), 7, 9, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.purple_500)), 10, 12, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.purple_500)), 14, 16, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.purple_500)), 17, 19, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)

        spannableString.setSpan(RelativeSizeSpan(1.8f), 7, 9, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(RelativeSizeSpan(1.8f), 10, 12, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(RelativeSizeSpan(1.8f), 14, 16, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(RelativeSizeSpan(1.8f), 17, 19, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        mBinding.goHomeTime.text = spannableString
    }

    private fun setSpringTime() {
        val spannableString = SpannableStringBuilder(String.format(
            getString(R.string.spr_time_tips), getCountDown(
                SPRING_TIME
            )
        ))
        spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.purple_500)), 7, 9, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.purple_500)), 10, 12, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.purple_500)), 14, 16, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.purple_500)), 17, 19, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)

        spannableString.setSpan(RelativeSizeSpan(1.8f), 7, 9, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(RelativeSizeSpan(1.8f), 10, 12, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(RelativeSizeSpan(1.8f), 14, 16, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(RelativeSizeSpan(1.8f), 17, 19, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        mBinding.springTime.text = spannableString
    }


    private fun getNumberTypeFace(): Typeface? {
        var typeface = Typeface.DEFAULT
        try {
            typeface =
                Typeface.createFromAsset(this.assets, "fonts/medalFonts.ttf")
        } catch (e: Exception) {
            //
        }
        return typeface
    }

    private fun getCountDown(countTime: Long): String {
        val currentTime = System.currentTimeMillis()
        var interval = 0L
        if (countTime < currentTime) {
            return "已结束"
        } else {
            interval = countTime - currentTime
        }
        return formatTime(interval, false)
    }

    private fun formatTime(time: Long, now: Boolean): String{
        val date = Date(time)
        val gc = GregorianCalendar()
        gc.time = date
        val format = if (now) SimpleDateFormat("yyyy年MM月dd日") else SimpleDateFormat("dd天hh小时mm分ss秒")
        return format.format(gc.time)
    }

    var circle = 0
    fun start() {
        if (mTimer == null) {
            mTimer = object : CountDownTimer(System.currentTimeMillis(), 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    // 更新页面
                    circle ++
                    if (circle > 10) {
                        changeBackground()
                        circle = 0
                    }
                    numberChange()
                }

                override fun onFinish() {

                }
            }.start()
        }
    }

    fun release() {
        if (mTimer != null) {
            mTimer!!.cancel()
            mTimer = null
        }
    }

    override fun onDestroy() {
        release()
        super.onDestroy()
    }

}