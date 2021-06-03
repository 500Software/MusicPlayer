package player.musicplayer.music

import adsingleton.inAppMusicPauseInterstitial
import adsingleton.onBackPressedInterstitial
import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.musicplayer.commons.extensions.checkUseEnglish
import com.onesignal.OneSignal
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig

const val ONESIGNAL_APP_ID = "app-id"

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        MobileAds.initialize(this) {}
        onBackPressedInterstitial.getInstance(this).createAd()
        inAppMusicPauseInterstitial.getInstance(this).createAd()

        val config = YandexMetricaConfig.newConfigBuilder("app-id").build()
        YandexMetrica.activate(applicationContext, config)
        YandexMetrica.enableActivityAutoTracking(this)

        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)

        checkUseEnglish()

    }
}
