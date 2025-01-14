    package com.mhji.a2021_hackathon_android

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.mhji.a2021_hackathon_android.Network.RetrofitService
import com.mhji.a2021_hackathon_android.Network.brokerSignUp
import com.mhji.a2021_hackathon_android.data.BrokerSignUpBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class BrokerJoinActivity : AppCompatActivity() {

    lateinit var client : Retrofit
    lateinit var service : RetrofitService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broker_join)

        var city : String = ""
        var county : String = ""

        val citySpinner = findViewById<Spinner>(R.id.join_city)
        val countySpinner = findViewById<Spinner>(R.id.join_county)
        val arrayAdapter : ArrayAdapter<String>

        val BSignUpId : EditText = findViewById(R.id.Bjoin_id_txt)
        val BSignUpPw : EditText = findViewById(R.id.BJoin_pw_txt)
        val BSignUpPhone : EditText = findViewById(R.id.BJoin_phone_txt)
        val BSignUpname : EditText = findViewById(R.id.BJoin_name_txt)

        val BsignUpBtn : Button = findViewById(R.id.CJoin_Btn)

        BsignUpBtn.setOnClickListener {
            service.brokerSignUpRequest(BrokerSignUpBody(BSignUpId.text.toString(), BSignUpPw.text.toString(), BSignUpPhone.text.toString(), BSignUpname.text.toString(), city, county)).enqueue(object :
                Callback<brokerSignUp> {
                override fun onResponse(call: Call<brokerSignUp>, response: Response<brokerSignUp>) {
//                    Log.d("LOG", "${response.code()}")
                    val intent : Intent = Intent(this@BrokerJoinActivity, BrokerLoginActivity::class.java)
                    startActivity(intent)
                }

                override fun onFailure(call: Call<brokerSignUp>, t: Throwable) {
                    Toast.makeText(this@BrokerJoinActivity, "연결 실패.....", Toast.LENGTH_SHORT).show()
                }
            })
        }

        arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.spinner_region) as Array<String>
        )
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }
}