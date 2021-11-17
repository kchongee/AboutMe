package com.example.aboutme

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener{

    private lateinit var binding : ActivityMainBinding
    private val myName : MyName = MyName("Chong Ee","choi")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.myName = myName
        Log.d("myName class: ","Name:${myName?.name} & Nickname:${myName?.nickname}")

        binding.doneButton.setOnClickListener{
            addNickName(it)
        }

        binding.nicknameText.setOnClickListener(View.OnClickListener {
            updateNickname(it)
        })

        ArrayAdapter.createFromResource(this,R.array.programming_languages,android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.programmingLangSpinner.adapter = adapter
            }
        binding.programmingLangSpinner.onItemSelectedListener = this
    }

    private fun addNickName(view:View){
        binding.apply{
            myName?.nickname = nicknameEdit.text.toString()
            invalidateAll()
            nicknameEdit.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
        }
        view.visibility = View.GONE

        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken,0)
    }

    private fun updateNickname(view:View){
        binding.apply{
            nicknameEdit.visibility = View.VISIBLE
            nicknameEdit.requestFocus()
            doneButton.visibility = View.VISIBLE
        }
        view.visibility = View.GONE

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.nicknameEdit, 0)
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//        TODO("Not yet implemented")
        Toast.makeText(this,"Selected option id:${p3}\n" +
                "Selection option: ${p0?.getItemAtPosition(p2)}",Toast.LENGTH_LONG).show()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
//        TODO("Not yet implemented")

    }
}