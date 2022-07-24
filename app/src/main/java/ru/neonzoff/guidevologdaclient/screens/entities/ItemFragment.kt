package ru.neonzoff.guidevologdaclient.screens.entities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.widget.ViewPager2
import ru.neonzoff.guidevologdaclient.ENGLISH
import ru.neonzoff.guidevologdaclient.ENTITY
import ru.neonzoff.guidevologdaclient.LANGUAGE
import ru.neonzoff.guidevologdaclient.R
import ru.neonzoff.guidevologdaclient.RUSSIAN
import ru.neonzoff.guidevologdaclient.SHAREDPREFERENCES
import ru.neonzoff.guidevologdaclient.adapters.ContactAdapter
import ru.neonzoff.guidevologdaclient.adapters.ItemAdapter
import ru.neonzoff.guidevologdaclient.adapters.ItemImageViewPagerAdapter
import ru.neonzoff.guidevologdaclient.api.dto.BaseEntityDto
import ru.neonzoff.guidevologdaclient.databinding.FragmentItemBinding
import ru.neonzoff.guidevologdaclient.views.APPViewModel

class ItemFragment : Fragment() {

    private lateinit var binding: FragmentItemBinding
    private lateinit var currentEntity: BaseEntityDto
    private lateinit var contactRecyclerView: RecyclerView
    private lateinit var contactAdapter: ContactAdapter
    private lateinit var itemImageViewPagerAdapter: ItemImageViewPagerAdapter
    private lateinit var imageViewPager: ViewPager2

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var entityAdapter: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemBinding.inflate(layoutInflater)
        currentEntity = arguments?.getSerializable(ENTITY) as BaseEntityDto
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
        swipeRefreshLayout = binding.swipeRefreshEntity
        recyclerView = binding.rvEntity
    }

    private fun initList(viewModel: APPViewModel) {
        layoutManager = LinearLayoutManager(this.context)
        entityAdapter = ItemAdapter()
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = entityAdapter
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
        viewModel.getEntity(currentEntity.id)
        viewModel.getContactTypes()
        viewModel.getStreets()
//        viewModel.contactTypes.observe(viewLifecycleOwner) { contactTypes ->
//            contactTypes.body()?.let {
//                contactAdapter.setListContactType(it)
//            }
//        }
        viewModel.entity.observe(viewLifecycleOwner) { entity ->
            entity.body()?.let {
//                itemImageViewPagerAdapter.setListImages(it.images)
//                contactAdapter.setListContact(it.contacts)
                entityAdapter.reloadEntity(
                    4,
                    it.images,
                    when (SHAREDPREFERENCES.getString(LANGUAGE, ENGLISH)) {
                        RUSSIAN -> it.name
                        else -> it.nameEn
                    },
                    when (SHAREDPREFERENCES.getString(LANGUAGE, ENGLISH)) {
                        RUSSIAN -> it.description
                        else -> it.descriptionEn
                    },
                    when (SHAREDPREFERENCES.getString(LANGUAGE, ENGLISH)) {
//                        вынужденная мера: из-за малой скорости сервера, из-за запроса улиц - они не успевают загрузиться и остальные элементы пустыми отображаются
                        RUSSIAN -> "${it.street} ${it.houseNumber}"
                        else -> "${it.street} ${it.houseNumber}"
                    },
                    when (SHAREDPREFERENCES.getString(LANGUAGE, ENGLISH)) {
                        RUSSIAN -> it.workSchedule
                        else -> it.workScheduleEn
                    },
                    it.pos
                )
            }
        }
    }

    //    действия при обновлении
    private fun reload(viewModel: APPViewModel) {
        recyclerView.post {
//            adapter.reload(createData(0, 10))
            contactAdapter = ContactAdapter()
            contactRecyclerView = view?.findViewById(R.id.rv_contacts)!!
            contactRecyclerView.adapter = contactAdapter
            itemImageViewPagerAdapter = ItemImageViewPagerAdapter()
            imageViewPager = view?.findViewById(R.id.vp_item)!!
            imageViewPager.adapter = itemImageViewPagerAdapter

            viewModel.getEntity(currentEntity.id)
            viewModel.getContactTypes()

            viewModel.contactTypes.observe(viewLifecycleOwner) { contactTypes ->
                contactTypes.body()?.let {
                    contactAdapter.setListContactType(it)
                }
            }

            viewModel.entity.observe(viewLifecycleOwner) { entity ->
                entity.body()?.let {
                    itemImageViewPagerAdapter.setListImages(it.images)
                    contactAdapter.setListContact(it.contacts)
                    entityAdapter.reloadEntity(
                        4,
                        it.images,
                        when (SHAREDPREFERENCES.getString(LANGUAGE, ENGLISH)) {
                            RUSSIAN -> it.name
                            else -> it.nameEn
                        },
                        when (SHAREDPREFERENCES.getString(LANGUAGE, ENGLISH)) {
                            RUSSIAN -> it.description
                            else -> it.descriptionEn
                        },
                        when (SHAREDPREFERENCES.getString(LANGUAGE, ENGLISH)) {
//                        вынужденная мера: из-за малой скорости сервера, из-за запроса улиц - они не успевают загрузиться и остальные элементы пустыми отображаются
                            RUSSIAN -> "${it.street} ${it.houseNumber}"
                            else -> "${it.street} ${it.houseNumber}"
                        },
                        when (SHAREDPREFERENCES.getString(LANGUAGE, ENGLISH)) {
                            RUSSIAN -> it.workSchedule
                            else -> it.workScheduleEn
                        },
                        it.pos
                    )
                }
            }
        }
    }
}
