package com.hakangokturk.mymovie.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.hakangokturk.mymovie.R
import com.hakangokturk.mymovie.databinding.ItemDashboardViewpagerBinding
import com.hakangokturk.mymovie.model.Upcoming


class DashboardViewPagerAdapter(private val upcomingList: List<Upcoming.Result?>): PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = ItemDashboardViewpagerBinding.inflate(LayoutInflater.from(container.context), container, false)
        container.addView(binding.root)
        binding.upcoming = upcomingList[position]

        binding.root.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_dashboardFragment_to_upcomingFragment)
        }
        return binding.root
    }
    override fun getCount(): Int {
        return upcomingList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == (`object` as ConstraintLayout)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }
}