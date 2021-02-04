package com.androdevlinux.coinfeed.utils

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

object TimeUtil {

    @JvmStatic
    fun main(args: Array<String>) {
        val apiDateTime = LocalDateTime.parse(
            "2021-02-04T06:53:41.047951Z", DateTimeFormatter.ofPattern(
                "yyyy-MM-dd'T'HH:mm:ss.SSSSSSz"
            )
        )
        apiDateTime.atZone(ZoneId.of("UTC"))
        val currentDateTime = LocalDateTime.now(ZoneId.of("UTC"))
        println(apiDateTime.until(currentDateTime, ChronoUnit.MINUTES).toString())

    }

    fun getMinWithMicroSecs(time : String): Long {
        val apiDateTime = LocalDateTime.parse(
            time, DateTimeFormatter.ofPattern(
                "yyyy-MM-dd'T'HH:mm:ss.SSSSSSz"
            )
        )
        apiDateTime.atZone(ZoneId.of("UTC"))
        val currentDateTime = LocalDateTime.now(ZoneId.of("UTC"))
        return apiDateTime.until(currentDateTime, ChronoUnit.MINUTES)
    }

    fun getMinWithMilliSecs(time : String): Long {
        val apiDateTime = LocalDateTime.parse(
            time, DateTimeFormatter.ofPattern(
                "yyyy-MM-dd'T'HH:mm:ssz"
            )
        )
        apiDateTime.atZone(ZoneId.of("UTC"))
        val currentDateTime = LocalDateTime.now(ZoneId.of("UTC"))
        return apiDateTime.until(currentDateTime, ChronoUnit.MINUTES)
    }
}