package com.jlhan.mvvm.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.jlhan.mvvm.R;
import com.jlhan.mvvm.databinding.LayoutItemSwordsmanBinding;
import com.jlhan.mvvm.mvvm.Swordsman;

import java.util.List;

public class SwordsmanAdapter extends RecyclerView.Adapter<SwordsmanAdapter.SwordsmanViewholder> {


    private List<Swordsman> mList;

    public SwordsmanAdapter(List<Swordsman> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public SwordsmanViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemSwordsmanBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.layout_item_swordsman, parent, false);
        return new SwordsmanViewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SwordsmanViewholder holder, int position) {
        Swordsman swordsman = mList.get(position);
        holder.getBinding().setSwordsman(swordsman);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class SwordsmanViewholder extends RecyclerView.ViewHolder {

        private LayoutItemSwordsmanBinding binding;

        public SwordsmanViewholder(@NonNull LayoutItemSwordsmanBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public LayoutItemSwordsmanBinding getBinding() {
            return binding;
        }
    }

} 