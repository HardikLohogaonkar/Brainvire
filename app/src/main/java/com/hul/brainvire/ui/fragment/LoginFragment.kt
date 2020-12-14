package com.hul.brainvire.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hul.brainvire.R
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_login, container, false)

        view.btnLogin.setOnClickListener(this)

        return view
    }

    override fun onClick(v: View?) {

        when (v!!.id) {

            R.id.btnLogin -> {

                val username = etUsername.text.toString()
                val password = etPassword.text.toString()

                if (username == "test@android.com" && password == "123456") {

                    findNavController().navigate(R.id.action_login_to_list_fragment)
                } else {

                    if (username != "test@android.com") {
                        tipUsername.error = "Please enter valid username"
                    } else if (password != "123456")
                        tipPassword.error = "Please enter valid password"
                }
            }
        }
    }
}