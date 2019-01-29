package com.example.razer.exhibition

import android.content.Context
import com.example.myapplication.Exhibit
import com.example.myapplication.ExhibitsLoader
import java.io.IOException
import java.nio.charset.Charset
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import org.json.JSONArray
import java.lang.reflect.Type
import org.json.JSONException
import android.provider.Contacts.People
import com.google.gson.annotations.SerializedName
import com.jakewharton.retrofit2.adapter.rxjava2.Result.response
import com.google.gson.GsonBuilder








//class for loading data from assets
class ExhibitionDataLoader(var context: Context) : ExhibitsLoader {
    override fun getExhibitList(): List<Exhibit> {
        var jsonObject: JSONObject? = null

        val gsonBuilder = GsonBuilder()
        gsonBuilder.setDateFormat("M/d/yy hh:mm a")
        var gson = gsonBuilder.create()

        val listType = object : TypeToken<List<Exhibit>>() {}.type
        try {
            jsonObject = JSONObject(loadJSONFromAsset());
            val posts: List<Exhibit> = gson.fromJson(jsonObject!!.getJSONArray("list").toString(), listType)
            return posts
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return emptyList()
    }

    private inner class JsonDataWrapper{
        @SerializedName("list")
        var list: List<Exhibit> = emptyList()
    }

    private fun loadJSONFromAsset(): String {
        var json: String? = null
        try {
            val stream = context.getAssets().open("rest.json")
            val size = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()
            json = String(buffer, Charset.defaultCharset())
        } catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
    }
}