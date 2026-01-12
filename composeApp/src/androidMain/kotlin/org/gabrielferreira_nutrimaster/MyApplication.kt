package org.gabrielferreira_nutrimaster

import android.app.Application
import com.gabrielferreira_dev.nutrimaster.di.initializeKoin
import com.google.firebase.Firebase
import com.google.firebase.initialize
import org.koin.android.ext.koin.androidContext

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initializeKoin {
            androidContext(this@MyApplication)
        }
        Firebase.initialize(context = this)
    }
}