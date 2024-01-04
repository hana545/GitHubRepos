package com.example.githubrepos.ui.Repositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubrepos.R
import com.example.githubrepos.databinding.FragmentRepositoriesBinding
import com.example.githubrepos.model.Repository
import com.example.githubrepos.ui.RepositoryDetails.RepositoryDetailsViewModel

class RepositoriesFragment : Fragment() {

    private lateinit var _binding: FragmentRepositoriesBinding
    private val binding get() = _binding

    private lateinit var repoAdapter: RepositoryAdapter
    private val viewModel by viewModels<RepositoriesViewModel>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentRepositoriesBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.repoList.observe(viewLifecycleOwner) { repos ->
            updateItems(repos)
        }

        initRepositoriesRecycler()

    }

    private fun initRepositoriesRecycler() {
        repoAdapter = RepositoryAdapter(mutableListOf()){ repo ->
            val direction = RepositoriesFragmentDirections.actionHomeFragmentToDetailsFragment().setRepo(repo)
            findNavController().navigate(direction)
        }
        binding.recylerViewRepositories.apply{
            layoutManager = LinearLayoutManager(requireContext())
            adapter = repoAdapter
        }
    }

    private fun updateItems(repositories: MutableList<Repository>) {
        repoAdapter.setItems(repositories)
    }


}