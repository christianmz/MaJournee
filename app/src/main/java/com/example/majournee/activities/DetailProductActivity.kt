package com.example.majournee.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.majournee.R
import kotlinx.android.synthetic.main.cardview_detail_product.*

class DetailProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_product)

        intent?.extras.let {
            tv_product_name_detail.text = it?.getString("name")
            tv_product_desc_detail.text = it?.getString("desc")
            tv_product_price_detail.text = "$ ${String.format("%.2f", it?.getDouble("price"))}"
        }
    }
}
