package com.example.work_staff_marching.cyf.ui;

import androidx.appcompat.app.AppCompatActivity;



import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.RadioGroup;
import com.example.work_staff_marching.R;
import com.example.work_staff_marching.cyf.fragment.HomeFragment;
import com.example.work_staff_marching.cyf.fragment.MessageFragment;

import java.util.ArrayList;
public class UserIndexActivity extends AppCompatActivity {
    private static final String TAG = "UserIndexActivity";
    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private RadioGroup radioGroup;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_index);
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
        fragmentManager = this.getFragmentManager();
    }

    private void initEvent() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radiobutton_home: {
                        setCurrentFragment(0);
                        break;
                    }
                    case R.id.radiobutton_message: {
                        setCurrentFragment(1);
                        break;
                    }
                    case R.id.radiobutton_profile: {
                        setCurrentFragment(2);
                        break;
                    }
                    case R.id.radiobutton_post:{
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
        HomeFragment homeFragment = new HomeFragment();
        fragmentList.add(homeFragment);
      MessageFragment messageFragment = new MessageFragment();
       fragmentList.add(messageFragment);
//       DiscoverFragment discoverFragment = new DiscoverFragment();
//        fragmentList.add(discoverFragment);
//        ProfileFragment profileFragment = new ProfileFragment();
//       fragmentList.add(profileFragment);
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
