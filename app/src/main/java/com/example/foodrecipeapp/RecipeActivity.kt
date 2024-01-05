package com.example.foodrecipeapp

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.foodrecipeapp.databinding.ActivityRecipeBinding

class RecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeBinding
    private var imageCrop=true
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemImage)
        binding.titleN .text=intent.getStringExtra("tittle")
        //binding.ingData.text=intent.getStringExtra("ing")
        binding.stepsData.text=intent.getStringExtra("des")
        val ing =
            intent.getStringExtra("ing")?.split("\n".toRegex())?.dropLastWhile { it.isEmpty()}?.toTypedArray()
        binding.timeN.tooltipText=ing?.get(0)
        for(i in 1 until ing!!.size)
        {
            binding.ingData.text=
                """${binding.ingData.text} 
🟩${ing[i]}
                """.trimIndent()
        }

        binding.upDownButton.setOnClickListener {

        }

        binding.stepsButton.setOnClickListener {
            binding.ingScroll.visibility=View.GONE
            binding.stepScroll.visibility=View.VISIBLE

        }
        binding.ingButton.setOnClickListener {
            binding.stepScroll.visibility=View.GONE
            binding.ingScroll.visibility=View.VISIBLE

        }

        binding.fullScreenButton.setOnClickListener {

            if(imageCrop){
                binding.itemImage.scaleType=ImageView.ScaleType.FIT_CENTER
                Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemImage)
                binding.fullScreenButton.setColorFilter(Color.BLACK,PorterDuff.Mode.SRC_ATOP)
                binding.shade.visibility= View.GONE
                imageCrop=!imageCrop
            }
            else{
                binding.itemImage.scaleType=ImageView.ScaleType.CENTER_CROP
                Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemImage)
                binding.fullScreenButton.colorFilter = null
                imageCrop=!imageCrop
            }
        }
        binding.backButton.setOnClickListener {
            finish()
        }

    }

}

