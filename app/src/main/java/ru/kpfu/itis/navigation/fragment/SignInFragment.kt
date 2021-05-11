package ru.kpfu.itis.navigation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_sign_in.*
import kotlinx.android.synthetic.main.fragment_sign_in.email_et
import kotlinx.android.synthetic.main.fragment_sign_in.password_et
import ru.kpfu.itis.navigation.R


class SignInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sign_in_btn.setOnClickListener {
            if (password_et.text.toString() == "password" && email_et.text.toString() == "login") {
                findNavController().navigate(R.id.action_signInFragment_to_mainActivity, bundleArgs(password_et.text.toString(), email_et.text.toString()))
            } else {
                Snackbar.make(
                        requireActivity().findViewById(android.R.id.content),
                        "no correct password and email",
                        Snackbar.LENGTH_LONG
                ).show()
            }

        }
        sign_up_tv.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }

    companion object {
        fun bundleArgs(password: String, email: String) = bundleOf("password" to password, "email" to email)
    }

}