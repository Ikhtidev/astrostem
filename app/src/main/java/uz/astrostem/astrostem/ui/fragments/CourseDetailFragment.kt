package uz.astrostem.astrostem.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import uz.astrostem.astrostem.R
import uz.astrostem.astrostem.adapters.ThemeAdapter
import uz.astrostem.astrostem.databinding.FragmentCourseDetailBinding
import uz.astrostem.astrostem.ui.PdfViewerActivity
import uz.astrostem.astrostem.utils.Constants.Companion.FILE_NUMBER
import uz.astrostem.astrostem.utils.Constants.Companion.FRAGMENT_TYPE
import uz.astrostem.astrostem.utils.Constants.Companion.THEME_LIST
import uz.astrostem.astrostem.utils.TYPE

class CourseDetailFragment : Fragment(R.layout.fragment_course_detail) {

    private var _binding: FragmentCourseDetailBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCourseDetailBinding.bind(view)

        initViews()
    }

    private fun initViews() {
        val filteredList = THEME_LIST.filter { it.type == FRAGMENT_TYPE }
        val adapter = ThemeAdapter(filteredList)
        binding.apply {
            tvCourseName.text = FRAGMENT_TYPE.typeName
            setAboutCourseText()
            rvTheme.adapter = adapter
        }

        adapter.setListener(object : ThemeAdapter.ThemeClickListener {
            override fun onThemeClick(position: Int) {
                FILE_NUMBER = position
                startActivity(Intent(activity, PdfViewerActivity::class.java))
            }
        })
    }

    private fun setAboutCourseText() {
        binding.tvAboutCourse.text=when(FRAGMENT_TYPE){
            TYPE.T ->{
                getString(R.string.about_theoretical)
            }
            TYPE.P ->{
                getString(R.string.about_practical)
            }
            TYPE.L ->{
                getString(R.string.about_laboratory)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}