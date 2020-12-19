package com.pack.memesapp.activity.main.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.pack.memesapp.R
import com.pack.memesapp.network.NetworkService
import com.pack.memesapp.network.models.MemeData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MemeCellsFragment : Fragment() {

    private val memeCells = ArrayList<MemeCell>()

    private lateinit var darkView: View
    private lateinit var spinner: ProgressBar
    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var errorFirstLine: TextView
    private lateinit var errorSecondLine: TextView
    private lateinit var dataAdapter: MemeCellsDataAdapter
    private lateinit var recyclerView : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_meme_cells, container, false)
    }

//

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        refreshLayout = view.findViewById(R.id.refreshLayout)
        refreshLayout.setProgressBackgroundColorSchemeResource(R.color.active_color)
        refreshLayout.setColorSchemeResources(R.color.background)

        refreshLayout.setOnRefreshListener {
            refreshScreen(view)
            refreshLayout.isRefreshing = false
        }

        refreshScreen(view);
    }

    private fun refreshScreen(view: View) {
        darkView = view.findViewById(R.id.darkScreen)
        darkView.visibility = View.VISIBLE

        spinner = view.findViewById(R.id.progressBarMemeCells)
        spinner.visibility = View.VISIBLE

        Handler().postDelayed({
            NetworkService.memeDataClient.getMemes()?.enqueue(object : Callback<List<MemeData>?> {
                override fun onResponse(
                    call: Call<List<MemeData>?>,
                    response: Response<List<MemeData>?>
                ) {

                    errorFirstLine = view.findViewById(R.id.errorFirstLine)
                    errorSecondLine = view.findViewById(R.id.errorSecondLine)



                    if (response.isSuccessful) {
                        errorFirstLine.visibility = View.GONE
                        errorSecondLine.visibility = View.GONE

                        memeCells.clear()
                        response.body()?.forEach { data ->
                            memeCells.add(MemeCell(data.photoUrl!!, data.description!!))
                        }

                    } else {
                        memeCells.clear()
                        errorFirstLine.visibility = View.VISIBLE
                        errorSecondLine.visibility = View.VISIBLE
                    }

                    dataAdapter = MemeCellsDataAdapter(view.context, memeCells)
                    recyclerView = view.findViewById(R.id.recyclerView)
                    recyclerView.adapter = dataAdapter

                    darkView.visibility = View.GONE
                    spinner.visibility = View.GONE
                }

                override fun onFailure(call: Call<List<MemeData>?>, t: Throwable) {}
            })
        }, this.resources.getInteger(R.integer.refresh_delay).toLong())
    }
}
