package com.company.pms.pmsbase.utils;

/**
 * @Author zcf
 * @Create 2018/8/1 13:40
 * @Desc 用于导出信息到excel文件时各属性显示顺序
 */
public class FieldUtils {
    public static final String[][] EMPLOYEE_FIELDS = {{"id", "ID"}, {"name", "姓名"}, {"gender", "性别", "男", "女"},
            {"age", "年龄"}, {"phone", "联系电话"}, {"password", "登录密码"}, {"position", "职位"}, {"performance", "出勤率"},
            {"entityType", "实体类型"}, {"dateCreated", "入职时间"}, {"dateModified", "修改时间"}, {"deleted", "状态", "离职", "工作中"}};

    public static final String[][] CUSTOMER_FIELDS = {{"id", "ID"}, {"name", "姓名"}, {"gender", "性别", "男", "女"}, {"age", "年龄"},
            {"phone1", "联系电话1"}, {"phone2", "联系电话2"}, {"address", "联系地址"}, {"password", "登录密码"}, {"entityType", "实体类型"},
            {"dateCreated", "录入时间"}, {"dateModified", "修改时间"}, {"deleted", "状态", "已删除", "使用中"}};

    public static final String[][] CARBARN_FIELDS = {{"id", "ID"}, {"address", "所在位置"}, {"area", "面积"}, {"price", "价格"},
            {"state", "状态", "空闲", "使用中"}, {"introduce", "车库简介"}, {"entityType", "实体类型"},
            {"dateCreated", "开放时间"}, {"dateModified", "修改时间"}, {"deleted", "状态", "废弃", "可用"}};

    public static final String[][] CHARGE_FIELDS = {{"id", "ID"}, {"consumOfElectric", "用电量"}, {"chargeOfElectric", "已缴电费"},
            {"consumOfWater", "用水量"}, {"chargeOfWater", "已缴水费"},{"chargeOfProperty", "已缴物业费"},
            {"state", "状态", "已缴", "欠费"}, {"houseId", "房屋ID"}, {"entityType", "实体类型"},
            {"dateCreated", "缴费时间"}, {"dateModified", "修改时间"}, {"deleted", "状态", "无效", "有效"}};

    public static final String[][] COMPLAIN_FIELDS = {{"id", "ID"}, {"employeeId", "投诉员工"}, {"deviceId", "相关设备"},
            {"customerId", "来自客户"}, {"reason", "投诉原因"}, {"entityType", "实体类型"},
            {"dateCreated", "投诉时间"}, {"dateModified", "修改时间"}, {"deleted", "状态", "撤诉", "投诉中"}};

    public static final String[][] DEVICE_FIELDS = {{"id", "ID"}, {"name", "设备名"}, {"address", "所在位置"},
            {"state", "状态", "异常", "正常"}, {"houseId", "所在房屋"}, {"employeeId", "负责员工"}, {"entityType", "实体类型"},
            {"dateCreated", "投入时间"}, {"dateModified", "检修时间"}, {"deleted", "状态", "废弃", "使用中"}};

    public static final String[][] HOUSE_FIELDS = {{"id", "ID"}, {"address", "所在地址"}, {"area", "面积"}, {"price", "价格"},
            {"state", "状态", "空闲", "租用中"}, {"introduce", "房屋简介"}, {"customerId", "用户ID"},{"entityType", "实体类型"},
            {"dateCreated", "开放时间"}, {"dateModified", "修改时间"}, {"deleted", "状态", "废弃", "可用"}};
}
