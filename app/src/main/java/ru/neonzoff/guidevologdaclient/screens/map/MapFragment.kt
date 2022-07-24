package ru.neonzoff.guidevologdaclient.screens.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import ru.neonzoff.guidevologdaclient.databinding.FragmentMapBinding
import ru.neonzoff.guidevologdaclient.mapView
import ru.neonzoff.guidevologdaclient.positions

class MapFragment : Fragment() {

    private lateinit var binding: FragmentMapBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(layoutInflater)
        MapKitFactory.initialize(container?.context)
        mapView = binding.mapView
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        MapKitFactory.getInstance().onStop()
        mapView.onStop()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (positions != null) {
            mapView.map.move(
                CameraPosition(
                    Point(positions!![1].toDouble(), positions!![0].toDouble()),
                    18.0f,
                    0.0f,
                    0.0f
                )
            )
        } else {
            mapView.map.move(
                CameraPosition(Point(59.220501, 39.891523), 11.0f, 0.0f, 0.0f),
                Animation(Animation.Type.SMOOTH, 0F),
                null
            )
        }
    }

}