package com.example.majournee.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.majournee.R
import com.example.majournee.adapters.ShopAdapter
import com.example.majournee.models.Product
import kotlinx.android.synthetic.main.fragment_shop.view.*

class ShopFragment : Fragment() {

    private lateinit var mRecycler: RecyclerView
    private lateinit var mAdapter: ShopAdapter
    private val mLayoutManager by lazy { GridLayoutManager(context, 2) }
    private val list by lazy { setList() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_shop, container, false)
        activity?.setTitle(R.string.shop)
        mRecycler = rootView.rv_shop as RecyclerView
        setRecyclerView()
        return rootView
    }

    private fun setRecyclerView() = with(mRecycler){
        setHasFixedSize(true)
        itemAnimator = DefaultItemAnimator()
        layoutManager = mLayoutManager
        mAdapter = ShopAdapter(list)
        adapter = mAdapter
    }

    private fun setList() : List<Product>{
        return (0..20).map {
            Product("name $it", "description $it", 200.00 + it)
        }
    }
}
