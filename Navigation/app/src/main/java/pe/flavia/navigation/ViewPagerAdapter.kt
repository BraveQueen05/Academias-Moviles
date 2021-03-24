package pe.flavia.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import pe.flavia.navigation.fragments.FirstFragment
import pe.flavia.navigation.fragments.SecondFragment
import pe.flavia.navigation.fragments.ThirdFragment

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    3/24/21 - 12:55 PM
    Solera Mobile
*/

class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    var list = listOf<Fragment>()

    override fun getItemCount(): Int = this.list.size

    override fun createFragment(position: Int): Fragment = this.list[position]
}