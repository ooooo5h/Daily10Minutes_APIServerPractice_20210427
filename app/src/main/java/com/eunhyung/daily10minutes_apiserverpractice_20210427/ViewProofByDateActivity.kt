package com.eunhyung.daily10minutes_apiserverpractice_20210427

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import com.eunhyung.daily10minutes_apiserverpractice_20210427.adapters.ProofAdapter
import com.eunhyung.daily10minutes_apiserverpractice_20210427.datas.Project
import com.eunhyung.daily10minutes_apiserverpractice_20210427.datas.Proof
import com.eunhyung.daily10minutes_apiserverpractice_20210427.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_view_proof_by_date.*
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ViewProofByDateActivity : BaseActivity() {

    lateinit var mProject : Project

    val mSelectedDate = Calendar.getInstance()

    val mProofList = ArrayList<Proof>()

    lateinit var mProofAdapter : ProofAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_proof_by_date)
        setupEvents()
        setValues()
    }
    override fun setupEvents() {

        postProofBtn.setOnClickListener {

            val myIntent = Intent(mContext, PostProofActivity::class.java)
            myIntent.putExtra("project", mProject)
            startActivity(myIntent)
        }

        selectDateBtn.setOnClickListener {

            val dateSetListener = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

                    Log.d("선택된 날짜", "${year}년 ${month}월 ${dayOfMonth}일" )

                    mSelectedDate.set(year, month, dayOfMonth)

                    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
                    dateTxt.text = simpleDateFormat.format(mSelectedDate.time)

                    getProofListByDate()

                }


            }

            val datePickerDialog = DatePickerDialog(mContext, dateSetListener,
                mSelectedDate.get(Calendar.YEAR),
                mSelectedDate.get(Calendar.MONTH),
                mSelectedDate.get(Calendar.DAY_OF_MONTH))
            datePickerDialog.show()

        }



    }
    override fun setValues() {

        mProject = intent.getSerializableExtra("projectInfo") as Project

        mProofAdapter = ProofAdapter(mContext, R.layout.proof_list_item, mProofList)
        proofListView.adapter = mProofAdapter

    }

    fun getProofListByDate () {

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        val dateString = simpleDateFormat.format(mSelectedDate.time)

        ServerUtil.getRequestProofListByDate(mContext, mProject.id, dateString, object : ServerUtil.JsonResponseHandler {
            override fun onResponse(jsonObj: JSONObject) {

                val dataObj = jsonObj.getJSONObject("data")
                val projectObj = dataObj.getJSONObject("project")
                val proofsArr = projectObj.getJSONArray("proofs")

                mProofList.clear()

                for (i in 0 until proofsArr.length()) {

//                    인증글 JSON -> Proof 객체로 변환 -> mProofList에 추가

                   mProofList.add( Proof.getProofFromJson(proofsArr.getJSONObject(i)))

                }

                runOnUiThread {
                    mProofAdapter.notifyDataSetChanged()
                }

            }


        })
    }
}