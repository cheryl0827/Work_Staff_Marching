package com.example.work_staff_marching.cyf.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.fragment.HomeFragment;
import com.example.work_staff_marching.cyf.utils.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangeCatageryActivity extends BaseActivity {


    @BindView(R.id.lv_left)
    ListView lvLeft;
    @BindView(R.id.lv_middle)
    ListView lvMiddle;
    @BindView(R.id.lv_right)
    ListView lvRight;
    ArrayAdapter<String> Adapter1 = null;
    ArrayAdapter<String> Adapter2 = null;
    ArrayAdapter<String> Adapter3 = null;
    static int provincePosition = 0;
    private String [] data1=new String[]{"农村农业","国土资源","城乡建设","劳动和社会保障","教育文体","卫生计生"};
    private String [][] data2=new String[][]{
            {"村务管理","土地承包经营","扶贫开发","动物防疫"},//农村农业
            {"土地资源管理","土地征收","矿产资源管理","海洋气象地震"},//国土资源
            {"村镇建设","工程质量","建筑市场","城乡规划"},//城乡建设
            {"劳动保护","就业培训","工资福利","劳动关系","退休政策及待遇"},//劳动和社会保障
            {"失学辍学","考试招生","教育体制","教育收费"},//教育文体
            {"公共卫生","医政监管","医患纠纷","人口计生"}//卫生计生
    };
    private String [][][] data3=new String[][][]{
            {// 农村农业
                    {"集体资产管理","集体财务公开","村务公开","村级债务"},//村务管理
                    {"土地承包","土地流转","宅基地纠纷"},//土地承包经营
                    {"扶贫开发政策","扶贫开发资金使用管理"},//扶贫开发
                    {"疫情防控","扑杀补偿"}//动物防疫
            },
            {//国土资源
                    {"土地规划","耕地保护","土地权属纠纷","土地转让",""},//土地资源管理
                    {"审批手续","土地征占","安置补偿","失地农民保障"},//土地征收
                    {"地质勘查","矿产开采","矿山地质环境"},//矿产资源管理
                    {"海洋","气象","地震"}//海洋气象地震
            },
            {//城乡建设
                    {"小城镇建设","新农村建设"},//村镇建设
                    {"工程质量","施工安全","抗震防灾"},//工程质量
                    {"工程招投标","勘察设计和施工监管","企业资质和执业资格","拖欠工程款"},//建筑市场
                    {"规划方案","历史名城保护","违章建筑","容积挡光"}//城乡规划
            },
            {//劳动和社会保障
                    {"劳动环境","劳动安全","女工保护","工作时间和休息休假"},//劳动保护
                    {"就业和再就业","职业培训","职业技能鉴定"},//就业培训
                    {"工资调整","工资发放","福利待遇","住房公积金","最低工资标准"},//工资福利
                    {"劳动合同纠纷","劳务派遣纠纷","非法用工","农民工权益"},//劳动关系
                    {"退休政策","退休人员待遇","退休金发放"}//退休政策及待遇
            },
            {//教育文体
                    {"教育救助","希望工程"},//失学辍学
                    {"招生录取","考场考纪"},//考试招生
                    {"教育体制改革","教育统筹管理","教育资源配置","异地升学"},//教育体制
                    {"收费标准","乱收费"}//教育收费
            },
            {//卫生计生
                    {"公共卫生","疾病预防控制","突发公共卫生事件处理"},//公共卫生
                    {"医疗机构管理","医务人员管理","血液管理","医疗收费","非法行医"},//医政监管
                    {"医疗事故争议","患者权益","医护人员权益"},//医患纠纷
                    {"人口政策","违法生育","失独家庭","计生执法","计划生育服务管理"}//人口计生
            }
    };
    @Override
    protected int getContentView() {
        return R.layout.activity_catagery;
    }

    @Override
    protected void init(Bundle saveInstanceState) {
        setTitle("问题类别修改");
        Adapter1 = new ArrayAdapter<String>(
                ChangeCatageryActivity.this, R.layout.catagery_design,data1);
        lvLeft.setAdapter(Adapter1);
        lvLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             其中position就是你点击的第position个选项
             **/
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Adapter2 = new ArrayAdapter<String>(
                        ChangeCatageryActivity.this, R.layout.catagery_design, data2[position]);
                lvMiddle.setAdapter(Adapter2);
                provincePosition = position;
                Adapter3=null;
                lvRight.setAdapter(Adapter3);
                TextView textView=(TextView)view.findViewById(R.id.TextView);
                ChangeOnlinePetitionActivity.catagery1String = textView.getText().toString();
                // Log.v("re", textView.getText().toString());
            }
        });
        lvMiddle.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Adapter3 = new ArrayAdapter<String>(
                        ChangeCatageryActivity.this, R.layout.catagery_design, data3[provincePosition][position]);
                lvRight.setAdapter(Adapter3);
                TextView textView1=(TextView)view.findViewById(R.id.TextView);
                ChangeOnlinePetitionActivity.catagery2String = textView1.getText().toString();
            }
        });
        lvRight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView2=(TextView)view.findViewById(R.id.TextView);
                ChangeOnlinePetitionActivity.catagery3String = textView2.getText().toString();
                ChangeOnlinePetitionActivity.catageryString=ChangeOnlinePetitionActivity.catagery1String+"-"+ChangeOnlinePetitionActivity.catagery2String+"-"+ChangeOnlinePetitionActivity.catagery3String;
                setResult(RESULT_OK);
                finish();
            }
        });
    }

}
