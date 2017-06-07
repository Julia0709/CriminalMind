package com.derrick.park.criminalmind;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by park on 2017-06-01.
 */

public class CrimeListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdater;

    private ImageView mImageViewSolved;

    private SimpleDateFormat simpleDateFormatDay = new SimpleDateFormat("MM/dd/yyyy");


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        mAdater = new CrimeAdapter(crimes);
        mCrimeRecyclerView.setAdapter(mAdater);
    }

    private class CrimeHolder extends RecyclerView.ViewHolder {

        private TextView mTitleTextView;
        private TextView mDateTextView;

        public CrimeHolder(View v) {
            super(v);

            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);

            mImageViewSolved = (ImageView) itemView.findViewById(R.id.image_solved);

        }

        public void bind(final Crime crime) {

            mTitleTextView.setText(crime.getTitle());

            Date date = crime.getDate();
            mDateTextView.setText(simpleDateFormatDay.format(date));

            mImageViewSolved.setImageResource(R.drawable.ic_solved);

            // toast when itemView is clicked
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Number: " + crime.getTitle(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    // Adapter
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @Override
        public int getItemViewType(int index) {

            if (mCrimes.get(index).ismRequiresPolice() == true) {
                return 0;
            } else {
                return 1;
            }
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            // initialize view
            View view = null;

            // use normal layout or special layout with button
            if (viewType == 0) {
                view = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_crime, parent, false);
            } else if (viewType == 1) {
                view = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_serious_crime, parent, false);
            }

            return new CrimeHolder(view);

        }

        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bind(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }

}
