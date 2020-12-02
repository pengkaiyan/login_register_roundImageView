package com.example.login;



import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class LoginFragment extends Fragment {
    EditText editTextName;
    EditText editTextPassword;
    View imageView ;
    RelativeLayout layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //加载Fragment的界面
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        layout = (RelativeLayout) v.findViewById(R.id.layout);
        //获取用户名和密码输入控件
        editTextName = (EditText) v.findViewById(R.id.editTextName);
        editTextPassword = (EditText) v.findViewById(R.id.editTextPassword);
        imageView = v.findViewById(R.id.imageViewHead);

        //用id找到用户输入控件
        editTextName = (EditText) v.findViewById(R.id.editTextName);
        //用代码设置它的提示
        editTextName.setHint("请输入用户名");

//        //利用RoundedBitmapDrawable搞出圆形图像
//        //获取头像控件，实际上是把图像文件解码后创建Bitmap对象
//        //必须是ImageView
//        ImageView imageView=v.findViewById(R.id.imageViewHead);
//        //从Drawable资源获取Bitmap
//        Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.img1);
//        //创建RoundedBitmapDrawable对象
//        RoundedBitmapDrawable roundedBitmapDrawable =
//                RoundedBitmapDrawableFactory.create(getResources(), src);
//        //设置圆角半径（根据实际需求）
//        roundedBitmapDrawable.setCornerRadius(400);
//        //设置反锯齿
//        roundedBitmapDrawable.setAntiAlias(true);
//        //将Drawable设置给ImageView控件，这会覆盖掉在界面设计器中的设置的图像
//        imageView.setImageDrawable(roundedBitmapDrawable);

        //找到登录按钮
        Button buttonLogin = (Button) v.findViewById(R.id.buttonLogin);
        //添加侦听器，响应按钮的click事件
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"你点击了登录按钮", BaseTransientBottomBar.LENGTH_LONG).show();
            }
        });

        //找到注册按钮，为它设置点击事件侦听器
        Button buttonRegister = (Button) v.findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //当注册按钮被执行时调用此方法
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                RegisterFragment fragment = new RegisterFragment();

                //替换掉FrameLayout中现有的Fragment
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                //将这次切换放入后退栈中，这样可以在点后退键时自动返回上一个页面
                fragmentTransaction.addToBackStack("login");
                fragmentTransaction.commit();
            }
        });

        return v;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState){
        super.onViewStateRestored(savedInstanceState);

        //如果用户名和密码非空，则赋给相应控件
        MainActivity activity = (MainActivity) getActivity();
        if(activity != null) {
            if (activity.userName != null) {
                editTextName.setText(activity.userName);
            }
            if (activity.password != null) {
                editTextPassword.setText(activity.password);
            }
        }
    }
}
