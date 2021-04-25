package com.reem.ushop.ui.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reem.ushop.adapter.SubCategoryAdapter;
import com.reem.ushop.databinding.FragmentSubCategoryBinding;
import com.reem.ushop.pojo.Subcategories;
import java.util.ArrayList;


public class SubCategoryFragment extends Fragment {
   private FragmentSubCategoryBinding binding;
   private View v;
   public  static ArrayList<Subcategories> subcategories=new ArrayList<>();
   private SubCategoryAdapter subCategoryAdapter;
    private static final String TAG = "SubCategoryFragment";

    public SubCategoryFragment() {
    }
    public static SubCategoryFragment newInstance(ArrayList<Subcategories> subcategory) {
        SubCategoryFragment fragment = new SubCategoryFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("sub cat", subcategory);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            subcategories = getArguments().getParcelableArrayList("sub cat");
            Log.e(TAG, "onCreate: "+subcategories.size() );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSubCategoryBinding.inflate(inflater, container, false);
        v = binding.getRoot();
        initView();
        return v;
    }

    @Override
    public void onResume() {
       setData();
        super.onResume();
    }

    private void setData() {
       subCategoryAdapter.setList(subcategories);
    }
    private void initView() {
        initRecy();
    }
    private void initRecy() {
        GridLayoutManager layoutManager =new GridLayoutManager(requireActivity(), 3, GridLayoutManager.VERTICAL, false);
        binding.rvSubCat.setHasFixedSize(true);
        binding.rvSubCat.setLayoutManager(layoutManager);
        subCategoryAdapter=new SubCategoryAdapter(requireContext());
        binding.rvSubCat.setAdapter(subCategoryAdapter);
    }
}