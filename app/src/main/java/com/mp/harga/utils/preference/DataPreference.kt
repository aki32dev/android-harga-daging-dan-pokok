package com.mp.harga.utils.preference

import com.chibatching.kotpref.KotprefModel

object DataPreference: KotprefModel(){
    var indexClassification by stringPref("Beras")
    var indexLocation by stringPref("Jawa Timur")
}