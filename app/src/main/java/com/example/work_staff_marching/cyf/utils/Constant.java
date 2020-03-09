package com.example.work_staff_marching.cyf.utils;

//请求对应
public class Constant {
    // 172.20.10.5 无线地址   Staff_Work_Marching服务器
    public static String BASE_URL = "http://192.168.4.100:8080/Staff_Work_Marching";
    /**
     * 获取验证码,类似前后台交接的接口
     */
    public static String get_code = BASE_URL + "/AddIdentifyCodeServlet";
    public static String get_code1 = BASE_URL + "/IndentifyCodeServlet";
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
     * 增加诉求任务的权重信息
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
     * 特定诉求任务查看
     */
    public static String get_showtask = BASE_URL + "/ShowTaskServlet";
    public static String get_showtaskl = BASE_URL + "/ShowTasklServlet";
    /**
     * 诉求任务状态的修改，审核
     */
    public static String get_taskaudit = BASE_URL + "/UpdateAduitTaskStatusServlet";
    /**
     * 诉求任务的查看
     */
    public static String get_admintaskproportion = BASE_URL + "/ShowTaskProportionServlet";
    /**
     * 修改诉求任务的权重
     */
    public static String get_adminupdatetaskproportion = BASE_URL + "/AdminTaskProportionServlet";
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
    /**
     * 显示未匹配的诉求任务
     */
    public static String get_showmarchingtask= BASE_URL + "/MarchingTaskShowServlet";
    /**
     * 显示工作人员的诉求任务的匹配信息(包括办理中，办理结束）
     */
    public static String get_showmarching= BASE_URL + "/MarchigShowServlet";
    /**
     * 添加工作人员的办理记录
     */
    public static String get_addrecord= BASE_URL + "/AddRecordServlet";
    /**
     * 查看工作人员的办理记录
     */
    public static String get_showrecord= BASE_URL + "/ShowRecordServlet";
    /**
     * 工作人员的办理结束
     */
    public static String get_recordend= BASE_URL + "/UpdateTaskStatusServlet";
    /**
     * 普通用户查看自己的诉求任务
     */
    public static String get_usertaskshow= BASE_URL + "/UserTaskShowServlet";
    /**
     * 诉求任务的评价查看
     */
    public static String get_estimateshow= BASE_URL + "/ShowEstimateServlet";
    /**
     * 工作人员的信息查看
     */
    public static String get_workuserinformationshow= BASE_URL + "/WorkUserInformationShow";
    /**
     * 上访人员信息的查看
     */
    public static String get_userinformationshow= BASE_URL + "/UserInformationShowServlet";
    /**
     * 工作人员处理的诉求任务数量
     */
    public static String get_calculatetasks= BASE_URL + "/CalculateTasksServlet";
    /**
     *查询匹配成功的诉求任务
     */
    public static String get_marchedshow= BASE_URL + "/MarchedShowServlet";
    /**
     *查询管理员的信息
     */
    public static String get_adminshow= BASE_URL + "/ShowAdminServlet";
    /**
     *查询匹配信息的上访人信息
     */
    public static String ShowMarchedUserInformationServlet= BASE_URL + "/ShowMarchedUserInformationServlet";
    /**
     *匹配诉求任务和工作人员
     */
    public static String CalculatePorprotionServlet= BASE_URL + "/CalculatePorprotionServlet";
    /**
     *查询匹配的工作人员信息
     */
    public static String ShowTaskWorkUserServlet= BASE_URL + "/ShowTaskWorkUserServlet";
}
