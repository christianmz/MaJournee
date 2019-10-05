package com.example.majournee.models

import android.util.Log
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.StringReader
import java.util.*

class ParserSong {

    companion object {
        private const val TAG = "ParserSong"
    }

    val songs: ArrayList<Song> = ArrayList()

    fun parseData(xmlData: String?): Boolean {

        var status = true
        lateinit var currentSong: Song
        var inEntry = false
        var hasImage = false
        var textValue = ""

        try {

            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val xpp = factory.newPullParser()
            xpp.setInput(StringReader(xmlData!!))
            var eventType = xpp.eventType

            while (eventType != XmlPullParser.END_DOCUMENT) {

                val tagName = xpp.name

                when (eventType) {

                    XmlPullParser.START_TAG -> {
                        Log.d(TAG, "parseData: StartData:$tagName")
                        if ("entry".equals(tagName, ignoreCase = true)) {
                            inEntry = true
                            currentSong = Song()
                        } else if ("image".equals(tagName, ignoreCase = true) && inEntry) {
                            val imageResolution = xpp.getAttributeValue(null, "height")
                            if (imageResolution != null) {
                                hasImage = "170".equals(imageResolution, ignoreCase = true)
                            }
                        }
                    }

                    XmlPullParser.TEXT -> textValue = xpp.text

                    XmlPullParser.END_TAG -> {
                        Log.d(TAG, "parseData: Close Tag $tagName")
                        if (inEntry) {
                            if ("entry".equals(tagName, ignoreCase = true)) {
                                songs.add(currentSong)
                                inEntry = false
                            } else if ("name".equals(tagName, ignoreCase = true)) {
                                currentSong.name = textValue
                            } else if ("artist".equals(tagName, ignoreCase = true)) {
                                currentSong.artist = textValue
                            } else if ("image".equals(tagName, ignoreCase = true)) {
                                if (hasImage) {
                                    currentSong.image = textValue
                                    hasImage = false
                                }
                            }
                        }
                    }
                }
                eventType = xpp.next()

                for (song: Song in songs) {
                    Log.d(TAG, song.toString())
                }
            }
        } catch (e: Exception) {
            status = false
            e.printStackTrace()
        }
        return status
    }
}
