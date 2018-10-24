package com.github.satoshun.example.paging.notroom.ui.main

import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
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
import kotlinx.android.synthetic.main.main_act.*
import java.util.concurrent.Executors

class MainActivity : BaseActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_act)

    recycler.layoutManager = LinearLayoutManager(this)
    val adapter = MainPagingAdapter()
    recycler.adapter = adapter

    val sourceFactory = UserDataSourceFactory()
    val livePageList = sourceFactory.toLiveData(
        pageSize = 10,
        fetchExecutor = Executors.newSingleThreadExecutor()
    )
    val listing = Listing(livePageList)
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

data class User(
  val name: String
)

class MainViewHolder(val view: TextView) : RecyclerView.ViewHolder(view)

object UserCallback : DiffUtil.ItemCallback<User>() {
  override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
    return oldItem === newItem
  }

  override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
    return oldItem == newItem
  }
}

class UserDataSourceFactory : DataSource.Factory<String, User>() {
  override fun create(): DataSource<String, User> {
    return UserDataSource()
  }
}

class UserDataSource : PageKeyedDataSource<String, User>() {
  override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, User>) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, User>) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, User>) {
    // do nothing
  }
}

class Listing(
  val pagedList: LiveData<PagedList<User>>
)
