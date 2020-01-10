package com.example.work_staff_marching.cyf.ui;

import android.os.Bundle;
import android.util.Log;
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
    private String[] provinces = new String[] {"福建省","浙江省","吉林省"};
    //地级选项值
    private String[][] citys = new String[][]
               {
                    { "福州市", "厦门市", "莆田市", "三明市", "泉州市", "漳州市", "南平市", "龙岩市", "宁德市" },
                    { "杭州市", "宁波市", "温州市", "嘉兴市", "湖州市" ,"绍兴市","金华市","舟山市","台州市","丽水市","衢州市"},
                    { "长春市", "吉林市", "四平市", "辽源市", "通化市", "白山市", "松原市", "白城市", "延边朝鲜族自治州" }
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

                    },
                    {//浙江省
                            {"上城区","下城区","江干区","拱墅区","西湖区","滨江区","萧山区","余杭区","富阳区","淳安县","建德市","临安市"}, //杭州
                            {"余姚市","慈溪市","奉化市","宁海县","象山县","江东区","海曙区","鄞州区","江北区","镇海区","北仑区"},//宁波
                            {"鹿城区","龙湾区","瓯海区","洞头区","瑞安市","乐清市","永嘉县","平阳县","苍南县","文成县","泰顺县","龙港市"},//温州
                            {"南湖区","秀洲区","海宁市","平湖市","桐乡市","嘉善县","海盐县"},//嘉兴
                            {"吴兴区","南浔区","德清县","长兴县","安吉县"},//湖州
                            {"越城区","柯桥区","上虞区","诸暨市","嵊州市","新昌县"},//绍兴
                            {"婺城区","金东区","兰溪市","东阳市","永康市","义乌市","武义县","浦江县","磐安县"},//金华
                            {"定海区","普陀区","岱山县","嵊泗县"},//舟山
                            {"椒江区","黄岩区","路桥区","临海市","温岭市","玉环市","三门县","天台县","仙居县"},//台州
                            {"莲都区","龙泉市","青田县","缙云县","遂昌县","松阳县","云和县","庆元县","景宁畲族自治县"},// 丽水
                            {"柯城区","衢江区","江山市","常山县","开化县","龙游县"}//衢州
                    },
                    {//吉林省
                            {"南关区","朝阳区","绿园区","二道区","双阳区","宽城区","九台区","榆树市","德惠市","农安县"},//长春
                            {"船营区","龙潭区","昌邑区","丰满区","磐石市","桦甸市","蛟河市","舒兰市","永吉县"},//吉林
                            {"铁西区","铁东区","公主岭市","双辽市","梨树县","伊通满族自治县"},//四平
                            {"龙山区","西安区","东丰县","东辽县"},//辽源
                            {"东昌区","二道江区","集安市","通化县","辉南县","柳河县","梅河口市"},//通化
                            {"浑江区","江源区","临江市","抚松县","靖宇县","长白朝鲜族自治县"},//白山
                            {"宁江区","扶余市","乾安县","长岭县","前郭尔罗斯蒙古族自治县"},//松原
                            {"洮北区","洮南市","大安市","镇赉县","通榆县"},//白城市
                            {"延吉市","图们市","敦化市","和龙市","珲春市","龙井市","汪清县","安图县"}//延边朝鲜族自治

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
//            String userName = getIntent().getStringExtra("userName");
//            String phone = getIntent().getStringExtra("phone");
//            String password = getIntent().getStringExtra("password");
//            //String roleName = getIntent().getStringExtra("roleName");
//            Log.v("re","userName");
//            Log.v("re","phone");
//            Log.v("re","password");


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

