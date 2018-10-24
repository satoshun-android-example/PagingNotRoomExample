package com.github.satoshun.example.paging.notroom

import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity : AppCompatActivity(),
    CoroutineScope {
  private val job = Job()
  override val coroutineContext: CoroutineContext
    get() = Dispatchers.Main + job

  override fun onDestroy() {
    super.onDestroy()
    job.cancel()
  }
}
