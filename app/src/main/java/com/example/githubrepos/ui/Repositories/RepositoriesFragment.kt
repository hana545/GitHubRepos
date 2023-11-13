package com.example.githubrepos.ui.Repositories

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubrepos.R
import com.example.githubrepos.databinding.FragmentRepositoriesBinding
import com.example.githubrepos.model.Repository

class RepositoriesFragment : Fragment() {

    private lateinit var _binding: FragmentRepositoriesBinding
    private val binding get() = _binding

    private lateinit var repoAdapter: RepositoryAdapter
    private lateinit var repoList :ArrayList<Repository>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentRepositoriesBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getRepositories()

        binding.btnNavigate.setOnClickListener {
           findNavController().navigate(R.id.action_homFragment_to_detailsFragment)

        }

        repoAdapter = RepositoryAdapter(repoList)
        binding.recylerViewRepositories.apply{
            layoutManager = LinearLayoutManager(requireContext())
            adapter = repoAdapter
        }

    }

private fun getRepositories(){
    repoList = arrayListOf(
        Repository("501","Harshita","Harshita@gmail.com", "", "", 5,8,1),
        Repository("502","minu","minu@gmail.com","", "", 6,8,4),
        Repository("503","ram","ram@gmail.com","", "", 1,8,6),
        Repository("504","sham","sham@gmail.com","", "", 5,3,1),
        Repository("505","raja","raja@gmail.com","", "", 1,1,8),
        Repository("506","harsh","harsh@gmail.com","", "", 7,1,10),
        Repository("507","harshu","harshu@gmail.com","", "", 0,8,6),
        Repository("508","xyz","xyz@gmail.com","", "", 3,2,0),
        Repository("509","abc","abc@gmail.com","", "", 8,0,0),
        Repository("510","pqr","pqr@gmail.com","", "", 4,6,1),
    )
}

}