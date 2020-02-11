package com.example.work_staff_marching.cyf.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.RadioGroup;

import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.fragment.MessageFragment;
import com.example.work_staff_marching.cyf.fragment.PersonAuditFragment;
import com.example.work_staff_marching.cyf.fragment.TaskFragment;
import com.example.work_staff_marching.cyf.fragment.UserMeFragment;

import java.util.ArrayList;

public class AdminIndexActivity extends AppCompatActivity {
    private static final String TAG = "AdminIndexActivity";
    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private RadioGroup radioGroup;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_index);
        initViews();
        initEvent();
        initData();
    }
    private void initViews() {
        //1、获取main_activity中的控件的实例化对象
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        //2、获取FragmentManager
        /*注意倒入的fragmentManager，如果你的fragment是android.support.v4.app包中的，
        你倒入的fragmentManager也要试v4v 包的，
        我这里的fragment是import android.app.Fragment; 所以我导入的fragmentManager也是android.app包下面的
        * */
        /*如果是v4包的要用this.getSupportFragmentManager();*/
        fragmentManager = this.getSupportFragmentManager();
    }

    private void initEvent() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radiobutton_person_audit: {
                        setCurrentFragment(0);
                        break;
                    }
                    case R.id.radiobutton_task_audit: {
                        setCurrentFragment(1);
                        break;
                    }
                    case R.id.radiobutton_march_management:{
                        setCurrentFragment(2);
                        break;
                    }
                    case R.id.radiobutton_march_result: {
                        setCurrentFragment(3);
                        break;
                    }
                    default: {
                    }
                }
            }
        });

    }

    private void initData() {
        /*3、数据的准备*/
        PersonAuditFragment personAuditFragment = new PersonAuditFragment();
        fragmentList.add(personAuditFragment);
        MessageFragment messageFragment = new MessageFragment();
        fragmentList.add(messageFragment);
        TaskFragment taskFragment = new TaskFragment();
        fragmentList.add(taskFragment);
        UserMeFragment userMeFragment = new UserMeFragment();
        fragmentList.add(userMeFragment);
//        //默认显示第1个也就是fragmentList.get(0)
        setCurrentFragment(0);

    }

    /**
     * 显示fragment
     * @param index
     */
    private void setCurrentFragment(int index){
        //3 通过fragmentmanager获取fragment的事务管理对象
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //4获取要显示的fragment
        Fragment fragment = fragmentList.get(index);
        //5、将要显示的fragment放入FragmentLayout中
        fragmentTransaction.replace(R.id.root_fragment_layout, fragment);
        //6、提交事务，确定显示
        fragmentTransaction.commit();
    }


}
