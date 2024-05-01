package uz.sayfullayeva.astrostem.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import uz.sayfullayeva.astrostem.MyApp
import uz.sayfullayeva.astrostem.R
import uz.sayfullayeva.astrostem.database.ThemeDatabase
import uz.sayfullayeva.astrostem.databinding.FragmentHomeBinding
import uz.sayfullayeva.astrostem.ui.QuestionsActivity
import uz.sayfullayeva.astrostem.ui.TestActivity
import uz.sayfullayeva.astrostem.utils.Constants
import uz.sayfullayeva.astrostem.utils.Constants.Companion.FRAGMENT_TYPE
import uz.sayfullayeva.astrostem.utils.Constants.Companion.TEST_ID
import uz.sayfullayeva.astrostem.utils.TYPE
import uz.sayfullayeva.astrostem.vm.DataStoreViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val themeDatabase: ThemeDatabase by lazy {
        ThemeDatabase.getInstance()
    }
    private val viewModel by activityViewModels<DataStoreViewModel>()

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
            btnVideos.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_videosFragment)
            }
//            btnCrossword.setOnClickListener {
//                MyApp.showToast(getString(R.string.crossword1))
//            }
        }
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
                btnSavedTheoretical.setImageResource(
                    if (isLiked) R.drawable.ic_save_mark
                    else R.drawable.ic_unsave_mark
                )
            }
            viewModel.getPracticalLike.observe(viewLifecycleOwner) { isLiked ->
                btnSavedPractical.setOnClickListener {
                    MyApp.showToast(
                        if (isLiked) getString(R.string.removed_from_saved)
                        else getString(R.string.added_to_saved)
                    )
                    viewModel.setPracticalLike(!isLiked)
                }
                btnSavedPractical.setImageResource(
                    if (isLiked) R.drawable.ic_save_mark
                    else R.drawable.ic_unsave_mark
                )
            }
            viewModel.getLaboratoryLike.observe(viewLifecycleOwner) { isLiked ->
                btnSavedLaboratory.setOnClickListener {
                    MyApp.showToast(
                        if (isLiked) getString(R.string.removed_from_saved)
                        else getString(R.string.added_to_saved)
                    )
                    viewModel.setLaboratoryLike(!isLiked)
                }
                btnSavedLaboratory.setImageResource(
                    if (isLiked) R.drawable.ic_save_mark
                    else R.drawable.ic_unsave_mark
                )
            }
        }
    }

    private fun setTestViews() {
        binding.apply {
            val test1 = themeDatabase.testDao().getAllTests()[0]
            val test2 = themeDatabase.testDao().getAllTests()[1]
//            val test3 = themeDatabase.testDao().getAllTests()[2]
//            val test4 = themeDatabase.testDao().getAllTests()[3]

            tvTestName1.text = test1.name
            examCountTests1.text = test1.testsCount.toString()
            examBestResult1.text = getString(R.string.all_test_count, test1.testResult.toString())
            btnTest1.setOnClickListener {
                openTestActivity(0)
            }

            tvTestName2.text = test2.name
            examCountTests2.text = test2.testsCount.toString()
            examBestResult2.text = getString(R.string.all_test_count, test2.testResult.toString())
            btnTest2.setOnClickListener {
                openTestActivity(1)
            }

            tvTestName3.text = "Вопросы по предмету «Астрономия Востока»"
            examCountTests3.text = "90"
//            examBestResult3.visibility = View.INVISIBLE
            btnTest3.setOnClickListener {
                Constants.ONLY_QUESTIONS = "Вопросы по предмету «Астрономия Востока»"
                startActivity(Intent(requireActivity(), QuestionsActivity::class.java))
            }

            tvTestName4.text = "Yopiq savollar"
            examCountTests4.text = "150"
//            examBestResult4.text = getString(R.string.all_test_count, test4.testResult.toString())
            btnTest4.setOnClickListener {
                Constants.ONLY_QUESTIONS = "Yopiq savollar"
                startActivity(Intent(requireActivity(), QuestionsActivity::class.java))
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

    override fun onResume() {
        super.onResume()
        setTestViews()
    }
}