package ru.neonzoff.guidevologdaclient.screens.home

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
import ru.neonzoff.guidevologdaclient.LANGUAGE
import ru.neonzoff.guidevologdaclient.R
import ru.neonzoff.guidevologdaclient.RUSSIAN
import ru.neonzoff.guidevologdaclient.SHAREDPREFERENCES
import ru.neonzoff.guidevologdaclient.adapters.HomeAdapter
import ru.neonzoff.guidevologdaclient.adapters.HomeImageViewPagerAdapter
import ru.neonzoff.guidevologdaclient.databinding.FragmentHomeBinding
import ru.neonzoff.guidevologdaclient.views.APPViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeImageViewPagerAdapter: HomeImageViewPagerAdapter
    private lateinit var homeImageViewPager: ViewPager2

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
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
        swipeRefreshLayout = binding.swipeRefreshHome
        recyclerView = binding.rvHome
    }

    private fun initList(viewModel: APPViewModel) {
        layoutManager = LinearLayoutManager(this.context)
        homeAdapter = HomeAdapter()
/*
//        действия адаптера при скролле
        adapter.onLoadMore = {
            loadMore()
        }
*/

//        обработчик обновления
        swipeRefreshLayout.setOnRefreshListener {
//            reload(viewModel)
            swipeRefreshLayout.isRefreshing = false
        }

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = homeAdapter

//        my
        viewModel.getHomeEntity()
        viewModel.homeEntity.observe(viewLifecycleOwner) {
            it.body()?.let { homeEntityDto ->
                homeImageViewPagerAdapter.setList(homeEntityDto.images)
                homeAdapter.reloadHome(
                    3,
                    homeEntityDto.images,
                    when (SHAREDPREFERENCES.getString(LANGUAGE, ENGLISH)) {
                        RUSSIAN -> homeEntityDto.name
                        else -> homeEntityDto.nameEn
                    },
                    when (SHAREDPREFERENCES.getString(LANGUAGE, ENGLISH)) {
                        RUSSIAN -> homeEntityDto.description
                        else -> homeEntityDto.descriptionEn
                    }
                )
            }
        }
    }

    //    действия при обновлении
    private fun reload(viewModel: APPViewModel) {
        recyclerView.post {
//            adapter.reload(createData(0, 10))
            viewModel.homeEntity.observe(viewLifecycleOwner) {
                it.body()?.let { homeEntityDto ->
                    homeAdapter.reloadHome(
                        3,
                        homeEntityDto.images,
                        when (SHAREDPREFERENCES.getString(LANGUAGE, ENGLISH)) {
                            RUSSIAN -> homeEntityDto.name
                            else -> homeEntityDto.nameEn
                        },
                        when (SHAREDPREFERENCES.getString(LANGUAGE, ENGLISH)) {
                            RUSSIAN -> homeEntityDto.description
                            else -> homeEntityDto.descriptionEn
                        }
                    )
                }
            }
            homeImageViewPagerAdapter = HomeImageViewPagerAdapter()
            homeImageViewPager = view?.findViewById(R.id.vp_home)!!
            homeImageViewPager.adapter = homeImageViewPagerAdapter
        }
    }

    /*
    //     добавление записей при прокрутке
        private fun loadMore() {
            recyclerView.post {
                adapter.loadMore(createData(adapter.itemCount, 5))
            }
        }
    */
/*
    private fun createData(offset: Int, limit: Int): MutableList<String> {

        var list = mutableListOf<String>()

//        for (i in offset..(offset + limit)) {
//            //create random content
//            when ((0..2).random()) {
//                0 -> {
//                    list.add("Type A")
//                }
//                1 -> {
//                    list.add("Type B")
//                }
//                2 -> {
//                    list.add("Type C")
//                }
//
//            }
//        }
        list.add("Type A")
        list.add("Type B")
        list.add("Type C")
        return list
    }
*/
/*
    private fun initEntity(viewModel: APPViewModel) {
        homeImageViewPagerAdapter = HomeImageViewPagerAdapter()
        homeImageViewPager = binding.vpHome
        homeImageViewPager.adapter = homeImageViewPagerAdapter
        viewModel.getHomeEntity()
        viewModel.homeEntity.observe(viewLifecycleOwner) {
            it.body()?.let { homeEntity ->
                homeImageViewPagerAdapter.setList(homeEntity.images)
                binding.tvHomeName.text = when (SHAREDPREFERENCES.getString(LANGUAGE, ENGLISH)) {
                    RUSSIAN -> homeEntity.name
                    else -> homeEntity.nameEn
                }
                binding.tvHomeDescription.text =
                    when (SHAREDPREFERENCES.getString(LANGUAGE, ENGLISH)) {
                        RUSSIAN -> homeEntity.description
                        else -> homeEntity.descriptionEn
                    }
            }
        }
    }
    */
}