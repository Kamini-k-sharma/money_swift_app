package com.ecommerce.moneyswift.utils

import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClientParser @Inject constructor() : Parser<String> {

    override suspend fun parse(jsonObject: JSONObject): List<String> {
        //val jsonObject = JSONObject(response)
        val clientSecret = jsonObject.getString("client_secret")
        val list = mutableListOf<String>()

        list.add(clientSecret)

        return list.ifEmpty {
            emptyList()
        }
    }
}
