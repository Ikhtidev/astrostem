package uz.astrostem.astrostem.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.astrostem.astrostem.R
import uz.astrostem.astrostem.databinding.FragmentHomeBinding
import uz.astrostem.astrostem.ui.TestActivity
import uz.astrostem.astrostem.utils.Constants.Companion.FRAGMENT_TYPE
import uz.astrostem.astrostem.utils.TYPE

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
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
            btnSavedTheoretical.setOnClickListener {
                Toast.makeText(activity, "Nazariy like bosildi", Toast.LENGTH_SHORT).show()
            }
            btnSavedPractical.setOnClickListener {
                Toast.makeText(activity, "Amaliy like bosildi", Toast.LENGTH_SHORT).show()
            }
            btnSavedLaboratory.setOnClickListener {
                Toast.makeText(activity, "Laboratoriya like bosildi", Toast.LENGTH_SHORT).show()
            }

            btnCrossword.setOnClickListener {
                Toast.makeText(activity, "Krossvord bosildi", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setTestViews() {
        binding.apply {
            tvTestName.text = "Sharq astranomiyasi"
            examCountTests.text = "56"
            examBestResult.text = "70%"
            btnTest1.setOnClickListener {
                startActivity(Intent(activity, TestActivity::class.java))
            }

            tvTestName2.text = "Astranomiyadan 100 ta test 2-kurs"
            examCountTests2.text = "80"
            examBestResult2.text = "80%"
            btnTest2.setOnClickListener {
                Toast.makeText(activity, "test 2", Toast.LENGTH_SHORT).show()
            }

            tvTestName3.text = "Астраномиядан русча тест 150 та"
            examCountTests3.text = "150"
            examBestResult3.text = "50%"

            tvTestName4.text = "Yangicha test astranomiya"
            examCountTests4.text = "56"
            examBestResult4.text = "70%"
        }
    }

    private fun openFragment(fragmentType: TYPE) {
        FRAGMENT_TYPE = fragmentType
        findNavController().navigate(R.id.action_homeFragment_to_theoreticalFragment)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}