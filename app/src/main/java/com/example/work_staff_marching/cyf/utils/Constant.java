package com.example.work_staff_marching.cyf.utils;

//请求对应
public class Constant {
    // 172.20.10.5 无线地址   Staff_Work_Marching服务器
    public static String BASE_URL = "http://192.168.9.105:8080/Staff_Work_Marching";
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
    public static String get_userlogin = BASE_URL + "/UserLoginServlet";
    /**
     * 网上上访
     */
    public static String get_taskadd = BASE_URL + "/TaskAddServlet";
    /**
     * 诉求任务权重表
     */
    public static String get_taskproportion = BASE_URL + "/UserTaskProportionServlet";
    /**
     * 修改用户的密码
     */
    public static String get_changepassword = BASE_URL + "/ChangePasswodServlet";
    /**
     * 修改用户的个人信息
     */
    public static String get_changeinformation = BASE_URL + "/UserChangeInformationServlet";
    /**
     * 修改用户的个人信息的手机号码
     */
    public static String get_changephone = BASE_URL + "/UserChangePhoneServlet";
    /**
     * 修改用户的个人信息的地址
     */
    public static String get_changeaddress = BASE_URL + "/UserChangeAdressServlet";
    /**
     * 查看诉求任务的所有信息
     */
    public static String get_taskreviewing = BASE_URL + "/TaskReviewingShowServlet";
    /**
     * 删除诉求任务
     */
    public static String get_taskdelete = BASE_URL + "/TaskDeleteServlet";
    /**
     * 修改诉求任务
     */
    public static String get_taskupdate = BASE_URL + "/TaskUpdateServlet";
    /**
     * 诉求任务评价
     */
    public static String get_estimateadd = BASE_URL + "/EstimateAddServlet";
    /**
     * 查询工作用户的姓名
     */
    public static String get_username = BASE_URL + "/UserNameShowServlet";
    /**
     * 查询用户信息
     */
    public static String get_user = BASE_URL + "/UserShowServlet";
    /**
     * 审核通过用户信息
     */
    public static String get_useraudit = BASE_URL + "/UserAuditServlet";
    /**
     * 审核不通过用户信息
     */
    public static String get_userauditfailure = BASE_URL + "/UserAuditFaillureServlet";
    /**
     * 增加工作人员评价指标
     */
    public static String get_addworkuserevaluatingindicator = BASE_URL + "/AddWorkUserEvaluatingIndicatorServlet";
    /**
     * 查看工作人员评价指标
     */
    public static String get_showworkuserevaluatingindicator = BASE_URL + "/ShowWorkUserEvaluatingIndicatorServlet";
    /**
     * 删除工作人员评价指标
     */
    public static String get_deleteworkuserevaluatingindicator = BASE_URL + "/DeleteWorkUserEvaluatingIndicatorServlet";
    /**
     * 修改工作人员评价指标
     */
    public static String get_updateworkuserevaluatingindicator = BASE_URL + "/UpdateWorkUserEvaluatingIndicatorServlet";
    /**
     * 查看所有工作人员评价指标
     */
    public static String get_showallworkuserevaluatingindicator = BASE_URL + "/ShowAllWorkUserEvaluatingIndicatorServlet";
    /**
     * 查看工作用户的基本信息
     */
    public static String get_showuserinformation= BASE_URL + "/ShowUserInformationServlet";
}
