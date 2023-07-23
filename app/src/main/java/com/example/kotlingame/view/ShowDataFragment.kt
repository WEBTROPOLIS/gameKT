package com.example.kotlingame.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlingame.databinding.FragmentShowDataBinding
import com.example.kotlingame.viewmodel.DataViewModel

class ShowDataFragment : Fragment(), DataListAdapter.OnItemClickListener {

    private var _binding: FragmentShowDataBinding? = null
    private val binding get() = _binding!!
    private lateinit var dataViewModel: DataViewModel
    private lateinit var dataAdapter: DataListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShowDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataViewModel = ViewModelProvider(requireActivity()).get(DataViewModel::class.java)
        dataAdapter = DataListAdapter(mutableListOf())

        binding.recyclerView.apply {
            adapter = dataAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        dataAdapter.setOnItemClickListener(this)


        dataViewModel.allTask.observe(viewLifecycleOwner) { dataList ->
            dataAdapter.updateData(dataList)
            binding.tvNoData.visibility = if (dataList.isEmpty()) View.VISIBLE else View.GONE
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(position: Int) {
       // showDeleteConfirmationDialog(position)
    }

    override fun onDeleteButtonClick(position: Int) {
        val taskToDelete = dataAdapter.getItemAtPosition(position)
        taskToDelete?.let {
            Toast.makeText(requireContext(), "Eliminar", Toast.LENGTH_SHORT).show()
            dataViewModel.deleteData(it)


            dataAdapter.deleteItem(position)
        }
    }



}
