package dev.pegasus.blurview

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.renderscript.Toolkit
import dev.pegasus.blurview.databinding.ActivityMainBinding
import dev.pegasus.blurview.utils.GeneralUtils
import dev.pegasus.blurview.utils.GeneralUtils.withDelay

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var bitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.mcvContainer.background = ContextCompat.getDrawable(this, R.drawable.dummy)
        withDelay(1000) {
            bitmap = GeneralUtils.createBitmapFromView(binding.mcvContainer)
            updateRadius()
        }

        binding.sbRadiusMain.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if (p1 > 0)
                    updateRadius(p1)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })
    }

    private fun updateRadius(radius: Int = 1) {
        val blurBitmap = Toolkit.blur(bitmap, radius)
        val drawable = GeneralUtils.createDrawableFromBitmap(this, blurBitmap)
        binding.mcvContainer.background = drawable
    }
}