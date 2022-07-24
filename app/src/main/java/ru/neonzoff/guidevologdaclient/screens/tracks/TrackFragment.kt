package ru.neonzoff.guidevologdaclient.screens.tracks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ru.neonzoff.guidevologdaclient.ENGLISH
import ru.neonzoff.guidevologdaclient.LANGUAGE
import ru.neonzoff.guidevologdaclient.R
import ru.neonzoff.guidevologdaclient.RUSSIAN
import ru.neonzoff.guidevologdaclient.SHAREDPREFERENCES
import ru.neonzoff.guidevologdaclient.TRACK
import ru.neonzoff.guidevologdaclient.adapters.EntityInTrackAdapter
import ru.neonzoff.guidevologdaclient.adapters.TrackAdapter
import ru.neonzoff.guidevologdaclient.api.dto.TrackDto
import ru.neonzoff.guidevologdaclient.databinding.FragmentTrackBinding
import ru.neonzoff.guidevologdaclient.views.APPViewModel

class TrackFragment : Fragment() {

    private lateinit var binding: FragmentTrackBinding
    private lateinit var currentTrack: TrackDto
    private lateinit var adapter: EntityInTrackAdapter

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewEntites: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var trackAdapter: TrackAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrackBinding.inflate(layoutInflater, container, false)
        currentTrack = arguments?.getSerializable(TRACK) as TrackDto
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this)[APPViewModel::class.java]
//        initEntity(viewModel)
        findView()
        initList(viewModel)
        reload(viewModel)
    }

    private fun findView() {
        swipeRefreshLayout = binding.swipeRefreshTrack
        recyclerView = binding.rvTrack
    }

    private fun initList(viewModel: APPViewModel) {
        layoutManager = LinearLayoutManager(this.context)
        trackAdapter = TrackAdapter()
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = trackAdapter
/*
//        действия адаптера при скролле
        adapter.onLoadMore = {
            loadMore()
        }
*/

//        обработчик обновления
        swipeRefreshLayout.setOnRefreshListener {
            reload(viewModel)
            swipeRefreshLayout.isRefreshing = false
        }

//        my
        viewModel.getTrack(currentTrack.id)
        viewModel.track.observe(viewLifecycleOwner) { list ->
            list.body()?.let {
                adapter.setList(it.entities)
                trackAdapter.reloadTrack(
                    3,
                    when (SHAREDPREFERENCES.getString(LANGUAGE, ENGLISH)) {
                        RUSSIAN -> it.name
                        else -> it.nameEn
                    },
                    when (SHAREDPREFERENCES.getString(LANGUAGE, ENGLISH)) {
                        RUSSIAN -> it.description
                        else -> it.descriptionEn
                    }
                )
            }
        }
    }

    //    действия при обновлении
    private fun reload(viewModel: APPViewModel) {
        recyclerView.post {
//            adapter.reload(createData(0, 10))
            adapter = EntityInTrackAdapter()
            recyclerViewEntites = view?.findViewById(R.id.rv_entities)!!
            recyclerViewEntites.adapter = adapter

            viewModel.getTrack(currentTrack.id)
            viewModel.track.observe(viewLifecycleOwner) { list ->
                list.body()?.let {
                    adapter.setList(it.entities)
                    trackAdapter.reloadTrack(
                        3,
                        when (SHAREDPREFERENCES.getString(LANGUAGE, ENGLISH)) {
                            RUSSIAN -> it.name
                            else -> it.nameEn
                        },
                        when (SHAREDPREFERENCES.getString(LANGUAGE, ENGLISH)) {
                            RUSSIAN -> it.description
                            else -> it.descriptionEn
                        }
                    )
                }
            }
        }
    }
}
