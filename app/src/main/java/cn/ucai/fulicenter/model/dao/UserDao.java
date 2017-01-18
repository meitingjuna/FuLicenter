package cn.ucai.fulicenter.model.dao;


import cn.ucai.fulicenter.application.FuLiCenterApplication;
import cn.ucai.fulicenter.model.bean.User;

/**
 * Created by LCH on 2017/1/17.
 */

public class UserDao {
    public static final String USER_TABLE_NAME = "t_fulicenter_user";
    public static final String USER_COLUME_NAME = "m_user_name";
    public static final String USER_COLUME_NICK = "m_user_nick";
    public static final String USER_COLUME_AVATAR = "m_user_avatar_id";
    public static final String USER_COLUME_AVATAR_PATH = "m_user_avatar_path";
    public static final String USER_COLUME_AVATAR_SUFFIX = "m_user_avatar_suffix";
    public static final String USER_COLUME_AVATAR_TYPE = "m_user_avatar_type";
    public static final String USER_COLUME_AVATAR_UPDATE_TIME = "m_user_update_time";

    private static UserDao instance;

    public UserDao() {
        DBManager.onInit(FuLiCenterApplication.getInstance());
    }

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    public boolean savaUser(User user) {
        return DBManager.getInstance().saveUser(user);
    }

    public User getUser(String userName) {
        return DBManager.getInstance().getUser(userName);
    }
}
