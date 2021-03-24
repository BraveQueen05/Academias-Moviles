package pe.flavia.navigation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pe.flavia.navigation.databinding.FragmentFirstBinding
import pe.flavia.navigation.databinding.FragmentSecondBinding

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    3/15/21 - 3:46 PM
    Solera Mobile
*/

class SecondFragment: Fragment() {
    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        this.binding = FragmentSecondBinding.inflate(inflater)
        return this.binding.root
    }
}