package com.example.rxjava_task6.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.rxjava_task6.R
import com.example.rxjava_task6.databinding.ActivityMainBinding
import com.example.rxjava_task6.ui.fragment.InputFragment
import com.example.rxjava_task6.ui.fragment.OutputFragment
import com.example.rxjava_task6.util.Communicator
import com.example.rxjava_task6.util.Constant

class MainActivity : AppCompatActivity(), Communicator {
    lateinit var binding: ActivityMainBinding
    private val fragmentInput = InputFragment()
    private val LOG_TAG = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun transferData(text: String) {
        val outputFragment = OutputFragment()

        val bundle = Bundle()
        bundle.putString(Constant.KEY,text)
        outputFragment.arguments= bundle
        sendData(outputFragment)
    }

    //send the data to the output fragment
    private fun sendData(fragment: Fragment) {


        val transaction = this.supportFragmentManager.beginTransaction()
            .remove(fragment)
            .add(R.id.fragment_output, fragment)
            .commit()
        // Log.v(LOG_TAG, "~~~~~~~~~~~~~~~~~~\n FRAGMENT IS CHANGED\n~~~~~~~~~~~~~~~~~~")


    }
}
