package cn.supcon.controller;


import cn.supcon.controller.viewobject.UserVO;
import cn.supcon.error.BusinessException;
import cn.supcon.error.EmBusinessError;
import cn.supcon.response.CommonReturnType;
import cn.supcon.service.UserService;
import cn.supcon.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller("user")
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name = "id") Integer id) throws BusinessException {
        // 调用service服务获取对应id的用户对象的Model模型
        UserModel userModel = userService.getUserById(id);
        // 若获取的对应用户信息不存在
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        // 将核心领域模型用户对象转化为可供UI使用的viewObject
        UserVO userVO = convertFromModel(userModel);
        // 返回通用模型
        return CommonReturnType.create(userVO);
    }

    private UserVO convertFromModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }
}
