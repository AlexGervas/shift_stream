package shift.cft.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class WeatherListAdapter : RecyclerView.Adapter<WeatherListAdapter.ViewHolder>() {

    private val weatherList: MutableList<Info> = LinkedList()
    private var weatherListener: WeatherListener? = null

    fun setWeatherList(newWeather: List<Info>) {
        weatherList.clear()
        weatherList.addAll(newWeather)

        notifyDataSetChanged()
    }

    fun setListener(listener: WeatherListener) {
        weatherListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        return ViewHolder(view, weatherListener)
    }

    override fun getItemCount(): Int {
        return weatherList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(model = weatherList[position])
    }

    class ViewHolder(itemView: View, private val weatherListener: WeatherListener?) : RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView.findViewById(R.id.itemTitle)

        fun bind(model: Info) {
            title.text = model.title
            itemView.setOnClickListener {

                weatherListener?.onClickNote(model)
            }
        }
    }

    interface WeatherListener {
        fun onClickNote(model: Info)
    }
}