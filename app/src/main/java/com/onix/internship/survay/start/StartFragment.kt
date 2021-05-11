package com.onix.internship.survay.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.onix.internship.survay.databinding.StartFragmentBinding
import com.onix.internship.survay.login.LoginFragment
import com.onix.internship.survay.register.RegisterFragment

class StartFragment : Fragment() {

    private lateinit var binding: StartFragmentBinding
    private lateinit var fragmentAdapter: FragmentAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = StartFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tabTitles = arrayOf("LOGIN", "REGISTER")
        fragmentAdapter = FragmentAdapter(this)
        viewPager = binding.viewPager
        viewPager.adapter = fragmentAdapter

        val tabLayout = binding.tableLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }
}
class FragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val fragmentList = arrayOf(LoginFragment(), RegisterFragment())
    override fun getItemCount() = fragmentList.size

    override fun createFragment(position: Int): Fragment {
       return fragmentList[position]
    }
}