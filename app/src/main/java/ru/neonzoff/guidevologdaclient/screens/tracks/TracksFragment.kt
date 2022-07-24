package ru.neonzoff.guidevologdaclient.screens.tracks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import ru.neonzoff.guidevologdaclient.APP
import ru.neonzoff.guidevologdaclient.R
import ru.neonzoff.guidevologdaclient.TRACK
import ru.neonzoff.guidevologdaclient.adapters.TracksAdapter
import ru.neonzoff.guidevologdaclient.api.dto.TrackDto
import ru.neonzoff.guidevologdaclient.databinding.FragmentTracksBinding
import ru.neonzoff.guidevologdaclient.views.APPViewModel

class TracksFragment : Fragment() {

    lateinit var binding: FragmentTracksBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: TracksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTracksBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun initTracks(viewModel: APPViewModel) {
        recyclerView = binding.rvTracks
        adapter = TracksAdapter()
        recyclerView.adapter = adapter
        viewModel.getTracks()
        viewModel.tracks.observe(viewLifecycleOwner) { list ->
            list.body()?.let {
                adapter.setList(it)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this)[APPViewModel::class.java]
        initTracks(viewModel)
    }

    companion object {
        fun clickTrack(trackDto: TrackDto) {
            val bundle = Bundle()
            bundle.putSerializable(TRACK, trackDto)
            APP.navController.navigate(R.id.action_tracks_to_trackFragment, bundle)
        }
    }
}