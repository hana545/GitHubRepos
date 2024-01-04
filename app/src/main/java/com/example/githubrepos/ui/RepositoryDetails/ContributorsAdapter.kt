package com.example.githubrepos.ui.RepositoryDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepos.databinding.ItemContributorBinding
import com.example.githubrepos.model.User

class ContributorsAdapter(private var contributorsList: MutableList<User>
) :RecyclerView.Adapter<ContributorsAdapter.ContributorViewHolder>(){

    class ContributorViewHolder(val binding: ItemContributorBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: User) {
            binding.viewContributorName.text = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContributorViewHolder {
        val binding = ItemContributorBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ContributorViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return contributorsList.size
    }

    override fun onBindViewHolder(holder: ContributorViewHolder, position: Int) {
        holder.bind(contributorsList[position])
    }

    fun setItems(contributors: MutableList<User>) {
        contributorsList = contributors
        notifyDataSetChanged()
    }

}