package com.laughat.funnymemes.dashboard


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.laughat.funnymemes.BR
import com.laughat.funnymemes.R
import com.laughat.funnymemes.base.models.MemesItem
import com.laughat.funnymemes.databinding.MemeItemBinding


class MemesListAdapter(var memesItemList: List<MemesItem>?):
    RecyclerView.Adapter<MemesListAdapter.MemeAdapterViewHolder>() {
    lateinit var mContext: Context
    var onImageViewClick: ((MemesItem) -> Unit)? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MemesListAdapter.MemeAdapterViewHolder {
        mContext = parent.context
        return MemeAdapterViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.meme_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MemesListAdapter.MemeAdapterViewHolder, position: Int) {
        var memeItem = memesItemList?.get(position)

        holder.getBinding()?.setVariable(BR.model, memeItem)
        val inBinding:MemeItemBinding =
            holder.getBinding() as MemeItemBinding

        // Loading icon on image view using Glide
        Glide.with(mContext).asBitmap().load(memeItem?.url).into(inBinding.ivMeme)
        inBinding.ivMeme.setOnClickListener { onImageViewClick?.invoke(memeItem!!) }


    }

    override fun getItemCount(): Int {
        return memesItemList?.size!!
    }

    inner class MemeAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var binding: ViewDataBinding? = null

        init {
            binding = DataBindingUtil.bind(itemView)
        }

        fun getBinding(): ViewDataBinding? {
            return binding
        }
    }

    /*
   *
   * click listener for item selected
   * */

    fun setOnImageViewClickListener(onImageViewClick: (MemesItem) -> Unit) {
        this.onImageViewClick = onImageViewClick
    }

}

