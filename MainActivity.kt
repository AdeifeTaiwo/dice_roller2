package com.example.diceroller

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Build
import android.os.Bundle
import android.view.ViewAnimationUtils
import android.view.animation.Animation
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var diceImages : ImageView
    lateinit var shakeAnimation : Animation
    lateinit var quizLinearLayout : ConstraintLayout


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.roll_button)
        quizLinearLayout = findViewById(R.id.quizLinearLayout);


        var animator: Animator
        val centerX: Int = (quizLinearLayout.getLeft() + quizLinearLayout.getRight()) / 2
        val centerY: Int = (quizLinearLayout.getTop() + quizLinearLayout.getBottom()) / 2


        // calculate animation radius
        val radius =
            Math.max(quizLinearLayout.width, quizLinearLayout.height)
        diceImages = findViewById(R.id.imageView)

        rollButton.setOnClickListener {


            animator = ViewAnimationUtils.createCircularReveal(quizLinearLayout, centerX, centerY,
                radius.toFloat(), 0f)

            animator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    rollDice()


                }
            })
            animator.duration = 500
            animator.start()

            Toast.makeText(this, "RollButton Clicked", Toast.LENGTH_LONG);
        }


    }

    private fun rollDice(){

        val randomInt = Random().nextInt(6) + 1


        val drawableResource = when (randomInt){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6



        }

        //diceImages.startAnimation(shakeAnimation);
        diceImages.setImageResource(drawableResource);



    }
}