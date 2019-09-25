package com.example.majournee.fragments

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.majournee.R
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class MusicFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_music, container, false)

        activity?.setTitle(R.string.top_music)
        DownloaderTask().execute("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=10/xml")

        return rootView
    }

    private class DownloaderTask : AsyncTask<String, Unit, String>() {

        override fun doInBackground(vararg string: String): String? {
            val rssFeed: String? = downloadXMLFile(string[0])
            if (rssFeed == null) Log.e("MyError", "RSS NULL")
            return rssFeed
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            Log.d("MyResult", "Apple Data: $result")
        }

        private fun downloadXMLFile(urlPath: String): String? {

            val xmlResult = StringBuilder()

            try {
                val url = URL(urlPath)
                val connection = url.openConnection() as HttpURLConnection
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                var charsRead: Int
                val inputBuffer = CharArray(500)

                while (true) {
                    charsRead = reader.read(inputBuffer)
                    if (charsRead < 0) break
                    if (charsRead > 0) xmlResult.append(String(inputBuffer, 0, charsRead))
                }
                reader.close()
                return xmlResult.toString()


            } catch (e: MalformedURLException) {
                Log.e("MyError", "URL Error")
            }

            catch (e: IOException) {
                Log.e("MyError", "IOException Error")
            }
            return null
        }
    }
}
