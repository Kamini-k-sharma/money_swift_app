package com.ecommerce.moneyswift.utils

/**
 * app constants
 * should be placed in the local properties
 */
object Constants {

    const val SECRET_KEY =
        "sk_test_51PlWSF09vmZOOwtVQXDriqejtj87hpG2G1IdnQ5VPBUsse784ct0JVEk6rJmOrf8YCzzYpibWIjr4jzS78qBpznk00dTnqZXSz"

    const val PUBLISH_KEY =
        "pk_test_51PlWSF09vmZOOwtVUAeuvslp0AldeBcHAqkHLO7hcKnZPvdGI7eGGBbYCybvcELvdkY08sPnppAUHFor1ALmmGjE00aMzFDFk9"

    const val AUTHORIZATION = "Bearer $SECRET_KEY"

    const val BASE_URL = "https://api.stripe.com"
}
