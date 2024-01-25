package com.home.android_hotel.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.home.android_hotel.R
import com.home.android_hotel.databinding.FragmentHotelBinding
import com.home.fooddelivery.ui.menu.adapters.ViewPagerAdapter.HotelAdapter


class HotelFragment : Fragment() {

    private var _binding: FragmentHotelBinding? = null
    private val binding
        get() = _binding ?: throw java.lang.RuntimeException("FragmentHotelBinding == null")


    private lateinit var hotelPager: ViewPager2
    private lateinit var hotelAdapter: HotelAdapter
    private lateinit var pageChangeListener: ViewPager2.OnPageChangeCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHotelBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSlider()

        binding.toChooseRoom.setOnClickListener {
            findNavController().navigate(R.id.roomFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initSlider() {
        hotelPager = binding.includeSlider.slider
        hotelAdapter = HotelAdapter()
        hotelPager.adapter = hotelAdapter

        val dotsIndicator: LinearLayout = binding.includeSlider.dotsIndicator

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(8, 0, 8, 0)
        }

        val dotsArray = Array(hotelAdapter.imageArray.size) { ImageView(context) }

        dotsArray.forEach {
            it.setImageResource(
                R.drawable.dot_inactive
            )
            dotsIndicator.addView(it, params)
        }

        // По умолчанию выбрана первое фото
        if (dotsArray.isNotEmpty()) {
            dotsArray[0].setImageResource(R.drawable.dot_active)
        }

//        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                sliderAdapter.setDotsIndicator(dotsIndicator, position)
//            }
//        })

        pageChangeListener = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                dotsArray.mapIndexed { index, imageView ->
                    if (position == index) {
                        imageView.setImageResource(
                            R.drawable.dot_active
                        )
                    } else {
                        imageView.setImageResource(R.drawable.dot_inactive)
                    }
                }
                super.onPageSelected(position)
            }
        }
        hotelPager.registerOnPageChangeCallback(pageChangeListener)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HotelFragment.
         */
        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            HotelFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
    }
}