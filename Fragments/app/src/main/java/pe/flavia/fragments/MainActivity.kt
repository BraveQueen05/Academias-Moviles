package pe.flavia.fragments

import android.os.Bundle
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import pe.flavia.fragments.databinding.ActivityMainBinding
import pe.flavia.fragments.detail.FragmentUserDetail
import pe.flavia.fragments.detail.FragmentVegetableDetail
import pe.flavia.fragments.entities.User
import pe.flavia.fragments.entities.Vegetable
import pe.flavia.fragments.list.FragmentList
import pe.flavia.fragments.utils.USER
import pe.flavia.fragments.utils.USER_DETAIL_FRAGMENT
import pe.flavia.fragments.utils.VEGETABLE
import pe.flavia.fragments.utils.VEGETABLE_DETAIL_FRAGMENT

class MainActivity : AppCompatActivity(), FragmentList.IFragmentList {

    private lateinit var binding: ActivityMainBinding
    private var vegetableDetailFragment = FragmentVegetableDetail()
    private var userDetailFragment = FragmentUserDetail()

    private var fragmentList: FragmentList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind()
        assignFragments()
    }

    private fun bind(){
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
    }

    private fun assignFragments() {
        this.fragmentList = this.supportFragmentManager.findFragmentById(R.id.fList) as? FragmentList
    }

    override fun showDetail(item: Any) {
         if (item is Vegetable) this.vegetableDetailFragment.updateData(item) else this.userDetailFragment.updateData(item as User)
    }

    override fun firstItem(item: Any) {
        if (item is Vegetable) vegetableDetailFragment(item) else userDetailFragment(item as User)
    }

    private fun vegetableDetailFragment(item: Vegetable) {
        vegetableDetailFragment.arguments = bundleOf(VEGETABLE to item)
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out)
            replace(R.id.flDetail, vegetableDetailFragment, VEGETABLE_DETAIL_FRAGMENT)
            commit()
        }
    }

    private fun userDetailFragment(item: User){
        userDetailFragment.arguments = bundleOf(USER to item)
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out)
            replace(R.id.flDetail, userDetailFragment,USER_DETAIL_FRAGMENT)
            commit()
        }
    }
}