package com.hydra.criminalintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.hydra.criminalintent.bean.Crime;
import com.hydra.criminalintent.bean.CrimeLab;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Hydra on 2017/4/17.
 */

public class CrimeFragment extends Fragment {

    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateBtn;
    private CheckBox mIsSolvedCheckBox;
    private static final String ARG_CRIME_ID = "crime_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mCrime = new Crime();
//        UUID mCrimeId = (UUID) getActivity().getIntent().getSerializableExtra(CrimeActivity.EXTRA_CRIME_ID);
        UUID mCrimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        Log.e("获得ID", mCrimeId+"");
        mCrime = CrimeLab.get(getActivity()).getCrime(mCrimeId);
    }

    //携带值的方法
    public static CrimeFragment newIntence(UUID crimeId){

        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //实 例 化 fragment 视 图 的 布 局
//这些注解是用来标注方法是否能传入null值，如果可以传入NUll值，则标记为nullbale，如果不可以则标注为Nonnull.
// 在我们做了一些不安全严谨的编码操作的时候,这些注释会给我们一些警告。
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,Bundle savedInstanceState) {
        //三个参数分别为布局资源ID、父视图、告知布局生成器是否将生成的视图添加给父视图
        View v = inflater.inflate(R.layout.fragment_crime, container, false);

        mTitleField = (EditText) v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDateBtn = (Button) v.findViewById(R.id.crime_date);
        updateDate();
//        mDateBtn.setEnabled(false);
        mDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
//                DatePickerFragment dialog = new DatePickerFragment();
              DatePickerFragment dialog =  DatePickerFragment.newIntent(mCrime.getDate());
                dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });
        mIsSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
        mIsSolvedCheckBox.setChecked(mCrime.isSolved());
        mIsSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK){
            return;
        }
        if (requestCode == REQUEST_DATE){
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCrime.setDate(date);
            updateDate();
        }
    }

    private void updateDate() {
        Date date = ;
        mDateBtn.setText(date.toString());
    }
}
