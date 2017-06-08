package com.derrick.park.criminalmind;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static android.graphics.Color.BLACK;

/**
 * Created by park on 2017-06-01.
 */

public class CrimeListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdater;

    private ImageView mImageViewSolved;

    private SimpleDateFormat simpleDateFormatDay = new SimpleDateFormat("MM/dd/yyyy");

    // EXTRA_ID for lifecycle
    public static final String EXTRA_ID = "com.derrick.park.criminalmind.id";


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
        private ImageView mImageViewSolved;

//        public CrimeHolder(View v) {
//            super(v);
        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime, parent, false));

            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);

            mImageViewSolved = (ImageView) itemView.findViewById(R.id.image_solved);

        }

        public void bind(final Crime crime) {

            mTitleTextView.setText(crime.getTitle());

            Date date = crime.getDate();
            mDateTextView.setText(simpleDateFormatDay.format(date));

            // solved_image
            mImageViewSolved.setVisibility(View.VISIBLE);
            if (crime.isSolved() == true) {
                mImageViewSolved.setVisibility(View.VISIBLE);
            }
            else {
                mImageViewSolved.setVisibility((View.INVISIBLE));
            }

//            mImageViewSolved.setImageResource(R.drawable.ic_solved);

            // go to edit page
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(v.getContext(), "Number: " + crime.getTitle(), Toast.LENGTH_SHORT).show();
                    Intent intent = CrimeFragment.newIntent(getActivity(), crime.getId().toString());
                    v.getContext().startActivity(intent);
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

//        @Override
//        public int getItemViewType(int index) {
//
//            if (mCrimes.get(index).ismRequiresPolice() == true) {
//                return 0;
//            } else {
//                return 1;
//            }
//        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {

//            // initialize view
//            View view = null;
//
//            // use normal layout or special layout with button
//            if (viewType == 0) {
//                view = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_crime, parent, false);
//            } else if (viewType == 1) {
//                view = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_serious_crime, parent, false);
//            }

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CrimeHolder(layoutInflater, parent);

        }

        @Override
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
