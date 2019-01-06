package cn.supcon.service;

import cn.supcon.error.BusinessException;
import cn.supcon.service.model.UserModel;


public interface UserService {

    public UserModel getUserById(Integer id);
    public void register(UserModel userModel) throws BusinessException;
}
