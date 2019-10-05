package com.example.majournee.fragments

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.majournee.R
import com.example.majournee.adapters.MusicAdapter
import com.example.majournee.models.ParserSong
import kotlinx.android.synthetic.main.fragment_music.view.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class MusicFragment : Fragment() {

    private lateinit var mRecycler: RecyclerView
    private val mLayoutManager by lazy { LinearLayoutManager(context) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_music, container, false)

        activity?.setTitle(R.string.top_music)
        DownloaderTask().execute("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=10/xml")
        mRecycler = rootView.rv_music as RecyclerView
        DownloaderTask().setRecyclerView(mRecycler, mLayoutManager)

        return rootView
    }

    class DownloaderTask : AsyncTask<String, Unit, String>() {

        private var mAdapter: MusicAdapter? = null

        override fun doInBackground(vararg string: String): String? {
            val rssFeed: String? = downloadXMLFile(string[0])
            if (rssFeed == null) Log.e("MyError", "RSS NULL")
            return rssFeed
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            val parseData = ParserSong()
            parseData.parseData(result)
            val songs = parseData.songs
            mAdapter = MusicAdapter(songs)
        }

        fun setRecyclerView(mRecycler: RecyclerView, mLayoutManager: LinearLayoutManager) =
            with(mRecycler) {
                setHasFixedSize(true)
                itemAnimator = DefaultItemAnimator()
                layoutManager = mLayoutManager
                adapter = mAdapter
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
            } catch (e: IOException) {
                Log.e("MyError", "IOException Error")
            }
            return null
        }
    }
}
