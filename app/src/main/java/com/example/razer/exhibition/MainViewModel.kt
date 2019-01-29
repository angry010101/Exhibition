package com.example.razer.exhibition

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.myapplication.Exhibit

class MainViewModel(application: Application): AndroidViewModel(application) {
    private var loader = ExhibitionDataLoader(application.applicationContext)
    var data = MutableLiveData<List<Exhibit>>()

    fun requestExhibits(){
        data.value = loader.getExhibitList()
    }

}