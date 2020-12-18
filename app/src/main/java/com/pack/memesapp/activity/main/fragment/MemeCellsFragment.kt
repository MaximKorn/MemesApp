package com.pack.memesapp.activity.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.pack.memesapp.R
import com.pack.memesapp.network.NetworkService
import com.pack.memesapp.network.models.MemeData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MemeCellsFragment : Fragment() {

    private lateinit var errorFirstLine: TextView
    private lateinit var errorSecondLine: TextView
    private val memeCells = ArrayList<MemeCell>()
    private lateinit var dataAdapter: MemeCellsDataAdapter
    private lateinit var recyclerView : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_meme_cells, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val darkView = view.findViewById<View>(R.id.darkScreen)
        darkView.visibility = View.VISIBLE

        val spinner = view.findViewById<ProgressBar>(R.id.progressBarMemeCells)
        spinner.visibility = View.VISIBLE

        NetworkService.memeDataClient.getMemes()?.enqueue(object : Callback<List<MemeData>?> {
            override fun onResponse(call: Call<List<MemeData>?>, response: Response<List<MemeData>?>) {

                errorFirstLine = view.findViewById(R.id.errorFirstLine)
                errorSecondLine = view.findViewById(R.id.errorSecondLine)

                if (response.isSuccessful) {
                    errorFirstLine.visibility = View.GONE
                    errorSecondLine.visibility = View.GONE
                    val requestResult = response.body()

                    requestResult!!.forEach { data ->
                        memeCells.add(MemeCell(data.photoUrl, data.description))
                    }

                    dataAdapter = MemeCellsDataAdapter(view.context, memeCells)
                    recyclerView = view.findViewById(R.id.recyclerView)
                    recyclerView.adapter = dataAdapter

                } else {
                    errorFirstLine.visibility = View.VISIBLE
                    errorSecondLine.visibility = View.VISIBLE
                }

                darkView.visibility = View.GONE
                spinner.visibility = View.GONE
            }

            override fun onFailure(call: Call<List<MemeData>?>, t: Throwable) {}
        })
    }
}
