package tm.mr.compactplayerviewsample

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_item_layout.view.*

class VideosRecyclerView : RecyclerView {

    private val rvAdapter = VideosAdapter()
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        adapter = rvAdapter
        layoutManager = LinearLayoutManager(context)
    }

    fun setItems(items: List<String>) {
        rvAdapter.items = items
    }
}

class VideosAdapter: RecyclerView.Adapter<ViewHolder>() {

    var items: List<String> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder.create(parent)
    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position])

}

class ViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_item_layout, parent, false)) {

    fun bind(url: String) {
        itemView.player.play(url)
    }

    companion object {
        fun create(parent: ViewGroup) =
            ViewHolder(parent)
    }

}