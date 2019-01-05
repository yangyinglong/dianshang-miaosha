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

import javax.servlet.http.HttpServletRequest;
import java.util.Random;


@Controller("user")
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;


    // 用户获取opt短信借口
    @RequestMapping("/getotp")
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name = "telphone") String telphone) {
        // 按照一定的规则生成OTP验证码
        Random random = new Random();
        int randomInt = random.nextInt(99999);
        randomInt += 10000;
        String otpCode = String.valueOf(randomInt);

        // 将OTP验证码同对应用户的手机号关联
        httpServletRequest.getSession().setAttribute(telphone, otpCode);

        System.out.println("telphone = " + telphone + " & otpCode = " + otpCode);

        return CommonReturnType.create(null);


    }

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
