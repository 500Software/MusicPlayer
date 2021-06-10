package player.musicplayer.music

import android.os.Bundle
import android.view.Menu
import android.widget.TextView
import player.musicplayer.music.activities.SimpleActivity

class ChangelogAppActivity : SimpleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_changelog_app)
        val textviewChangeLog = findViewById<TextView>(R.id.changelog_text)
        textviewChangeLog.text = "Music Player 2.0.0 - Removed some settings, Color changes, Recreating about activity, Admob SDK integration, Onesignal SDK integration, Yandex AppMetrica SDK integration.\n\nMusic Player 3.0.0 - Removed Yandex AppMetrica, Fixed Admob ads, And new app icon."
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        updateMenuItemColors(menu)
        return super.onCreateOptionsMenu(menu)
    }

}
