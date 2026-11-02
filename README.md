# Fermata YouTube Lite — college prototype

This fork packages only Fermata's web module and registers only the YouTube addon. It opens directly to YouTube and replaces the folders, favorites, playlists, and browser navigation with YouTube and a settings menu. Shared Fermata UI and media-service code remain, so this is larger than a minimal WebView wrapper.

VLC, ExoPlayer, IPTV, casting, network/cloud-storage addons, and AI modules are excluded from the build. Release builds enable code and resource shrinking. The default package is `me.aap.fermata.youtubelite.auto`, the label is **Fermata YouTube Lite**, and the default ABI is ARM64 for the Moto G85. It can coexist with upstream Fermata.

## Vehicle compatibility

This fork does not add a motion-lock bypass or establish support for video playback while driving. Android Auto compatibility, playback, fullscreen, sign-in, and reconnect behavior still require testing on the Moto G85 running Android 16 and the Maruti Suzuki Victoris. Built-in wireless Android Auto does not establish compatibility with the alternative adapters described by the upstream maintainer. See [upstream's Android 14+ compatibility discussion](https://github.com/AndreyPavlenko/Fermata/discussions/432).

Use a stationary vehicle or a bench/simulator for the college demonstration. Upstream Android Auto integration is retained without changes. Google may reject sign-in through an embedded browser. Top-level navigation permits only HTTPS YouTube pages and specific Google account/YouTube consent hosts.

## Build and check

Use JDK 21 and Android SDK platform 36. Set `sdk.dir` in the untracked `local.properties` file or configure the Android SDK environment. The existing upstream test signing configuration is for development; production distribution needs a private signing key.

```powershell
.\gradlew.bat :fermata:packageAutoReleaseUniversalApk :web:testAutoDebugUnitTest
```

The APK is written to `fermata/build/outputs/apk_from_bundle/autoRelease/`. Tests check the reduced addon registry, shared-class initialization without the removed addons, and navigation/account-flow boundaries. APK size does not measure WebView memory usage.

## Validation — November 2, 2026

The ARM64 release APK builds successfully and is 3,343,143 bytes (3.19 MiB). All three regression tests and the release vital lint check pass. APK inspection verifies the separate package/label, API 36 target, and fused modules `base,web`. APK signature verification passes. No phone is connected, so playback, sign-in, layout, and vehicle behavior remain untested. Existing upstream resource/manifest and Gradle deprecation warnings remain.

The test APK is copied to `dist/Fermata-YouTube-Lite-2.0.2-test.apk`. Its SHA-256 is `5A952ACD3899C45FFA904568EEE82BC207ED083204A80DF79B05530D0A0C6022`.

The original upstream README and GPL-3.0 license are retained below.

---

## !!!Be aware of scam!!! The sites like https :// fermata-auto . com  are 100% scam. This app is free and opensource!


## Fermata Media Player
[![Download the latest APK](https://img.shields.io/github/v/release/AndreyPavlenko/Fermata?label=Download%20APK&logo=github&style=for-the-badge)](https://github.com/AndreyPavlenko/Fermata/releases/latest)

## About
Fermata Media Player is a free, open source audio, video and TV player with a simple and intuitive interface. It is focused on playing media files organized in folders and playlists.

Supported features:

* Play media files organized in folders
* IPTV addon with support for XMLTV EPG and Catchup
* Remembers the last played track and position for each folder
* Support for favorites and playlists
* Support for CUE and M3U playlists
* Support for bookmarks
* Audio effects: Equalizer, Bass/Volume Boost and Virtualizer
* Configure audio effects for individual tracks and folders
* Configure playback speed for individual tracks and folders
* Customizable titles and subtitles
* Support for Android Auto
* Support for Android TV
* Show favorites and playlists on Android TV home screen
* Pluggable media engines: MediaPlayer, ExoPlayer and VLC
* Video player with support for subtitles and audio streams (VLC Engine only)

## Building the project
* Download and install the latest Android SDK or Android Studio from https://developer.android.com/studio/
* Set the environment variable ANDROID_SDK_ROOT pointing to the SDK directory
```bash
export ANDROID_SDK_ROOT=<path to android SDK>
```

### Clone the repository
```bash
git clone --recurse-submodules https://github.com/AndreyPavlenko/Fermata.git
cd Fermata
```

### Build AAB
```bash
./gradlew bundleAutoRelease -PAPP_ID_SFX=.type.your.pkg.sfx.here
find $PWD -name *.aab
```

### Build APK
```bash
./gradlew bundleAutoRelease -PAPP_ID_SFX=.type.your.pkg.sfx.here
find $PWD -name *.apk
```

### Building in docker
```bash
docker run -ti --name Fermata andreypavlenko/fermata
```
Enter the requested key alias and password, when prompted.
Build the required package using the above commands.
To copy the built package to the host machine, open a new terminal and run:
```bash
docker cp Fermata:/home/mobiledevops/Fermata/fermata/build/outputs/bundle/autoRelease/ .
```


## Donation
If you like the application, please consider making a donation:

[PayPal](https://www.paypal.com/donate/?hosted_button_id=NP5Q3YDSCJ98N)

[CloudTips](https://pay.cloudtips.ru/p/a03a73da)

[Yandex Money](https://money.yandex.ru/to/410014661137336)
