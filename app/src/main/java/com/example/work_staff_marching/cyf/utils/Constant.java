package com.example.work_staff_marching.cyf.utils;

//请求对应
public class Constant {


    // 172.20.10.5 无线地址   Staff_Work_Marching服务器
    public static String BASE_URL = "http://192.168.9.104:8080/Staff_Work_Marching";

    /**
     * 登录
     *
     * @param phone
     * @param password
     */
    public static String user_login = BASE_URL + "/LoginServlet";
    /**
     * 查询所有用户
     */
    public static String user_select_all = BASE_URL + "/SelectUserServlet";
    /**
     * 上传图片
     */
    public static String upload_img = BASE_URL + "/UploadImageServlet";
    /**
     * 用户注册
     */
    public static String user_regist = BASE_URL + "/RegistServlet";
    /**
     * 获取验证码,类似前后台交接的接口
     */
    public static String get_code = BASE_URL + "/AddIdentifyCodeServlet";
    /**
     * 工作用户注册
     */
    public static String get_register = BASE_URL + "/UserBaseRegisterServlet";
    /**
     * 普通用户注册
     */
    public static String get_userregister = BASE_URL + "/UserRegisterServlet";
    /**
     * 找回密码，并更新
     */
    public static String get_forgetpassword = BASE_URL + "/ForgetPasswordServlet";
    /**
     * 用户登录
     */
    public static String get_userlogin= BASE_URL + "/UserLoginServlet";

}
