package uz.astrostem.astrostem.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import uz.astrostem.astrostem.R
import uz.astrostem.astrostem.databinding.FragmentSavedBinding

class SavedFragment : Fragment(R.layout.fragment_saved) {

    private var _binding: FragmentSavedBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSavedBinding.bind(view)

//        binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}