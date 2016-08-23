package com.dastanapps.calculaterda;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.dastanapps.dastanLib.networks.DastanRest;
import com.dastanapps.dastanLib.networks.IRestRequest;
import com.dastanapps.dastanLib.networks.RestAPI;
import com.dastanapps.dastanLib.networks.RestRequest;
import com.dastanapps.dastanLib.networks.RestResponse;
import com.dastanapps.dastanLib.utils.FontUtils;
import com.dastanapps.dastanLib.utils.StringUtils;
import com.dastanapps.dastanLib.utils.ViewUtils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IRestRequest {


    @Bind(R.id.input_age)
    public TextInputLayout input_age;
    @Bind(R.id.input_height)
    public TextInputLayout input_height;
    @Bind(R.id.input_weight)
    public TextInputLayout input_weight;

    @Bind(R.id.edt_age)
    public EditText edt_age;
    @Bind(R.id.edt_height)
    public EditText edt_height;
    @Bind(R.id.edt_weight)
    public EditText edt_weight;
    @Bind(R.id.rg_gender)
    public RadioGroup rg_gender;
    @Bind(R.id.rb_female)
    public RadioButton rb_female;
    @Bind(R.id.rb_male)
    public RadioButton rb_male;
    @Bind(R.id.spinner)
    public Spinner spinner;
    @Bind(R.id.btn_calcrda)
    public Button btn_calcrda;

    @Bind(R.id.tv_bmr)
    public TextView tv_bmr;
    @Bind(R.id.tv_tcr)
    public TextView tv_tcr;

    @Bind(R.id.rv_nutrients)
    RecyclerView rv_nutrients;

    @Bind(R.id.cv_bmr)
    CardView cv_bmr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        FontUtils.setRobotoRegular(edt_age, edt_height, edt_weight, rb_female, rb_male);
        FontUtils.setRobotoMedium(tv_tcr, tv_bmr);
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_nutrients.setLayoutManager(llm);

        rv_nutrients.setNestedScrollingEnabled(false);

        btn_calcrda.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (!StringUtils.isFormValidRangeCheck(edt_age, input_age, "Age", 200)) {
            return;
        }

        if (!StringUtils.isFormValidRangeCheck(edt_weight, input_weight, "Weight", 500)) {
            return;
        }

        if (!StringUtils.isFormValidRangeCheck(edt_height, input_height, "Height", 300)) {
            return;
        }

        int rggenderId = rg_gender.getCheckedRadioButtonId();
        String gender = "0";
        if (rggenderId == -1) {
            ViewUtils.showToast(MainActivity.this, "Please select gender");
            return;
        } else if (rggenderId == R.id.rb_male) {
            gender = "1";
        }

        HashMap<String, String> postParams = RestRequest.prepareRDA(edt_weight.getText().toString(), edt_height.getText().toString(), edt_age.getText().toString(),
                gender, spinner.getSelectedItemPosition() + "");
        DastanRest.sentPostRequest(RestAPI.GET_RDA, postParams, RestAPI.ID_GET_RDA, this);
        ViewUtils.showProgressDialog(MainActivity.this);
    }

    @Override
    public void onResponse(int reqID, String resp) {
        ViewUtils.hideProgressDialog();
        if (isFinishing()) return;
        if (reqID == RestAPI.ID_GET_RDA && !TextUtils.isEmpty(resp)) {
            RDAResultB rdaResultB = RestResponse.parseNutrientsData(resp);
            if (rdaResultB != null && rdaResultB.status.equals("1")) {
                tv_bmr.setText("BMR: " + rdaResultB.bmr);
                tv_tcr.setText("TCR: " + rdaResultB.tcr);
                cv_bmr.setVisibility(View.VISIBLE);
                ArrayList<NutrientItemsB> nutrientItemsBs = RestResponse.getNutrients(rdaResultB.data);
                NutrientsAdap nutrientsAdap = new NutrientsAdap(MainActivity.this, nutrientItemsBs);
                rv_nutrients.setAdapter(nutrientsAdap);
            } else {
                ViewUtils.showToast(MainActivity.this, "Invalid Response");
            }
        } else {
            ViewUtils.showToast(MainActivity.this, "Invalid Request");
        }
    }

    @Override
    public void onError(String error) {

    }
}
