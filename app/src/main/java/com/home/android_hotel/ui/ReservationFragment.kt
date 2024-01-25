package com.home.android_hotel.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.home.android_hotel.R
import com.home.android_hotel.databinding.FragmentReservationBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ReservationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReservationFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private var _binding: FragmentReservationBinding? = null
    private val binding
        get() = _binding ?: throw java.lang.RuntimeException("FragmentReservationBinding == null")

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
        _binding = FragmentReservationBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textEditPhone.addTextChangedListener(object : TextWatcher {

            var isFormatting: Boolean = false

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Ничего не делаем
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Проверяем, что введенные символы являются цифрами

                Log.d("onTextChanged", "$s")
                if (s != null && s.isNotEmpty() && s.length <= 18)  {
                    val formattedNumber = StringBuilder()
                    if (s[0].isDigit()){
                        // Добавляем символы из шаблона +7 (***) ***-**-**
                        formattedNumber.append("+7 (")
                    }
                    for (i in s.indices) {
//                        if (i == 7 && s[i] != ' '){
//                            formattedNumber. append(" (${s[i]}")
//                        }
                        if (i == 7 && s[i] != ')') {
                            formattedNumber.append(") ${s[i]}")
                        } else if ((i == 12 || i == 15) && s[i] != '-') {
                            formattedNumber.append("-${s[i]}")
                        } else {
                            formattedNumber.append(s[i])
                        }
                    }
                    binding.textInputPhone.error = null
                    binding.textEditPhone.removeTextChangedListener(this)
                    binding.textEditPhone.setText(formattedNumber)
                    binding.textEditPhone.setSelection(formattedNumber.length)
                    binding.textEditPhone.addTextChangedListener(this)
                }
                else {
                    // Если длина введенного текста достигла максимальной длины маски (18 символов),
                    // то блокируем дальнейший ввод
                    binding.textEditPhone.removeTextChangedListener(this)
                    binding.textInputPhone.error = "Максимальная длина номера телефона достигнута"
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // Ничего не делаем
            }
        })

        binding.openInfoButton.setOnClickListener {
            val visibility = binding.inputLayout.visibility
            if (visibility == View.GONE) {
                binding.inputLayout.visibility = View.VISIBLE
                binding.openInfoButton.setImageResource(R.drawable.ic_up_arrow)
            } else {
                binding.inputLayout.visibility = View.GONE
                binding.openInfoButton.setImageResource(R.drawable.ic_down_arrow)
            }
        }

        binding.pay.setOnClickListener {
            findNavController().navigate(R.id.orderFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ReservationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReservationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}