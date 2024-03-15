package uz.sayfullayeva.astrostem.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import uz.sayfullayeva.astrostem.MyApp
import uz.sayfullayeva.astrostem.R
import uz.sayfullayeva.astrostem.databinding.FragmentSavedBinding
import uz.sayfullayeva.astrostem.utils.Constants
import uz.sayfullayeva.astrostem.utils.TYPE
import uz.sayfullayeva.astrostem.vm.MainViewModel

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

    private fun emptyTvVisibility() {
        binding.apply {
            if (btnTheoretical.visibility == View.GONE && btnPractical.visibility == View.GONE && btnLaboratory.visibility == View.GONE) {
                tvEmptySavedCourses.visibility = View.VISIBLE
            } else tvEmptySavedCourses.visibility = View.INVISIBLE
        }
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
                    MyApp.showToast(
                        if (isLiked) getString(R.string.removed_from_saved)
                        else getString(R.string.added_to_saved)
                    )
                    viewModel.setTheoreticalLike(!isLiked)
                }
                btnTheoretical.visibility = if (isLiked) View.VISIBLE else View.GONE
                emptyTvVisibility()
            }
            viewModel.getPracticalLike.observe(viewLifecycleOwner) { isLiked ->
                btnSavedPractical.setOnClickListener {
                    MyApp.showToast(
                        if (isLiked) getString(R.string.removed_from_saved)
                        else getString(R.string.added_to_saved)
                    )
                    viewModel.setPracticalLike(!isLiked)
                }
                btnPractical.visibility = if (isLiked) View.VISIBLE else View.GONE
                emptyTvVisibility()
            }
            viewModel.getLaboratoryLike.observe(viewLifecycleOwner) { isLiked ->
                btnSavedLaboratory.setOnClickListener {
                    MyApp.showToast(
                        if (isLiked) getString(R.string.removed_from_saved)
                        else getString(R.string.added_to_saved)
                    )
                    viewModel.setLaboratoryLike(!isLiked)
                }
                btnLaboratory.visibility = if (isLiked) View.VISIBLE else View.GONE
                emptyTvVisibility()
            }
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}