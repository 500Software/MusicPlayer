package player.musicplayer.music

import adsingleton.inAppMusicPauseInterstitial
import adsingleton.onBackPressedInterstitial
import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.musicplayer.commons.extensions.checkUseEnglish
import com.onesignal.OneSignal

const val ONESIGNAL_APP_ID = "app-id"

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        MobileAds.initialize(this) {}
        onBackPressedInterstitial.getInstance(this).createAd()
        inAppMusicPauseInterstitial.getInstance(this).createAd()

        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)

        checkUseEnglish()

    }
}
