package com.example.ios_registr

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.ios_registr.model.test


class Test : AppCompatActivity() {

    lateinit var question: TextView
    lateinit var group: RadioGroup
    lateinit var answer1: RadioButton
    lateinit var answer2: RadioButton
    lateinit var answer3: RadioButton
    lateinit var answer4: RadioButton
    lateinit var finish: Button
    lateinit var next: Button
    lateinit var prev: Button
    lateinit var card: CardView
    lateinit var again: ImageButton
    lateinit var correcans: TextView
    lateinit var linear:View
    var status = true
    var list = mutableListOf<test>()

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        question = findViewById(R.id.question)
        answer1 = findViewById(R.id.answer1)
        answer2 = findViewById(R.id.answer2)
        answer3 = findViewById(R.id.answer3)
        answer4 = findViewById(R.id.answer4)
        next = findViewById(R.id.next)
        prev = findViewById(R.id.previus)
        group = findViewById(R.id.radioGroup)
        finish = findViewById(R.id.finish)
        card = findViewById(R.id.card2)
        again = findViewById(R.id.restart)
        correcans = findViewById(R.id.correctans)
        linear = findViewById(R.id.liner)
        var index = 0
        list.add(test("1∙1", "1", "2", "3", "4", "", "1"))
        list.add(test("2∙2", "12", "4", "9", "13", "", "4"))
        list.add(test("3∙3", "5", "6", "17", "9", "", "9"))
        list.add(test("4∙5", "10", "20", "30", "40", "", "20"))

        createTest(index)

        finish.setOnClickListener {
            var a = AnimationUtils.loadAnimation(this, R.anim.anim)
            card.startAnimation(a)
            card.visibility = View.VISIBLE
            gameover()
            correcans.text = correcans.text.toString() + finishing().toString()
        }
        again.setOnClickListener {
            gameover()
            for (i in list) {
                i.choosen = ""
            }
            var a = AnimationUtils.loadAnimation(this, R.anim.anim2)
            card.startAnimation(a)
            card.visibility = View.INVISIBLE
            finish.visibility = View.INVISIBLE
            next.visibility = View.VISIBLE
            prev.visibility = View.INVISIBLE
            correcans.text = correcans.text.dropLast(1)
            group.clearCheck()
            index = 0
            createTest(index)
            card.isEnabled = true
        }
        answer1.setOnClickListener {
            var a = findViewById<RadioButton>(group.checkedRadioButtonId)
            onclick(a, index)
            Log.d("TAG", "onCreate: a1")
        }
        answer2.setOnClickListener {
            var a = findViewById<RadioButton>(group.checkedRadioButtonId)
            onclick(a, index)
            Log.d("TAG", "onCreate: a2")
        }
        answer3.setOnClickListener {
            var a = findViewById<RadioButton>(group.checkedRadioButtonId)
            onclick(a, index)
            Log.d("TAG", "onCreate: a3")
        }
        answer4.setOnClickListener {
            var a = findViewById<RadioButton>(group.checkedRadioButtonId)
            onclick(a, index)
            Log.d("TAG", "onCreate: a4")
        }
        next.setOnClickListener {
            group.clearCheck()
            if (index < list.size - 1) {
                index++
                createTest(index)
                if (index == list.size - 1) {
                    finish.visibility = View.VISIBLE
                    next.visibility = View.INVISIBLE
                }
            }
            prev.visibility = View.VISIBLE
            check(index)


        }
        prev.setOnClickListener {
            finish.visibility = View.INVISIBLE
            next.visibility = View.VISIBLE
            group.clearCheck()
            if (index < list.size && index != 0) {
                index--
                createTest(index)
            }
            check(index)
            if (index == 0) {
                prev.visibility = View.INVISIBLE
            }

        }
    }


    fun createTest(i: Int) {
        question.text = list[i].qustion
        answer1.text = list[i].answer1
        answer2.text = list[i].answer2
        answer3.text = list[i].answer3
        answer4.text = list[i].answer4
    }

    fun check(i: Int) {
        if (!list[i].choosen.isEmpty()) {
            if (answer1.text == list[i].choosen) {
                answer1.isChecked = true
            } else if (answer2.text == list[i].choosen) {
                answer2.isChecked = true
            } else if (answer3.text == list[i].choosen) {
                answer3.isChecked = true
            } else {
                answer4.isChecked = true
            }
            Log.d("TAG", "check: not empyt" + " ${list[i].choosen}")
        } else Log.d("TAG", "check: empyt")
    }

    fun onclick(view: View?, i: Int) {
        var view = findViewById<RadioButton>(view!!.id)
        list[i].choosen = view.text.toString()
        Log.d("TAG", "TARGET : ${list[i].choosen}")
    }

    fun finishing(): Int {
        var correctans = 0
        for (i in list) {
            if (i.choosen == i.correct) {
                correctans++
            }
        }
        return correctans
    }
    fun gameover(){
        if (status) {
            finish.isEnabled = false
            prev.isEnabled = false
            group.isEnabled = false
            status = false
        }else{
            finish.isEnabled = true
            prev.isEnabled = true
            group.isEnabled = true
            status = true
        }
    }
    fun createButton(view: View?){
        for (i in list.indices-1){
            var btn:Button = findViewById(view!!.id)
            btn.text = (i+1).toString()


        }

    }
}