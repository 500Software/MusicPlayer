package player.musicplayer.music.activities

import adsingleton.onBackPressedInterstitial
import android.view.KeyEvent
import android.view.MenuItem
import com.musicplayer.commons.activities.BaseSimpleActivity
import player.musicplayer.music.R


open class SimpleActivity : BaseSimpleActivity() {

    override fun getAppIconIDs() = arrayListOf(
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher
    )

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK && isTaskRoot) {
            finish()
            true
        } else {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                backInterstitial()
            }
            super.onKeyDown(keyCode, event)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> backInterstitial()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun backInterstitial() {
        if (onBackPressedInterstitial.getInstance(this).interstitialAd != null){
            onBackPressedInterstitial.getInstance(this).interstitialAd.show(this@SimpleActivity)
        }
        finish()
    }

    override fun getAppLauncherName() = getString(R.string.app_launcher_name)

}
