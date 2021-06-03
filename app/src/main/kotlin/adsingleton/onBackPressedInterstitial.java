package adsingleton;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import org.jetbrains.annotations.NotNull;

public class onBackPressedInterstitial {

    private static onBackPressedInterstitial mInstance;
    public Context mcontext;
    private InterstitialAd mInterstitialAd;

    private onBackPressedInterstitial(Context context) {
        mcontext = context.getApplicationContext();
    }

    public static synchronized onBackPressedInterstitial getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new onBackPressedInterstitial(context);
        }
        return mInstance;
    }

    public void createAd() {

        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(mcontext, "ad-id", adRequest, new InterstitialAdLoadCallback() {

            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                mInterstitialAd = interstitialAd;
                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        final Handler handler = new Handler(Looper.getMainLooper());
                        handler.postDelayed(onBackPressedInterstitial.this::createAd, 240000);
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(@NotNull AdError adError) {

                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        mInterstitialAd = null;
                    }
                });
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                mInterstitialAd = null;
            }

        });
    }

    public InterstitialAd getInterstitialAd() {
        return mInterstitialAd;
    }

}
