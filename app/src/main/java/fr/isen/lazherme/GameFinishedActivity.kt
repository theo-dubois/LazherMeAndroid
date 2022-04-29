package fr.isen.lazherme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.*
import fr.isen.lazherme.databinding.ActivityGameFinishedBinding

private lateinit var binding : ActivityGameFinishedBinding
private lateinit var userKey : String
private lateinit var userEmail : String
private lateinit var code : String
private lateinit var scoreBleu : String
private lateinit var scoreRouge : String
private lateinit var myRef: DatabaseReference
private lateinit var database : FirebaseDatabase
class GameFinishedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        database = FirebaseDatabase.getInstance()
        myRef = database.reference
        binding = ActivityGameFinishedBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getGameSpecs()
        userKey = intent.getStringExtra("userKey").toString()
        userEmail = intent.getStringExtra("userEmail").toString()
        code = intent.getStringExtra("code").toString()
        binding.homeButton.setOnClickListener{
            val intent = Intent(this, GameFinishedActivity::class.java)
            intent.putExtra("code",code)
            startActivity(intent)
        }
    }
    private fun getGameSpecs(){
        val ref = myRef.child("Games").child(code).child("gameSpecs")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                scoreBleu = snapshot.child("scoreBlue").value.toString()
                scoreRouge = snapshot.child("scoreRed").value.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}