package com.mp.harga.ui.main

import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mp.harga.R
import com.mp.harga.adapter.ClassificationAdapter
import com.mp.harga.adapter.DataAdapter
import com.mp.harga.core.data.source.remote.response.DataResponse
import com.mp.harga.core.domain.model.Classification
import com.mp.harga.databinding.ActivityMainBinding
import com.mp.harga.ui.base.BaseActivity
import com.mp.harga.ui.main.dialog.DialogProvince
import com.mp.harga.utils.preference.DataPreference.indexClassification
import com.mp.harga.utils.preference.DataPreference.indexLocation
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(), SwipeRefreshLayout.OnRefreshListener {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var classificationAdapter : ClassificationAdapter
    private lateinit var dataAdapter : DataAdapter
    private var dataResponse : DataResponse? = null
    private lateinit var sharedMainViewModel : SharedMainViewModel

    override fun initLayout(): View {
        _binding = ActivityMainBinding.inflate(layoutInflater)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        return binding.root
    }

    override fun initView() {
        super.initView()
        with(binding) {
            tvLokasi.text = indexLocation
        }
    }

    override fun initData() {
        super.initData()
        showShimmer(true)
        mainViewModel.getData()
    }

    override fun initObserver() {
        super.initObserver()
        sharedMainViewModel = ViewModelProvider(this)[SharedMainViewModel::class.java]
        sharedMainViewModel.dataProvince.observe(this) { province ->
            indexLocation = province
            with(binding) {
                tvLokasi.text = province
            }
            if (dataResponse != null) {
                dataAdapter.submitList(dataResponse!!.commodity!!)
            }
        }

        mainViewModel.loadingLiveData.observe(this){ loading ->
            showShimmer(loading)
        }

        mainViewModel.recipesLiveData.observe(this){ response ->
            dataResponse = response
            dataAdapter.submitList(response.commodity!!)
            binding.tvDate.text = response.updatedAt
        }
    }

    override fun initClickListener() {
        super.initClickListener()
        with(binding){
            mainPage.setOnRefreshListener(this@MainActivity)

            btnLocation.setOnClickListener {
                showDialogProvince()
            }
        }
    }

    override fun initRecyclerView() {
        super.initRecyclerView()
        classificationAdapter = ClassificationAdapter(
            onItemClick = {
                indexClassification = it
                setClassification()
                if (dataResponse != null) {
                    dataAdapter.submitList(dataResponse!!.commodity!!)
                }
            }
        )

        dataAdapter = DataAdapter()

        with(binding) {
            rvData.apply {
                adapter = dataAdapter
            }
            rvClassification.apply {
                adapter = classificationAdapter
            }
        }
        setClassification()
    }

    override fun onRefresh() {
        initData()
        with(binding){
            mainPage.isRefreshing = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showShimmer(parBoolean: Boolean){
        with(binding){
            shimmer.isVisible = parBoolean
            mainFooter.isVisible = !parBoolean
            mainPage.isVisible = !parBoolean
        }
    }

    private fun setClassification() {
        val classification = listOf(
            Classification("Beras", R.drawable.seed),
            Classification("Daging Ayam", R.drawable.chicken),
            Classification("Daging Sapi", R.drawable.beef),
            Classification("Telur Ayam", R.drawable.egg),
            Classification("Minyak Goreng", R.drawable.oil),
            Classification("Gula Pasir", R.drawable.sugar),
            Classification("Bawang Putih", R.drawable.garlic),
            Classification("Bawang Merah", R.drawable.onion),
            Classification("Cabai Merah", R.drawable.pepper),
            Classification("Cabai Rawit", R.drawable.chili),
        )
        classificationAdapter.submitList(classification)
    }

    private fun showDialogProvince(){
        DialogProvince().apply {
            setStyle(
                BottomSheetDialogFragment.STYLE_NORMAL,
                R.style.BaseBottomSheetDialog
            )
            show(supportFragmentManager, DialogProvince.TAG)
        }
    }
}