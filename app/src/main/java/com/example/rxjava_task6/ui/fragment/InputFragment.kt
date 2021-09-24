package com.example.rxjava_task6.ui.fragment

import android.view.LayoutInflater
import androidx.core.widget.doOnTextChanged
import com.example.rxjava_task6.databinding.FragmentInputBinding
import com.example.rxjava_task6.util.Communicator
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.observables.ConnectableObservable
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class InputFragment : BaseFragment<FragmentInputBinding>() {
    lateinit var communicator: Communicator
    private val outputFragment= OutputFragment()
    override val LOG_TAG: String
        get() = javaClass.simpleName
    override val bindingInflater: (LayoutInflater) -> FragmentInputBinding = FragmentInputBinding::inflate

    override fun setup() {
        communicator = activity as Communicator
        getData()

    }

    override fun addCallBack() {

    }

    private fun getData() {
        val observable = PublishSubject.create<String> { emmiter ->
            binding?.inputText?.doOnTextChanged { text, start, before, count ->
                emmiter.onNext(text.toString())

            }.toString()
        }.debounce(1500, TimeUnit.MILLISECONDS).publish()
        connectObservable(observable)

    }
    private fun connectObservable(observable: @NonNull ConnectableObservable<String>){
        observable.connect()
        observable.subscribe(::nextValue, ::onError)
    }
    private fun nextValue(next: String) {
        communicator.transferData(next)

        //Log.v(LOG_TAG, "~~~~~~~~~~~~~~~~~~\nDATA PASSED TO FRAGMENT :$next\n~~~~~~~~~~~~~~~~~~")

    }


    private fun onError(e:Throwable){
        println("ERROR")
    }
}