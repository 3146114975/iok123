package com.boot.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("smbms_user")
public class User {
    private Integer id;
    @TableField("userCode")
    private String userCode;    //用户编码
    private String userName;
    private String userPassword;
    private Integer gender;
    private Date birthday;
    private String phone;
    private String address;
    private Integer userRole;
    private Integer createdBy;  //创建者
    @TableField("creationDate")
    private Date createDate;
    private Integer modifyBy;   //更新者
    private Date modifyDate;    //更新时间

    @TableField(exist = false)  // 表示这个属性不存在，当数据库查询时
    private Integer age;    //年龄
    @TableField(exist = false)  // 表示这个属性不存在，当数据库查询时
    private String userRoleName;    //用户角色名称

}
