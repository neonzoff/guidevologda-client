package ru.neonzoff.guidevologdaclient.screens.entities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import ru.neonzoff.guidevologdaclient.APP
import ru.neonzoff.guidevologdaclient.R
import ru.neonzoff.guidevologdaclient.TYPE_ENTITY
import ru.neonzoff.guidevologdaclient.adapters.TypeEntityAdapter
import ru.neonzoff.guidevologdaclient.api.dto.TypeEntityDto
import ru.neonzoff.guidevologdaclient.databinding.FragmentTypeentityBinding
import ru.neonzoff.guidevologdaclient.views.APPViewModel

class TypeEntityFragment : Fragment() {

    lateinit var binding: FragmentTypeentityBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: TypeEntityAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel = ViewModelProvider(this)[APPViewModel::class.java]
        binding = FragmentTypeentityBinding.inflate(layoutInflater, container, false)
        initTypeEntities(viewModel)
        return binding.root
    }

    private fun initTypeEntities(viewModel: APPViewModel) {
        recyclerView = binding.rvTypeEntity
        adapter = TypeEntityAdapter()
        recyclerView.adapter = adapter
        viewModel.getTypesEntity()
        viewModel.typesEntity.observe(viewLifecycleOwner) { list ->
            list.body()?.let { adapter.setList(it) }
        }
    }

    companion object {
        fun clickTypeEntity(typeEntityDto: TypeEntityDto) {
            val bundle = Bundle()
            bundle.putSerializable(TYPE_ENTITY, typeEntityDto)
            APP.navController.navigate(R.id.action_typeentities_to_entityFragment2, bundle)
        }
    }


}