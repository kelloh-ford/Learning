package com.example.nestedvschainedswitchmap

import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import io.reactivex.Observable


val EditText.editTextObservable: Observable<CharSequence>
    get() = Observable.create<CharSequence> { emitter ->

        if(text.isNotBlank())
            emitter.onNext(text)
        doOnTextChanged { text, _, _, _ ->

            text?.let {
                emitter.onNext(it)
            }
        }
    }

val Button.buttonObservable:Observable<Unit>
    get() = Observable.create<Unit> { emitter ->
        setOnClickListener { view ->
            emitter.onNext(Unit)
        }
        emitter.setCancellable { setOnClickListener(null) }

    }
