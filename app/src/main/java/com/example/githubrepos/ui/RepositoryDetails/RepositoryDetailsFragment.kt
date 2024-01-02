package com.example.githubrepos.ui.RepositoryDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.githubrepos.R
import com.example.githubrepos.databinding.FragmentRepositoryDetailsBinding
import com.example.githubrepos.model.Repository
import com.example.githubrepos.ui.MainActivity
import com.google.android.material.chip.Chip

class RepositoryDetailsFragment : Fragment() {

    private lateinit var _binding: FragmentRepositoryDetailsBinding
    private val binding get() = _binding

    private val viewModel by viewModels<RepositoryDetailsViewModel>()
    private val args : RepositoryDetailsFragmentArgs by navArgs()

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

        bindRepoData()

    }

    private fun bindRepoData() {
        val repo = args.repo!!
        binding.apply {
            repoDetailsDescription.text  = repo.name
            repoDetailsAuthorName.text = repo.author.name
            repoDetailsDescription.text = repo.description
            repoDetailsCreatedAt.text = repo.created
            repoDetailsUpdatedAt.text = repo.updated
            repoDetailsStars.text = repo.forks.toString()
            repoDetailsWatchers.text = repo.watchers.toString()
            repoDetailsIssues.text = repo.issues.toString()
            repoDetailsForks.text = repo.forks.toString()
        }
        /*for (lang in repo.languages){
            createLanguageChip(lang)
        }*/
    }

    private fun createLanguageChip(language : String) {
        val chip = this.layoutInflater.inflate(R.layout.layout_language_chip, binding.repoDetailsLanguageChips, false) as Chip
        chip.text = language
        binding.repoDetailsLanguageChips.addView(chip)
    }

    private fun initActionBar() {
        (activity as MainActivity).apply {
            setSupportActionBar(binding.toolbar)
            binding.toolbar.title = "${args.repo?.name} Details"
            binding.repoDetailsBtnGoToGit.setOnClickListener {
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