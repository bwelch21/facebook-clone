package com.brandtwelch.pictureme.userprofile

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.brandtwelch.pictureme.R
import com.brandtwelch.pictureme.db.entities.Post

class FeedAdapter(
    private val feed: ArrayList<Post>
) : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    class FeedViewHolder(
        private val cardView: CardView
    ) : RecyclerView.ViewHolder(cardView) {

        private var authorTextView: TextView = cardView.findViewById(R.id.author_name)
        private var postBodyTextView: TextView = cardView.findViewById(R.id.post_body)

        fun setPostDetails(post: Post) {
            authorTextView.text =  cardView.context.getText(R.string.brandt_welch)
            postBodyTextView.text = post.postBody
        }
    }

    override fun getItemCount() = feed.size

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.setPostDetails(feed[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.text_timeline_item, parent, false)
        return FeedViewHolder(cardView as CardView)
    }
}