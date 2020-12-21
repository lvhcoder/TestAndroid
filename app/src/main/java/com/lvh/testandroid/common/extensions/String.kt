package com.lvh.testandroid.common.extensions

import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.regex.Pattern

fun String.encodeSH256(): String {
    var md: MessageDigest? = null
    var oauthToken = ""
    try {
        md = MessageDigest.getInstance("SHA-256")
        md!!.update(this.toByteArray(charset("UTF-8")))
        val digest = md.digest()
        oauthToken = String.format("%064x", java.math.BigInteger(1, digest))
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    } catch (e: UnsupportedEncodingException) {
        e.printStackTrace()
    }

    return oauthToken
}

fun String.isEmailValid(): Boolean {
    val regex = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}"
    val pattern = Pattern.compile(regex)
    val matcher = pattern.matcher(this)
    return matcher.matches()

}

fun String.isValidPassword(): Boolean {
    if (this == null) return false
    if (this.length < 8) return false
    return !this.contains(" ")
}