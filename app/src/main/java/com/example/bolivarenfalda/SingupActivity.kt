package com.example.bolivarenfalda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bolivarenfalda.databinding.ActivitySingupBinding
import com.google.firebase.auth.FirebaseAuth

class SingupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySingupBinding
    private lateinit var firebaseAuth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySingupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth= FirebaseAuth.getInstance()

        binding.signupButton.setOnClickListener{

            val email=binding.signupEmail.text.toString()
            val password=binding.signupPassword.text.toString()
            val confirmPassword=binding.signupConfirm.text.toString()


            if(email.isNotEmpty() &&password.isNotEmpty() &&confirmPassword.isNotEmpty()){
                if (password == confirmPassword){
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                        if (it.isSuccessful){
                            val intent= Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                        }

                    }
                }else{

                    Toast.makeText(this,"la contraseña no coincide",Toast.LENGTH_SHORT).show()

                }


            }else{
                Toast.makeText(this,"los campos no deben estar vacios",Toast.LENGTH_SHORT).show()
            }

        }
        binding.loginRedirectText.setOnClickListener {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)


        }



    }
}