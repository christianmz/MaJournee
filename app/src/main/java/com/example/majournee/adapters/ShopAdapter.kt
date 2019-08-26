package com.example.majournee.adapters

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.majournee.R
import com.example.majournee.activities.DetailProductActivity
import com.example.majournee.inflate
import com.example.majournee.models.Product
import kotlinx.android.synthetic.main.cardview_product.view.*

class ShopAdapter(private val data: List<Product>) :
    RecyclerView.Adapter<ShopAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(parent.inflate(R.layout.cardview_product))

    override fun onBindViewHolder(holder: Holder, position: Int) =
        holder.bind(data[position])

    override fun getItemCount(): Int = data.size

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(product: Product) =
            with(itemView) {
                tv_product_name.text = product.name
                tv_product_desc.text = product.description
                tv_product_price.text = "$ ${String.format("%.2f", product.price)}"

                setOnClickListener {

                    val intent = Intent(context, DetailProductActivity::class.java)
                    intent.putExtra("name", product.name)
                    intent.putExtra("desc", product.description)
                    intent.putExtra("price", product.price)

                    /*val pairImage: Pair<View, String> =
                        Pair.create(iv_product_detail, "trans_image")
                    val pairName: Pair<View, String> =
                        Pair.create(tv_product_name, "trans_name")
                    val pairDesc: Pair<View, String> =
                        Pair.create(tv_product_desc, "trans_desc")
                    val pairPrice: Pair<View, String> =
                        Pair.create(tv_product_price, "trans_price")

                    val options: ActivityOptionsCompat =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                            context as Activity,
                            pairImage,
                            pairName,
                            pairDesc,
                            pairPrice
                        )
                    context.startActivity(intent, options.toBundle())*/
                }
            }
    }
}
