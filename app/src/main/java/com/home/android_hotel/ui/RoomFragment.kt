package com.home.android_hotel.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.home.android_hotel.R
import com.home.android_hotel.databinding.FragmentRoomBinding
import com.home.android_hotel.databinding.RoomTableRowBinding
import com.home.fooddelivery.ui.menu.adapters.ViewPagerAdapter.PeculiaritiesAdapter
import com.home.fooddelivery.ui.menu.adapters.ViewPagerAdapter.RoomsAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RoomFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RoomFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentRoomBinding? = null
    private val binding
        get() = _binding ?: throw java.lang.RuntimeException("FragmentRoomBinding == null")

    lateinit var roomViewModel: RoomViewModel

    private lateinit var pageChangeListener: ViewPager2.OnPageChangeCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        roomViewModel = ViewModelProvider(this)[RoomViewModel::class.java]

        _binding = FragmentRoomBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        roomViewModel.roomsList.observe(viewLifecycleOwner) {
            Log.d("data", "${it.toString()}")

            for (data in it) {

                // Создаем новый TableRow из макета, используя ViewBinding
                val tableRowBinding = RoomTableRowBinding.inflate(layoutInflater)
                val tableRow = tableRowBinding.root // Получаем корневой элемент TableRow

                // Находим ViewPager внутри TableRow и устанавливаем данные из списка фото
                val roomPager = tableRowBinding.includeSlider.slider
                val roomsAdapter = RoomsAdapter()
                roomsAdapter.submitList(data.imageUrls)
                roomPager.adapter = roomsAdapter

                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(8, 0, 8, 0)
                }

                val dotsIndicator = tableRowBinding.includeSlider.dotsIndicator

                val dotsArray = Array(data.imageUrls.size) { ImageView(context) }


                dotsArray.forEach {
                    it.setImageResource(
                        R.drawable.dot_inactive
                    )
                    dotsIndicator.addView(it, params)
                }

//                    val dotsArray = ArrayList<ImageView>() // Список для хранения точек
//                for (i in 0 until data.imageUrls.size) { // Создаем точки для каждого элемента ViewPager2
//                    val dot = ImageView(context)
//                    dot.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.gradient_dot)) // Устанавливаем градиентный фон точек
//                    dot.layoutParams = LinearLayout.LayoutParams(
//                        ViewGroup.LayoutParams.WRAP_CONTENT,
//                        ViewGroup.LayoutParams.WRAP_CONTENT
//                    )
//
//                    // Устанавливаем margin для точек
//                    val params = dot.layoutParams as LinearLayout.LayoutParams
//                    params.setMargins(5, 0, 5, 0)
//                    dot.layoutParams = params
//
//                    dotsArray.add(dot) // Добавляем точку в список
//                    dotsIndicator.addView(dot) // Добавляем точку в LinearLayout (индикатор)
//                }

                // По умолчанию выбрана первое фото
                if (dotsArray.isNotEmpty()) {
                    dotsArray[0].setImageResource(R.drawable.dot_active)
                }

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
                roomPager.registerOnPageChangeCallback(pageChangeListener)


                // Находим recyclerView внутри TableRow и устанавливаем данные из списка
                val recyclerView = tableRowBinding.rvPeculiarity
                val peculiaritiesAdapter = PeculiaritiesAdapter()

                val layoutManager = FlexboxLayoutManager(context)
                layoutManager.flexDirection =
                    FlexDirection.ROW // Устанавливаем направление - горизонтальное
                layoutManager.flexWrap =
                    FlexWrap.WRAP // Разрешаем перенос элементов на новую строку
                recyclerView.layoutManager = layoutManager


                peculiaritiesAdapter.submitList(data.peculiarities)
                recyclerView.adapter = peculiaritiesAdapter

                with(tableRowBinding) {
                    titleAbout.text = data.name
                    price.text = data.price.toString()
                    pricePer.text = data.pricePer
                    toChooseRoom.setOnClickListener {
                        findNavController().navigate(R.id.reservationFragment)
                    }
                }
                binding.tableLayout.addView(tableRow)
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RoomFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RoomFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}