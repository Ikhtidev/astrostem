package uz.astrostem.astrostem.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import uz.astrostem.astrostem.R
import uz.astrostem.astrostem.database.ThemeDatabase
import uz.astrostem.astrostem.databinding.FragmentHomeBinding
import uz.astrostem.astrostem.ui.TestActivity
import uz.astrostem.astrostem.utils.Constants.Companion.FRAGMENT_TYPE
import uz.astrostem.astrostem.utils.Constants.Companion.TEST_ID
import uz.astrostem.astrostem.utils.TYPE
import uz.astrostem.astrostem.vm.MainViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val themeDatabase: ThemeDatabase by lazy {
        ThemeDatabase.getInstance()
    }
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        startObservers()
        initViews()
    }

    private fun initViews() {
        setTrainingViews()
        setTestViews()
    }

    private fun setTrainingViews() {
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
            btnCrossword.setOnClickListener {
                Toast.makeText(activity, getString(R.string.crossword1), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startObservers() {
        binding.apply {
            viewModel.getTheoreticalLike.observe(viewLifecycleOwner) { isLiked ->
                btnSavedTheoretical.setOnClickListener {
                    viewModel.setTheoreticalLike(!isLiked)
                }
                when (isLiked) {
                    true -> {
                        btnSavedTheoretical.setImageResource(R.drawable.ic_save_mark)
                    }

                    false -> {
                        btnSavedTheoretical.setImageResource(R.drawable.ic_unsave_mark)
                    }
                }
            }
            viewModel.getPracticalLike.observe(viewLifecycleOwner) { isLiked ->
                btnSavedPractical.setOnClickListener {
                    viewModel.setPracticalLike(!isLiked)
                }
                when (isLiked) {
                    true -> {
                        btnSavedPractical.setImageResource(R.drawable.ic_save_mark)
                    }

                    false -> {
                        btnSavedPractical.setImageResource(R.drawable.ic_unsave_mark)
                    }
                }
            }
            viewModel.getLaboratoryLike.observe(viewLifecycleOwner) { isLiked ->
                btnSavedLaboratory.setOnClickListener {
                    viewModel.setLaboratoryLike(!isLiked)
                }
                when (isLiked) {
                    true -> {
                        btnSavedLaboratory.setImageResource(R.drawable.ic_save_mark)
                    }

                    false -> {
                        btnSavedLaboratory.setImageResource(R.drawable.ic_unsave_mark)
                    }
                }
            }
        }
    }

    private fun setTestViews() {
        binding.apply {
            val test1 = themeDatabase.testDao().getAllTests()[0]
            val test2 = themeDatabase.testDao().getAllTests()[1]
            val test3 = themeDatabase.testDao().getAllTests()[2]
            val test4 = themeDatabase.testDao().getAllTests()[3]

            tvTestName.text = test1.name
            examCountTests.text = test1.testsCount.toString()
            examBestResult.text = getString(R.string.all_test_count, test1.testResult.toString())
            btnTest1.setOnClickListener {
                openTestActivity(0)
            }

            tvTestName2.text = test2.name
            examCountTests2.text = test2.testsCount.toString()
            examBestResult2.text = getString(R.string.all_test_count, test2.testResult.toString())
            btnTest2.setOnClickListener {
                openTestActivity(1)
            }

            tvTestName3.text = test3.name
            examCountTests3.text = test3.testsCount.toString()
            examBestResult3.text = getString(R.string.all_test_count, test3.testResult.toString())
            btnTest3.setOnClickListener {
                openTestActivity(2)
            }

            tvTestName4.text = test4.name
            examCountTests4.text = test4.testsCount.toString()
            examBestResult4.text = getString(R.string.all_test_count, test4.testResult.toString())
            btnTest4.setOnClickListener {
                openTestActivity(3)
            }
        }
    }

    private fun openTestActivity(testId: Int) {
        TEST_ID = testId
        startActivity(Intent(activity, TestActivity::class.java))
    }

    private fun openFragment(fragmentType: TYPE) {
        FRAGMENT_TYPE = fragmentType
        findNavController().navigate(R.id.action_homeFragment_to_courseDetailFragment)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}