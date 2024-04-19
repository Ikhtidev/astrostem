package uz.sayfullayeva.astrostem.ui.fragments

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import uz.sayfullayeva.astrostem.R
import uz.sayfullayeva.astrostem.database.ThemeDatabase
import uz.sayfullayeva.astrostem.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val themeDatabase: ThemeDatabase by lazy {
        ThemeDatabase.getInstance()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)

        initViews()
        setClickListeners()
    }

    private fun initViews() {
        val test1 = themeDatabase.testDao().getAllTests()[0]
        binding.apply {
            tvTestResult1.text =
                getString(R.string.result_best_text, test1.name, test1.testResult.toString())
            progressBar1.progress = (test1.testResult) * 100 / 30
        }
        val test2 = themeDatabase.testDao().getAllTests()[1]
        binding.apply {
            tvTestResult2.text =
                getString(R.string.result_best_text, test2.name, test2.testResult.toString())
            progressBar2.progress = (test2.testResult) * 100 / 30
        }
    }

    private fun setClickListeners() {
        binding.apply {
            ownerPhone1.setOnClickListener {
                checkPermissions("+998975920409")
            }
            ownerPhone2.setOnClickListener {
                checkPermissions("+998336601290")
            }
            ownerPhone3.setOnClickListener {
                checkPermissions("+998948090509")
            }
            ownerEmail1.setOnClickListener {
                sendEmail("gulhayoixtiyorqizi0409@mail.ru")
            }
            ownerEmail2.setOnClickListener {
                sendEmail("ugiloykamardinovna@gmail.com")
            }
            ownerEmail3.setOnClickListener {
                sendEmail("azizamurodullayevna0509@gmail.com")
            }
            ownerTelegram1.setOnClickListener {
                openTelegram("DotsGulhayoSayfullayeva")
            }
            ownerTelegram2.setOnClickListener {
                openTelegram("+998336601290")
            }
            ownerTelegram3.setOnClickListener {
                openTelegram("azizamurodullayevna")
            }
        }
    }

    private fun openTelegram(telegramLink: String) {
        val intent = Intent(ACTION_VIEW, Uri.parse("https://t.me/$telegramLink"))
        startActivity(intent)
    }

    private fun sendEmail(email: String) {
        val intent = Intent(ACTION_VIEW, Uri.parse("mailto:$email"))
        startActivity(intent)
    }

    private fun checkPermissions(phoneNumber: String) {
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                android.Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.CALL_PHONE),
                101
            )
        } else {
            val callIntent = Intent(ACTION_VIEW, Uri.parse("tel:$phoneNumber"))
            startActivity(callIntent)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}