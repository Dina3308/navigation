package ru.kpfu.itis.navigation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_sign_up.*
import ru.kpfu.itis.navigation.R

class SignUpFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sign_up_btn.setOnClickListener {
            if (password_et.text?.isNotEmpty() == true && email_et.text?.isNotEmpty() == true) {
                findNavController().navigate(R.id.action_signUpFragment_to_mainActivity, bundleArgs(password_et.text.toString(), email_et.text.toString()))
            } else {
                Snackbar.make(
                        requireActivity().findViewById(android.R.id.content),
                        "enter all fields",
                        Snackbar.LENGTH_LONG
                ).show()
            }
        }

    }

    companion object {

        fun bundleArgs(password: String, email: String) = bundleOf("password" to password, "email" to email)
    }
}