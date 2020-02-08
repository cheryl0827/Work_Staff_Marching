package com.example.work_staff_marching.cyf.utils;

//请求对应
public class Constant {
    // 172.20.10.5 无线地址   Staff_Work_Marching服务器
    public static String BASE_URL = "http://192.168.9.104:8080/Staff_Work_Marching";
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
    /**
     * 网上上访
     */
    public static String get_taskadd= BASE_URL + "/TaskAddServlet";
    /**
     * 诉求任务权重表
     */
    public static String get_taskproportion= BASE_URL + "/UserTaskProportionServlet";
    /**
     * 修改用户的密码
     */
    public static String get_changepassword= BASE_URL + "/ChangePasswodServlet";
    /**
     * 修改用户的个人信息
     */
    public static String get_changeinformation= BASE_URL + "/UserChangeInformationServlet";
    /**
     * 修改用户的个人信息的手机号码
     */
    public static String get_changephone= BASE_URL + "/UserChangePhoneServlet";
    /**
     * 修改用户的个人信息的地址
     */
    public static String get_changeaddress= BASE_URL + "/UserChangeAdressServlet";
    /**
     * 查看诉求任务不通过的所有信息
     */
    public static String get_taskreviewing= BASE_URL + "/TaskReviewingShowServlet";
}
