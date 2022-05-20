package com.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Mapper
//对数据库操作的接口
public interface UserMapper extends BaseMapper<User>{

    //得到要登录的用户信息
    @Select("select * from smbms_user where userCode=#{userCode} ")
    public User getLoginInfo(String userCode);

    //修改密码
    public int updatePassword(Connection conn, int id, String password) throws Exception;

    //根据用户名 或 角色 查询用户总数

    public int getUserCounts(String username,int userRole) throws Exception;

    //根据条件 查询 获取用户列表 userlist
//    @Select("select u.*,r.roleName as userRoleName from smbms_user u,smbms_role r where u.userRole = r.id" +
//            " and u.username like #{username}"+ " and u.userRole = #{userRole}")
    public List<User> getUserList( String username, int userRole);


    //用户管理模块中的 子模块—— 添加用户
    public abstract int addUser(Connection conn,User user)throws SQLException;

    //用户管理模块中的子模块 —— 删除用户
    public abstract boolean deleteUser(Connection conn,int userId)throws SQLException;

    //根据用户id 查询用户信息
    public abstract User findById(Connection conn,int userId)throws SQLException, Exception;
    //用户管理模块中的子模块 —— 更改用户信息
    public abstract boolean modify(Connection conn,int id,User user)throws SQLException;


}
