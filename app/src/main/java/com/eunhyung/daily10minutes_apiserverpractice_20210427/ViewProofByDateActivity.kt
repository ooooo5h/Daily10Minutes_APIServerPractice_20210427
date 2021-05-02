package com.eunhyung.daily10minutes_apiserverpractice_20210427

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_view_proof_by_date.*

class ViewProofByDateActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_proof_by_date)
        setupEvents()
        setValues()
    }
    override fun setupEvents() {

        selectDateBtn.setOnClickListener {

            val dateSetListener = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

                    Log.d("선택된 날짜", "${year}년 ${month}월 ${dayOfMonth}일" )

                }


            }

            val datePickerDialog = DatePickerDialog(mContext, dateSetListener, 2021, 4, 24)
            datePickerDialog.show()

        }



    }
    override fun setValues() {

    }
}