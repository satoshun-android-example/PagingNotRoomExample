object Vers {
  val compile_sdk = 28
  val min_sdk = 25
  val target_sdk = 28

  val kotlin = "1.3.0-rc-190"
  val couroutine = "1.0.0-RC1"
}

object Libs {
  val android_plugin = "com.android.tools.build:gradle:3.3.0-beta01"
  val kotlin_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Vers.kotlin}"

  val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Vers.kotlin}"
  val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Vers.couroutine}"
  val ui_coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Vers.couroutine}"

  val coroutine_common = "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${Vers.couroutine}"
  val coroutine_jvm = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Vers.couroutine}"

  val ktx = "androidx.core:core-ktx:1.0.0"

  val appcompat = "androidx.appcompat:appcompat:1.0.0"
  val recyclerview = "androidx.recyclerview:recyclerview:1.0.0"
  val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.0-alpha2"
  val cardview = "androidx.cardview:cardview:1.0.0"

  val material = "com.google.android.material:material:1.0.0"
  val paging = "androidx.paging:paging-runtime:2.1.0-alpha01"
  val paging_ktx = "androidx.paging:paging-runtime-ktx:2.1.0-alpha01"

  val room_common = "androidx.room:room-common:2.0.0"
  val room_runtime = "androidx.room:room-runtime:2.0.0"
  val room_compiler = "androidx.room:room-compiler:2.0.0"

  val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.0.0"
  val livedata = "androidx.lifecycle:lifecycle-livedata:2.0.0"
  val lifecycle_compiler = "androidx.lifecycle:lifecycle-compiler:2.0.0"

  private val vdagger = "2.18"
  val dagger = "com.google.dagger:dagger:$vdagger"
  val dagger_compiler = "com.google.dagger:dagger-compiler:$vdagger"
  val dagger_android = "com.google.dagger:dagger-android:$vdagger"
  val dagger_android_support = "com.google.dagger:dagger-android-support:$vdagger"
  val dagger_android_compiler = "com.google.dagger:dagger-android-processor:$vdagger"

  val emoji = "androidx.emoji:emoji:1.0.0"
  val emoji_compat = "androidx.emoji:emoji-appcompat:1.0.0"
  val emoji_bundled = "androidx.emoji:emoji-bundled:1.0.0"

  val multidex = "androidx.multidex:multidex:2.0.0"

  val rxjava = "io.reactivex.rxjava2:rxjava:2.2.0"
  val rxandroid = "io.reactivex.rxjava2:rxandroid:2.1.0"

  val android_annotation = "androidx.annotation:annotation:1.0.0"

  val junit = "junit:junit:4.12"
  val truth = "com.google.truth:truth:0.42"
  val mockito_kotlin = "com.nhaarman:mockito-kotlin-kt1.1:1.5.0"
  val test_runner = "androidx.test:runner:1.1.0-beta01"
  val test_rule = "androidx.test:rules:1.1.0-beta01"
  val test_junit_ext = "androidx.test.ext:junit:1.0.0-beta02"
  val espresso = "androidx.test.espresso:espresso-core:3.1.0-beta01"
}
