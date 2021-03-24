package pe.flavia.navigation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pe.flavia.navigation.databinding.FragmentFirstBinding

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    3/15/21 - 3:45 PM
    Solera Mobile
*/

class FirstFragment: Fragment() {
    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        this.binding = FragmentFirstBinding.inflate(inflater)
        return this.binding.root
    }
}