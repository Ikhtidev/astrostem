package uz.sayfullayeva.astrostem

import android.app.Application
import android.content.Context
import android.widget.Toast

class MyApp: Application() {

    companion object {
        private lateinit var instance: MyApp

        fun getContext(): Context {
            return instance.applicationContext
        }

        fun showToast(text:String){
            Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}