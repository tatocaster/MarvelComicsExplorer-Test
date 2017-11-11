package me.tatocaster.marvelapp.utils

import java.security.MessageDigest

/**
 * https://www.samclarke.com/kotlin-hash-strings/
 */

fun String.sha512(input: String) = hashString("SHA-512", input)

fun String.sha256(input: String) = hashString("SHA-256", input)

fun String.sha1(input: String) = hashString("SHA-1", input)

fun String.md5(input: String) = hashString("MD5", input)

/**
 * Supported algorithms on Android:
 *
 * Algorithm	Supported API Levels
 * MD5          1+
 * SHA-1	    1+
 * SHA-224	    1-8,22+
 * SHA-256	    1+
 * SHA-384	    1+
 * SHA-512	    1+
 */
fun hashString(type: String, input: String) =
        MessageDigest
                .getInstance(type)
                .digest(input.toByteArray())
                .map { String.format("%02X", it).toLowerCase() }
                .joinToString(separator = "")