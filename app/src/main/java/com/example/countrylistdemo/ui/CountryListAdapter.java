package com.example.countrylistdemo.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countrylistdemo.R;
import com.example.countrylistdemo.databinding.RowCountryListBinding;
import com.example.countrylistdemo.ui.general.vo.GeneralResponse;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryListViewHolder> {

    private Context mContext;
    private GeneralResponse mServiceList;

    public CountryListAdapter(Context mContext, GeneralResponse serviceList) {
        this.mContext = mContext;
        this.mServiceList = serviceList;
    }

    @NonNull
    @Override
    public CountryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowCountryListBinding mBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.row_country_list, parent, false);
        return new CountryListAdapter.CountryListViewHolder(mBinding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CountryListViewHolder holder, int position) {
        holder.binding.countryName.setText(mServiceList.getDataTests().get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mServiceList.getDataTests().size();
    }

    public class CountryListViewHolder extends RecyclerView.ViewHolder {
        private final RowCountryListBinding binding;

        CountryListViewHolder(@NonNull RowCountryListBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }


}

