package com.mmall.controller.backend;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.ICategoryService;
import com.mmall.service.IUserService;
import com.mmall.util.CookieUtil;
import com.mmall.util.JsonUtil;
import com.mmall.util.RedisPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/manage/category")
public class CategoryManageController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private ICategoryService iCategoryService;

    @RequestMapping(value = "/add_category.do")
    @ResponseBody
    public ServerResponse addCategory(HttpServletRequest httpServletRequest, String categoryName, @RequestParam(value = "parentId",defaultValue = "0") int parentId) {
//        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
//        if (StringUtils.isEmpty(loginToken)) {
//            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisPoolUtil.get(loginToken);
//        User user = JsonUtil.String2Obj(userJsonStr,User.class);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录!");
//        }
//        //校验一下是否是管理员身份
//        ServerResponse response = iUserService.checkAdminRole(user);
//        if (response.isSuccess()) {
//            //是管理员
//            // 增加我们处理分类的逻辑
//            return iCategoryService.addCategory(categoryName,parentId);
//        }else {
//            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
//        }

        //二期修改：全部使用拦截器对登录权限进行校验
        return iCategoryService.addCategory(categoryName,parentId);
    }

    @RequestMapping(value = "/set_category_name.do")
    @ResponseBody
    public ServerResponse SetCategoryName(HttpServletRequest httpServletRequest, Integer categoryId, String categoryName) {
//        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
//        if (StringUtils.isEmpty(loginToken)) {
//            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisPoolUtil.get(loginToken);
//        User user = JsonUtil.String2Obj(userJsonStr,User.class);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录!");
//        }
//        //校验一下是否是管理员身份
//        ServerResponse response = iUserService.checkAdminRole(user);
//        if (response.isSuccess()) {
//            //是管理员
//            //增加修改品类名字的逻辑
//            return iCategoryService.updateCategoryName(categoryId,categoryName);
//        }else {
//            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
//        }

        //二期修改：全部使用拦截器对登录权限进行校验
        return iCategoryService.updateCategoryName(categoryId,categoryName);
    }

    @RequestMapping(value = "/get_category.do")
    @ResponseBody
    //获取品类子节点(平级)
    public ServerResponse getChildrenParallelCategory(HttpServletRequest httpServletRequest,@RequestParam(value = "categoryId",defaultValue = "0") Integer categoryId) {
//        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
//        if (StringUtils.isEmpty(loginToken)) {
//            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisPoolUtil.get(loginToken);
//        User user = JsonUtil.String2Obj(userJsonStr,User.class);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录!");
//        }
//        //校验一下是否是管理员身份
//        ServerResponse response = iUserService.checkAdminRole(user);
//        if (response.isSuccess()) {
//            //是管理员
//            //查询子节点的category信息，并且不递归，保持平级
//            return iCategoryService.getChildrenParallelCategory(categoryId);
//        }else {
//            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
//        }

        //二期修改：全部使用拦截器对登录权限进行校验
        return iCategoryService.getChildrenParallelCategory(categoryId);
    }

    @RequestMapping(value = "/get_deep_category.do")
    @ResponseBody
    //获取当前分类id及递归子节点categoryId
    public ServerResponse getChildrenAndDeepChildrenCategory(HttpServletRequest httpServletRequest,@RequestParam(value = "categoryId",defaultValue = "0") Integer categoryId) {
//        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
//        if (StringUtils.isEmpty(loginToken)) {
//            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisPoolUtil.get(loginToken);
//        User user = JsonUtil.String2Obj(userJsonStr,User.class);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录!");
//        }
//        //校验一下是否是管理员身份
//        ServerResponse response = iUserService.checkAdminRole(user);
//        if (response.isSuccess()) {
//            //是管理员
//            //获取当前分类id及递归子节点categoryId
//            return iCategoryService.selectCategoryAndChildrenById(categoryId);
//        }else {
//            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
//        }

        //二期修改：全部使用拦截器对登录权限进行校验
        return iCategoryService.selectCategoryAndChildrenById(categoryId);
    }
}
