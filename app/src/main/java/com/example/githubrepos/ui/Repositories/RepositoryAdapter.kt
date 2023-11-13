package com.example.githubrepos.ui.Repositories

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepos.databinding.ViewRepositoryBinding
import com.example.githubrepos.model.Repository

class RepositoryAdapter(private val repositoryList : ArrayList<Repository>) :RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>(){

    class RepositoryViewHolder(val binding: ViewRepositoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Repository) {
            binding.viewRepositoryName.text = data.name
            binding.viewRepositoryAuthor.text = data.author
            binding.viewRepositoryForks.text = data.forks.toString()
            binding.viewRepositoryWatchers.text = data.watchers.toString()
            binding.viewRepositoryIssues.text = data.issues.toString()
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
    }

}