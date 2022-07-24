package ru.neonzoff.guidevologdaclient.screens.entities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import ru.neonzoff.guidevologdaclient.APP
import ru.neonzoff.guidevologdaclient.ENTITY
import ru.neonzoff.guidevologdaclient.R
import ru.neonzoff.guidevologdaclient.TYPE_ENTITY
import ru.neonzoff.guidevologdaclient.adapters.EntityAdapter
import ru.neonzoff.guidevologdaclient.api.dto.BaseEntityDto
import ru.neonzoff.guidevologdaclient.api.dto.TypeEntityDto
import ru.neonzoff.guidevologdaclient.databinding.FragmentEntityBinding
import ru.neonzoff.guidevologdaclient.views.APPViewModel

class EntityFragment : Fragment() {

    lateinit var binding: FragmentEntityBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: EntityAdapter
    lateinit var currentTypeEntity: TypeEntityDto

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEntityBinding.inflate(layoutInflater, container, false)
        currentTypeEntity = arguments?.getSerializable(TYPE_ENTITY) as TypeEntityDto
        return binding.root
    }

    private fun initEntities(viewModel: APPViewModel) {
        recyclerView = binding.rvEntity
        adapter = EntityAdapter()
        recyclerView.adapter = adapter
        viewModel.getEntities(currentTypeEntity.id)
        viewModel.entities.observe(viewLifecycleOwner) { list ->
            list.body()?.let {
                adapter.setList(it)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this)[APPViewModel::class.java]
        initEntities(viewModel)
    }

    companion object {
        fun clickEntity(baseEntityDto: BaseEntityDto) {
            val bundle = Bundle()
            bundle.putSerializable(ENTITY, baseEntityDto)
            APP.navController.navigate(R.id.action_entityFragment_to_itemFragment, bundle)
        }
    }

}