package com.example.kotlingame.view
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.kotlingame.databinding.FragmentAddDataBinding
import com.example.kotlingame.model.Datos
import com.example.kotlingame.viewmodel.DataViewModel


class AddDataFragment : Fragment() {

    private var _binding: FragmentAddDataBinding? = null
    private val binding get() = _binding!!
    private lateinit var dataViewModel: DataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataViewModel=ViewModelProvider(requireActivity()).get(DataViewModel::class.java)

        binding.btnSalir.setOnClickListener {
        parentFragmentManager.popBackStack()

        }

        binding.btnGuardar.setOnClickListener {
            val nombreJuego = binding.etNameGame.text.toString().trim()
            val nombreReal = binding.etNameReal.text.toString().trim()
            val edadText = binding.etAge.text.toString().trim()
            if(nombreJuego.isNotBlank() && nombreReal.isNotBlank() && edadText.isNotBlank()){
                val edad= edadText.toInt()
                val datos = Datos(0,nombreJuego,nombreReal,edad)
                dataViewModel.insertData(datos)
                Toast.makeText(context, "Usuario Agregado", Toast.LENGTH_SHORT).show()
                binding.etNameGame.text.clear()
                binding.etNameReal.text.clear()
                binding.etAge.text.clear()

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
