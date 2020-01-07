package com.example.work_staff_marching.cyf.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.utils.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterUserActivity extends BaseActivity {

    @BindView(R.id.indentificationCard)
    EditText indentificationCard;
    @BindView(R.id.province)
    Spinner province;
    @BindView(R.id.city)
    Spinner city;
    @BindView(R.id.country)
    Spinner country;
    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.register)
    Button register;
    @BindView(R.id.cancel)
    Button cancel;
    ArrayAdapter<String> provinceAdapter = null;  //省级适配器
    ArrayAdapter<String> cityAdapter = null;    //地级适配器
    ArrayAdapter<String> countyAdapter = null;    //县级适配器
    static int provincePosition = 0;

    //省级选项值
    private String[] provinces = new String[] {"福建省"};//,"浙江省","吉林省"
    //地级选项值
    private String[][] citys = new String[][]
               {
                    { "福州市", "厦门市", "莆田市", "三明市", "泉州市", "漳州市", "南平市", "龙岩市", "宁德市" }
//                    { "杭州市", "宁波市", "温州市", "嘉兴市", "湖州市" ,"绍兴市","金华市","舟山市","台州市","丽水市"},
//                    { "长春市", "吉林市", "四平市", "辽源市", "通化市", "白山市", "松原市", "白城市", "延边朝鲜族自治州",
//                            "东丽区" }
               };

    //县级选项值
    private String[][][] countrys = new String[][][]
            {
                    {    //福建省
                            {"鼓楼区","台江区","仓山区","马尾区","晋安区","闽侯县","连江县","罗源县","闽清县","永泰县","平潭县","福清市","长乐市"}, //福州
                            {"思明区","海沧区","湖里区","集美区","同安区","翔安区"}, //厦门
                            {"城厢区","涵江区","荔城区","秀屿区","仙游县"},  //莆田
                            {"梅列区","三元区","明溪县","宁化县","大田县","尤溪县","沙县","将乐县","秦宁县","建宁县","永安市"}, //三明
                            {"鲤城区","丰泽区","洛江区","泉港区","惠安县","安溪县","德化县","金门县","石狮市","晋江市","南安市"},  //泉州
                            {"芗城区","龙文区","云霄县","长泰县","东山县","南靖县","平和县","华安县","龙海市"},  //漳州
                            {"延平区","建阳区","顺昌县","蒲城县","光泽县","松溪县","郑和县","邵武市","武夷山市","建瓯市"},  //南平
                            {"新罗区","永定区","长汀县","上杭县","武平县","漳平市"},  //龙岩
                            {"蕉城区","霞浦县","古田县","屏南县","寿宁县","周宁县","福安市","福鼎市"},  //宁德

                    }
            };
        @Override
        protected int getContentView () {
            return R.layout.activity_register_user;
        }

        @Override
        protected void init (Bundle saveInstanceState){
            setTitle("普通用户注册");
            setSpinner();

        }
        @OnClick({R.id.register, R.id.cancel})
        public void onViewClicked (View view){
            switch (view.getId()) {
                case R.id.register:
                    break;
                case R.id.cancel:
                    break;
            }
        }
    /*
     * 设置下拉框
     */
    private void setSpinner() {
        //绑定适配器和值
        provinceAdapter = new ArrayAdapter<String>(RegisterUserActivity.this, android.R.layout.simple_spinner_item, provinces);
        province.setAdapter(provinceAdapter);
        province.setSelection(0, true);  //设置默认选中项

        cityAdapter = new ArrayAdapter<String>(RegisterUserActivity.this, android.R.layout.simple_spinner_item, citys[0]);
        city.setAdapter(cityAdapter);
        city.setSelection(0, true);  //默认选中第0个

        countyAdapter = new ArrayAdapter<String>(RegisterUserActivity.this, android.R.layout.simple_spinner_item, countrys[0][0]);
        country.setAdapter(countyAdapter);
        country.setSelection(0, true);
        //省级下拉框监听
        province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            // 表示选项被改变的时候触发此方法，主要实现办法：动态改变地级适配器的绑定值
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                //position为当前省级选中的值的序号
                //将地级适配器的值改变为city[position]中的值
                cityAdapter = new ArrayAdapter<String>(
                        RegisterUserActivity.this, android.R.layout.simple_spinner_item, citys[position]);
                // 设置二级下拉列表的选项内容适配器
                city.setAdapter(cityAdapter);
                provincePosition = position;    //记录当前省级序号，留给下面修改县级适配器时用
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        //市级下拉监听
        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                countyAdapter = new ArrayAdapter<String>(RegisterUserActivity.this, android.R.layout.simple_spinner_item, countrys[provincePosition][position]);
                country.setAdapter(countyAdapter);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }
    }

