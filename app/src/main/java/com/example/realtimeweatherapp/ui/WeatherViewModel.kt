package com.example.realtimeweatherapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realtimeweatherapp.api.Constant
import com.example.realtimeweatherapp.api.NetworkResponse
import com.example.realtimeweatherapp.api.RetrofitInstance
import com.example.realtimeweatherapp.api.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val weatherApi = RetrofitInstance.weatherApi
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
    val weatherResult : LiveData<NetworkResponse<WeatherModel>> = _weatherResult

    fun getData(city: String) {
        _weatherResult.value = NetworkResponse.Loading
        viewModelScope.launch {
            try {
                val response = weatherApi.getWeather(Constant.API_KEY, city)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _weatherResult.value = NetworkResponse.Success(it)
                    }
                } else {
                    _weatherResult.value = NetworkResponse.Error("Failed to load data...")
                }
            }
            catch (e: Exception) {
                Log.e("WeatherViewModel", "Error fetching weather: ${e.message}")
                _weatherResult.value = NetworkResponse.Error("Failed to load data. Please check your internet connection.")
            }

        }
    }
}