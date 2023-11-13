package com.example.githubrepos.ui.RepositoryDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.githubrepos.databinding.FragmentRepositoryDetailsBinding
import com.example.githubrepos.ui.MainActivity

class RepositoryDetailsFragment : Fragment() {

    private lateinit var _binding: FragmentRepositoryDetailsBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentRepositoryDetailsBinding.inflate(inflater, container, false)
        return _binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initActionBar()

    }

    private fun initActionBar() {
        (activity as MainActivity).apply {
            setSupportActionBar(binding.toolbar)
            binding.toolbar.title = "[Repo Title] Details"
            binding.btnGoToGit.setOnClickListener {
                Toast.makeText(requireContext(), "Navigate to browser", Toast.LENGTH_SHORT).show()
            }
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
            }

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            android.R.id.home -> {
                requireActivity().onBackPressedDispatcher.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}