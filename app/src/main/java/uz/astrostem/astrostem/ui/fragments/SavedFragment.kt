package uz.astrostem.astrostem.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import uz.astrostem.astrostem.R
import uz.astrostem.astrostem.databinding.FragmentSavedBinding
import uz.astrostem.astrostem.utils.Constants
import uz.astrostem.astrostem.utils.TYPE
import uz.astrostem.astrostem.vm.MainViewModel

class SavedFragment : Fragment(R.layout.fragment_saved) {

    private var _binding: FragmentSavedBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSavedBinding.bind(view)

        startObservers()
        initViews()
    }

    private fun initViews() {
        binding.apply {
            btnTheoretical.setOnClickListener {
                openFragment(TYPE.T)
            }
            btnPractical.setOnClickListener {
                openFragment(TYPE.P)
            }
            btnLaboratory.setOnClickListener {
                openFragment(TYPE.L)
            }
        }
    }

    private fun openFragment(fragmentType: TYPE) {
        Constants.FRAGMENT_TYPE = fragmentType
        findNavController().navigate(R.id.action_savedFragment_to_courseDetailFragment)
    }

    private fun startObservers() {
        binding.apply {

            viewModel.getTheoreticalLike.observe(viewLifecycleOwner) { isLiked ->
                btnSavedTheoretical.setOnClickListener {
                    viewModel.setTheoreticalLike(!isLiked)
                }
                when (isLiked) {
                    true -> {
                        tvEmptySavedCourses.visibility = View.GONE
                        btnTheoretical.visibility = View.VISIBLE
                    }
                    false -> {
                        tvEmptySavedCourses.visibility = View.VISIBLE
                        btnTheoretical.visibility = View.GONE
                    }
                }
            }
            viewModel.getPracticalLike.observe(viewLifecycleOwner) { isLiked ->
                btnSavedPractical.setOnClickListener {
                    viewModel.setPracticalLike(!isLiked)
                }
                when (isLiked) {
                    true -> {
                        tvEmptySavedCourses.visibility = View.GONE
                        btnPractical.visibility = View.VISIBLE
                    }
                    false -> {
                        tvEmptySavedCourses.visibility = View.VISIBLE
                        btnPractical.visibility = View.GONE
                    }
                }
            }
            viewModel.getLaboratoryLike.observe(viewLifecycleOwner) { isLiked ->
                btnSavedLaboratory.setOnClickListener {
                    viewModel.setLaboratoryLike(!isLiked)
                }
                when (isLiked) {
                    true -> {
                        tvEmptySavedCourses.visibility = View.GONE
                        btnLaboratory.visibility = View.VISIBLE
                    }
                    false -> {
                        tvEmptySavedCourses.visibility = View.VISIBLE
                        btnLaboratory.visibility = View.GONE
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}