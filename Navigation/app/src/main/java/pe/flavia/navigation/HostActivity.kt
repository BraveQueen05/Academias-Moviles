package pe.flavia.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import pe.flavia.navigation.databinding.HostActivityBinding
import pe.flavia.navigation.fragments.FirstFragment
import pe.flavia.navigation.fragments.SecondFragment
import pe.flavia.navigation.fragments.ThirdFragment

class HostActivity : AppCompatActivity() {

    private lateinit var binding: HostActivityBinding

    private val vpAdapter by lazy { ViewPagerAdapter(this) }

    private val list = listOf(FirstFragment(), SecondFragment(), ThirdFragment())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind()
        initVp()
        setUpTab()
    }

    private fun bind(){
        this.binding = HostActivityBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
    }

    private fun initVp(){
        this.vpAdapter.list = this.list
        this.binding.vp.adapter = this.vpAdapter
        this.binding.vp.getChildAt(0)?.apply {
            if (this is RecyclerView) this.setOverScrollMode(View.OVER_SCROLL_NEVER)
        }
    }

    private fun setUpTab(){
        TabLayoutMediator(this.binding.tabLayout, this.binding.vp) { tab, position ->
            tab.text = "${this.list[position]::class.simpleName}"
        }.attach()
    }
}