package com.example.lab4

import android.content.res.Resources
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var mPlayer: MediaPlayer
    private lateinit var playButton: Button
    private lateinit var pauseButton: Button
    private lateinit var stopButton: Button
    private var musicMap: MutableMap<String, String> = mutableMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val res: Resources = resources

        val musicNames: Array<String> = res.getStringArray(R.array.music)
        val musicRes: Array<String> = res.getStringArray(R.array.musicRes)
        for (i in musicNames.indices)
        {
            musicMap[musicNames[i]] = musicRes[i]
        }
        mPlayer = MediaPlayer.create(this, R.raw.loseyourselftodance)
        mPlayer.setOnCompletionListener { stopPlay() }

        playButton = findViewById<View>(R.id.playButton) as Button
        pauseButton = findViewById<View>(R.id.pauseButton) as Button
        stopButton = findViewById<View>(R.id.stopButton) as Button
        pauseButton.isEnabled = false
        stopButton.isEnabled = false

        val musicList = findViewById<ListView>(R.id.musicList) as ListView
        musicList.onItemClickListener = OnItemClickListener { parent, v, position, id -> // по позиции получаем выбранный элемент
            val selectedMusic: String = parent.getItemAtPosition(position) as String
            stopPlay()
            val temp: Uri = Uri.parse("android.resource://"  + packageName + musicMap[selectedMusic])
//            mPlayer = MediaPlayer.create(this, temp)
//            mPlayer.setOnCompletionListener { stopPlay() }
            mPlayer.reset()
            mPlayer.setDataSource(this, temp)
            mPlayer.prepare();

        }
    }

     private fun stopPlay() {
        mPlayer.stop()
        pauseButton.isEnabled = false
        stopButton.isEnabled = false
        try {
            mPlayer.prepare()
            mPlayer.seekTo(0)
            playButton.isEnabled = true
        } catch (t: Throwable) {
            Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun play(view: View?) {
        mPlayer.start()
        playButton.isEnabled = false
        pauseButton.isEnabled = true
        stopButton.isEnabled = true
    }

    fun pause(view: View?) {
        mPlayer.pause()
        playButton.isEnabled = true
        pauseButton.isEnabled = false
        stopButton.isEnabled = true
    }
    fun stop(view: View?) {
        stopPlay()
    }
    override fun onDestroy() {
        super.onDestroy()
        if (mPlayer.isPlaying) {
            stopPlay()
        }
    }
}
