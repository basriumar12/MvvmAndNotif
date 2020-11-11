package com.basri.mvvmandnotif.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.basri.mvvmandnotif.R
import com.basri.mvvmandnotif.constant.Constant
import com.basri.mvvmandnotif.model.get_lapor.Lapor
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.view_item_adapter.view.*
import java.util.*
import kotlin.collections.ArrayList

//package com.basri.mvvmandnotif.ui.adapter

//
class LaporAdapter(val list: ArrayList<Lapor>,
                    private val itemLaporListiner: OnLaporListiner)
    : RecyclerView.Adapter<LaporAdapter.ViewHolder>(), Filterable {

    var countryFilterList = ArrayList<Lapor>()

    init {
        countryFilterList = list
    }
    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {

        fun bindView(data: Lapor, laporListiner: OnLaporListiner) {
            itemView.tv_name.text = data.nama_pelapor
            itemView.tv_desk.text = data.deksripsi_lapor
            itemView.tv_tanggal.text = data.tanggal_lapor
            itemView.tv_lokasi.text = data.lokasi
            Glide.with(itemView.context)
                .load("${Constant.BASE_URL_IMAGE}${data.gambar}")
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .error(R.drawable.ic_launcher_background)
                .into(itemView.img_foto)

            itemView.setOnClickListener {
                laporListiner.onLaporClick(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_item_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = countryFilterList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val koridor = countryFilterList[position]
        holder.bindView(koridor, itemLaporListiner)
    }

    interface OnLaporListiner {
        fun onLaporClick(koridor: Lapor)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    countryFilterList = list
                } else {
                    val resultList = ArrayList<Lapor>()
                    for (row in list) {
                        if (row.nama_pelapor.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    countryFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = countryFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                countryFilterList = results?.values as ArrayList<Lapor>
                notifyDataSetChanged()
            }

        }
    }

}