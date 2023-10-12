package com.example.ilatest

import adapters.ItemAdapter
import adapters.SliderAdapter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import models.ListItem
import models.SliderItem


class MainActivity : AppCompatActivity() {

    private lateinit var cardsViewPager2: ViewPager2
    private lateinit var dotsTabLayout: TabLayout
    private lateinit var searchView: SearchView
    private lateinit var rvDataList: RecyclerView
    private lateinit var rvItemAdapter: ItemAdapter
    private var adapterData: ArrayList<ListItem> = arrayListOf()
    private var currentListIndex: Int = 0
    private var finalList: ArrayList<ArrayList<ListItem>> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cardsViewPager2 = findViewById(R.id.viewPagerImageSlider)
        dotsTabLayout = findViewById(R.id.tabLayout)
        searchView = findViewById(R.id.searchView)
        rvDataList = findViewById(R.id.recyclerView)

        // Prepare a list of images from resources (drawable)
        val sliderItems = listOf(
            SliderItem(R.drawable.image1),
            SliderItem(R.drawable.image2),
            SliderItem(R.drawable.image3),
        )

        //Fill Array with Dummy Data
        initializeFinalList()


        // Initialize RecyclerView and Adapter
        adapterData.addAll(finalList[0])
        rvDataList.layoutManager = LinearLayoutManager(this)
        rvItemAdapter = ItemAdapter(adapterData)
        rvDataList.adapter = rvItemAdapter


        // Set up the top Cards View Pager

        cardsViewPager2.adapter = SliderAdapter(sliderItems, cardsViewPager2)
        cardsViewPager2.clipToPadding = false
        cardsViewPager2.clipChildren = false
        cardsViewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        // Setting Synchronizer for the view Pager Indicator
        TabLayoutMediator(dotsTabLayout, cardsViewPager2) { _, _ -> }.attach()

        cardsViewPager2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentListIndex = position
                updateAdapter(finalList[position])
                Log.e("position", position.toString())

            }
        })

        // Search view filtration
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                val filteredData = finalList[currentListIndex].filter { item ->
                    item.getLabel().contains(newText)
                }
                updateAdapter(filteredData)
                return true
            }
        })

    }

    fun updateAdapter(newData: Collection<ListItem>) {
        adapterData.clear()
        adapterData.addAll(newData)
        rvItemAdapter.notifyDataSetChanged()
    }


    // This function is just to add dummy data to Final List Array
    private fun initializeFinalList() {

        val dataList1 = arrayListOf(
            ListItem(R.drawable.icon3, "50.5 BHD"),
            ListItem(R.drawable.icon3, "55.5 BHD"),
            ListItem(R.drawable.icon3, "56.5 BHD"),
            ListItem(R.drawable.icon3, "50.5 BHD"),
            ListItem(R.drawable.icon3, "55.5 BHD"),
            ListItem(R.drawable.icon3, "56.5 BHD"),
            ListItem(R.drawable.icon3, "50.5 BHD"),
            ListItem(R.drawable.icon3, "55.5 BHD"),
            ListItem(R.drawable.icon3, "56.5 BHD"),
            ListItem(R.drawable.icon3, "50.5 BHD"),
            ListItem(R.drawable.icon3, "55.5 BHD"),
            ListItem(R.drawable.icon3, "56.5 BHD"),
            ListItem(R.drawable.icon3, "50.5 BHD"),
            ListItem(R.drawable.icon3, "55.5 BHD"),
            ListItem(R.drawable.icon3, "56.5 BHD"),
        )

        val dataList2 = arrayListOf(
            ListItem(R.drawable.icon2, "20.5 BHD"),
            ListItem(R.drawable.icon2, "35.5 BHD"),
            ListItem(R.drawable.icon2, "46.5 BHD")
        )
        val dataList3 = arrayListOf(
            ListItem(R.drawable.icon3, "10.5 BHD"),
            ListItem(R.drawable.icon3, "25.5 BHD"),
            ListItem(R.drawable.icon3, "66.5 BHD")
        )

        finalList = arrayListOf(dataList1, dataList2, dataList3)
    }

}

