### CompactPlayerView
Helper for removing exoplayer set up boilerplates and setting attributes on xml

```html
<tm.mr.compactplayerview.PlayerView
    android:id="@+id/player"
    android:layout_width="match_parent"
    android:layout_height="0dp"
    app:src="http://gormless.dx.am/videos/mama.mp4, R.raw.islands, /storage/emulated/0/download/test.mp4"
    app:volume="0.5"
    app:autoplay="true"
    app:repeat_mode="all"
    app:layout_constraintDimensionRatio="16:9"
    app:layout_constraintTop_toTopOf="parent" />
```




#### properties:
* play from **url**, **file**, **raw resource**, **playlist**: 
  * `app:src="http://gormless.dx.am/videos/mama.mp4"`
  * `app:src="/storage/emulated/0/download/test.mp4"`
  * `app:src="R.raw.islands"`
  * `app:src="http://gormless.dx.am/videos/mama.mp4, R.raw.islands, /storage/emulated/0/download/test.mp4"`

* set volume: `app:volume="0.5"`
* set autoplay: `app:autoplay="true"`
* set repead mode: `app:repeat_mode="all|one|none"`




#### implementation:
1. project build.gradle:
   ```gradle
   allprojects {
       repositories {
           maven { url 'https://jitpack.io' }
           ...
       }
   }
   ```

2. app build.gradle
   ```gradle
   android {
       ...
       compileOptions {
           sourceCompatibility 1.8
           targetCompatibility 1.8
       }
       ...
   }
   ...
   dependencies {
       implementation 'com.github.batyrf:CompactPlayerView:latest-release'
       implementation 'com.google.android.exoplayer:exoplayer:2.9.6'
       implementation 'com.google.android.exoplayer:exoplayer-core:2.9.6'
       implementation 'com.google.android.exoplayer:exoplayer-dash:2.9.6'
       implementation 'com.google.android.exoplayer:exoplayer-ui:2.9.6'
   }
   ```

