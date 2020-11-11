package com.basri.mvvmandnotif.ui.adapter//package com.basri.mvvmandnotif.ui.adapter
//
//import android.graphics.drawable.Drawable
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.bumptech.glide.load.DataSource
//import com.bumptech.glide.load.engine.DiskCacheStrategy
//import com.bumptech.glide.load.engine.GlideException
//import com.bumptech.glide.request.RequestListener
//import com.bumptech.glide.request.target.Target
//import id.sch.smktelkomjkt.ecycle.R
//import id.sch.smktelkomjkt.ecycle.ui.master.donasi.model.ResponseDataDonasi
//import kotlinx.android.synthetic.main.view_item_histori_donasi.view.*
//
//class DonasiAdapter(val list: List<ResponseDataDonasi>,
//                    private val itemKoridorListener: OnKoridorListener)
//    : RecyclerView.Adapter<DonasiAdapter.ViewHolder>() {
//
//    class ViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView) {
//
//        fun bindView(data: ResponseDataDonasi, koridorListener: OnKoridorListener) {
//            itemView.tv_name.text = data.nama_donatur
//            itemView.tv_address.text = data.alamat
//            itemView.tv_barang.text = data.barang.model_barang
//            itemView.tv_status.text = data.status
//            itemView.tv_kerusakan.text = data.kondisi
//            Glide.with(itemView.context)
//                .load("${data.user.avatar}")
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .skipMemoryCache(true)
//                .placeholder(R.drawable.icon_profile_account)
//                .error(R.drawable.icon_profile_account)
//                .listener(object : RequestListener<Drawable> {
//                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
//                        return false
//                    }
//
//                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
//
//                        return false
//                    }
//
//                })
//                .into(itemView.img_profile__history_repair)
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.view_item_history_repair, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun getItemCount(): Int = list.size
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val koridor = list[position]
//        holder.bindView(koridor, itemKoridorListener)
//    }
//
//    interface OnKoridorListener {
//        fun onKoridorClick(koridor: ResponseDataDonasi)
//    }
//
//}