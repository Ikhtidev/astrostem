package uz.sayfullayeva.astrostem.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import uz.sayfullayeva.astrostem.R
import uz.sayfullayeva.astrostem.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)

//        binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}