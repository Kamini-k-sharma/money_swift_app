package com.ecommerce.moneyswift.utils

import org.json.JSONObject

interface Parser<T> {
    suspend fun parse(response: JSONObject): List<T>
}
