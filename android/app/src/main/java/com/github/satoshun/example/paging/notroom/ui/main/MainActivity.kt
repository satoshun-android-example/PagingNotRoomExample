package com.github.satoshun.example.paging.notroom.ui.main

import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.paging.toLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.satoshun.example.paging.notroom.BaseActivity
import com.github.satoshun.example.paging.notroom.R
import com.github.satoshun.example.paging.notroom.data.User
import kotlinx.android.synthetic.main.main_act.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

class MainActivity : BaseActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_act)

    recycler.layoutManager = LinearLayoutManager(this)
    val adapter = MainPagingAdapter()
    recycler.adapter = adapter

    val errorLiveData = MutableLiveData<Throwable>()
    val sourceFactory = UserDataSourceFactory(errorLiveData)
    val livePageList = sourceFactory.toLiveData(
        pageSize = 10,
        fetchExecutor = Executors.newSingleThreadExecutor()
    )
    val listing = Listing(
        livePageList,
        errorLiveData
    )
    listing.pagedList.observe(this, Observer {
      adapter.submitList(it!!)
    })
  }
}

class MainPagingAdapter : PagedListAdapter<User, MainViewHolder>(UserCallback) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
    return MainViewHolder(TextView(parent.context))
  }

  override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
    val item = getItem(position) ?: return
    holder.view.text = item.name
  }
}

class MainViewHolder(val view: TextView) : RecyclerView.ViewHolder(view)

object UserCallback : DiffUtil.ItemCallback<User>() {
  override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
    return oldItem === newItem
  }

  override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
    return oldItem == newItem
  }
}

class UserDataSourceFactory(
  private val errorLiveData: MutableLiveData<Throwable>
) : DataSource.Factory<Int, User>() {
  override fun create(): DataSource<Int, User> {
    return UserDataSource(errorLiveData)
  }
}

class UserDataSource(
  private val errorLiveData: MutableLiveData<Throwable>
) : PageKeyedDataSource<Int, User>() {
  override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, User>) {
    GlobalScope.launch {
      delay(1000)
      callback.onResult(
          (0..9).map { User(name = it.toString()) },
          0,
          10
      )
    }
  }

  override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
    GlobalScope.launch {
      delay(1500)
      callback.onResult(
          (params.key..params.key + 9).map { User(name = it.toString()) },
          params.key + 10
      )
    }
  }

  override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
    // do nothing
  }
}

class Listing(
  val pagedList: LiveData<PagedList<User>>,
  val error: LiveData<Throwable>
)
