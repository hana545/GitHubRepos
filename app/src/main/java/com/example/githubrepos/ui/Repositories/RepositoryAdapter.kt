package com.example.githubrepos.ui.Repositories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepos.databinding.ViewRepositoryBinding
import com.example.githubrepos.model.Repository

class RepositoryAdapter(private var repositoryList: MutableList<Repository>,
                        private var onItemClick : (Repository) -> Unit
) :RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>(){

    class RepositoryViewHolder(val binding: ViewRepositoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Repository) {
            binding.viewRepositoryName.text = data.name
            binding.viewRepositoryAuthor.text = data.author
            binding.viewRepositoryForks.text = data.forks.toString()
            binding.viewRepositoryWatchers.text = data.watchers.toString()
            binding.viewRepositoryIssues.text = data.issues.toString()
        }
        fun onClick(action: (Repository) -> Unit, repo: Repository) {
            binding.viewRepositoryCardView.setOnClickListener { action(repo) }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding = ViewRepositoryBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return repositoryList.size
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(repositoryList[position])
        holder.onClick(onItemClick, repositoryList[position])
    }

    fun setItems(repos: MutableList<Repository>) {
        repositoryList = repos
        notifyDataSetChanged()
    }

}