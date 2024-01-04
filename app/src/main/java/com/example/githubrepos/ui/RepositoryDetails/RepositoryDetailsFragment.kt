package com.example.githubrepos.ui.RepositoryDetails

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubrepos.R
import com.example.githubrepos.databinding.FragmentRepositoryDetailsBinding
import com.example.githubrepos.model.Repository
import com.example.githubrepos.model.User
import com.example.githubrepos.ui.MainActivity
import com.example.githubrepos.ui.Repositories.RepositoriesFragmentDirections
import com.example.githubrepos.ui.Repositories.RepositoryAdapter
import com.google.android.material.chip.Chip
import java.text.Format
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class RepositoryDetailsFragment : Fragment() {

    private lateinit var _binding: FragmentRepositoryDetailsBinding
    private val binding get() = _binding
    private lateinit var contributorsAdapter: ContributorsAdapter

    private val viewModel by viewModels<RepositoryDetailsViewModel>()
    private val args : RepositoryDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentRepositoryDetailsBinding.inflate(inflater, container, false)
        viewModel.getRepository(args.user,args.repo)
        return _binding.root
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindRepoData()


    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun bindRepoData() {
        viewModel.repoLiveData.observe(viewLifecycleOwner) { currentRepo ->
            if (currentRepo != null) {
                binding.apply {
                    repoDetailsDescription.text  = currentRepo.name
                    repoDetailsAuthorName.text = currentRepo.author.name
                    repoDetailsDescription.text = currentRepo.description
                    repoDetailsCreatedAt.text = parseDateString(currentRepo.created)
                    repoDetailsUpdatedAt.text = parseDateString(currentRepo.updated)
                    repoDetailsStars.text = currentRepo.forks.toString()
                    repoDetailsWatchers.text = currentRepo.watchers.toString()
                    repoDetailsIssues.text = currentRepo.issues.toString()
                    repoDetailsForks.text = currentRepo.forks.toString()

                    toolbar.title = "${currentRepo.name} Details"
                    initActionBar()
                }
            }
        }
        viewModel.languageList.observe(viewLifecycleOwner) { languages ->
            if (languages != null) {
                for (lang in languages){
                    createLanguageChip(lang)
                }
                if(languages.isEmpty()) createLanguageChip("None")

            }
        }

        initContributorsRecycler()
        viewModel.contributorsList.observe(viewLifecycleOwner) { contributors ->
            if (contributors != null) {
               updateItems(contributors)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun parseDateString(dateString: String) : String{
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val datetime = LocalDateTime.parse(dateString, formatter)

        return datetime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
    }

    private fun createLanguageChip(language : String) {
        val chip = this.layoutInflater.inflate(R.layout.layout_language_chip, binding.repoDetailsLanguageChips, false) as Chip
        chip.text = language
        binding.repoDetailsLanguageChips.addView(chip)
    }

    private fun initContributorsRecycler() {
        contributorsAdapter = ContributorsAdapter(mutableListOf())
        binding.repoDetailsRecyclerViewContributors.apply{
            layoutManager = LinearLayoutManager(requireContext())
            adapter = contributorsAdapter
        }
    }

    private fun updateItems(contributors: MutableList<User>) {
        contributorsAdapter.setItems(contributors)
    }

    private fun initActionBar() {
        (activity as MainActivity).apply {
            setSupportActionBar(binding.toolbar)
            binding.toolbar.title = "${viewModel.repoLiveData.value?.name} Details"
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