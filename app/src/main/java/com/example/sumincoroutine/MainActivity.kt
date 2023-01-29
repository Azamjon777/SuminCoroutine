package com.example.sumincoroutine

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.sumincoroutine.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.buttonLoad.setOnClickListener {
            lifecycleScope.launch {
                //если activity умрет, то и наши запрсоы отменятся
                loadData()
            }
        }
    }

    private suspend fun loadData() {
        binding.progress.isVisible = true
        binding.buttonLoad.isEnabled = false
        val loadCity = loadCity()

        binding.tvLocation.text = loadCity
        val loadTemp = loadTemp(loadCity)

        binding.tvTemperature.text = loadTemp.toString()
        binding.progress.isVisible = false
        binding.buttonLoad.isVisible = true

    }

    private suspend fun loadCity(): String {
        delay(5000)
        return "Tashkent"
    }

    private suspend fun loadTemp(city: String): Int {
        Toast.makeText(
            this, "Loading temperature for $city", Toast.LENGTH_SHORT
        ).show()
        delay(5000)
        return 17
    }

/* private fun loadCity(callback: (String) -> Unit) {
     thread {
         Thread.sleep(5000)
         runOnUiThread {
             callback.invoke("Tashkent")
         }
     }
 }

 private fun loadTemp(city: String, callback: (Int) -> Unit) {
     thread {//здесь создается другой поток
         runOnUiThread {// или по другому Handler(Looper.getMainLooper()).post{}
             //здесь отправляем на главный поток обьекты типа Runnable
             Toast.makeText(
                 this,
                 "Loading temperature for $city",
                 Toast.LENGTH_SHORT
             ).show()
         }
         Thread.sleep(5000)
         runOnUiThread {// runOnUiThread этот метод вызывает какойто код на главном потоке
             callback.invoke(17)
         }
     }
 }*/
}