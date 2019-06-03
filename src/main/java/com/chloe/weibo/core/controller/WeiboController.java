package com.chloe.weibo.core.controller;

import com.chloe.weibo.core.service.interfaces.UserService;
import com.chloe.weibo.pojo.data.Result;
import com.chloe.weibo.core.service.interfaces.MainFunctionService;
import com.chloe.weibo.core.service.interfaces.WeiboService;
import com.chloe.weibo.common.utils.ResultUtil;
import com.chloe.weibo.pojo.vo.UserVo;
import com.chloe.weibo.pojo.vo.WeiboVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
public class WeiboController {

    @Autowired
    private MainFunctionService mainFunctionService;
    @Autowired
    private WeiboService weiboService;
    @Autowired
    private UserService userService;


    /**
     * 发送微博
     * 参数：userId,message
     * @param weiboVo
     * @return
     */
    @PostMapping(value = "/add-weibo")
    public Result addWeibo(@RequestBody WeiboVo weiboVo) throws ParseException {
        Result result= mainFunctionService.sendWeiboVo(weiboVo);
        return result;
    }

    /**
     * 修改微博
     * 参数：weiboId,message
     * @param weiboVo
     * @return
     */
    @PostMapping(value = "/update-weibo")
    public Result updateWeibo(@RequestBody WeiboVo weiboVo) throws ParseException {
        Result result= mainFunctionService.updateWeiboVo(weiboVo);
        return result;
    }

    /**
     * 通过微博id获取一条微博信息
     * 参数：weiboId
     * @param weiboId
     * @return
     */
    @GetMapping(value = "/get-one-weibo")
    public Result getOneWeibo(Integer userId,Integer weiboId){
        Result<WeiboVo> result=new Result<>();
        WeiboVo weiboVo= mainFunctionService.getOneWeiboVoByWeiboId(userId,weiboId);
        if(weiboVo!=null){
            return  ResultUtil.success(weiboVo);
        }else {
            return ResultUtil.error("没有找到该微博信息");
        }
    }


    /**
     * 通过微博id获取一条微博的内容
     * @param weiboId
     * @return
     */
    @GetMapping(value = "/get-one-weibo-content")
    public Result getOneWeiboContent(Integer weiboId){
        Result result= mainFunctionService.getWeiboContentByWeiboId(weiboId);
            return result;
    }

    /**
     * 通过用户id获取自己及所有关注人的微博内容
     * @param userId
     * @return
     */
    @GetMapping(value = "/get-all-weibo-of-all-following-all-users")
    public Result getAllWeiboOfFollowingAllUsers(Integer userId){
        Result result= mainFunctionService.getAllWeiboVoListByUserId(userId);
        return result;
    }

    /**
     * 通过用户id获取该用户的所有微博内容
     * @param userId
     * @return
     */
    @GetMapping(value = "/get-one-user-all-weibo")
    public Result getOneUserAllWeibo(Integer userId){
        Result result= mainFunctionService.getOwnWeiboVoListByUserId(userId);
        return result;
    }

    /**
     * 通过微博id删除某一条微博
     * 参数：weiboId
     * @param weiboId
     * @return
     */
    @PostMapping(value = "/delete-weibo")
    public Result deleteWeibo(@RequestParam("weiboId") int weiboId) {
        if (mainFunctionService.deleteWeiboVo(weiboId)){
            return ResultUtil.success("删除微博成功");
        }else {
            return ResultUtil.error("删除微博失败");
        }
    }

    /**
     * 转发一条微博
     * 参数：weiboId,userId,message
     * @param weiboVo
     * @return
     */
    @PostMapping(value = "/forwarding-weibo")
    public Result forwardingWeibo(@RequestBody WeiboVo weiboVo) {
        int weiboId=weiboVo.getWeiboId();
        weiboVo.setWeiboId(null);
        Result result= mainFunctionService.ForwardingWeiboVo(weiboVo,weiboId);

        return result;
    }

    /**
     * 分页获取用户主页的一页微博内容
     * userId,pageNum
     * @param userId
     * @param pageNum
     * @return
     */
    @GetMapping("/get-weibo-of-user-home-page")
    public Result getWeiboOfUserHomePage(@RequestParam("userId") int userId,@RequestParam("pageNum") Integer pageNum) {
        Result result= mainFunctionService.getOwnWeiboVoPageByUserId(userId,pageNum);
        return result;
    }

    /**
     * 分页获取自己及所有关注人的一页微博内容
     * @param userId
     * @param pageNum
     * @return
     */
    @GetMapping("/get-all-weibo-of-home-page")
    public Result getAllWeiboOfHomePage(@RequestParam("userId")int userId,@RequestParam("pageNum") Integer pageNum) {
        Result result= mainFunctionService.getAllWeiboVoPageByUserId(userId,pageNum);
        return result;
    }

    /**
     * 转发微博时显示在转发微博页面内容
     * @param weiboId
     * @return
     */
    @GetMapping("/get-weibo-message")
    public Result getWeiboMessage(int weiboId) {

        Result result= mainFunctionService.getForwardingOrignalWeiboContent(weiboId);

        return result;
    }

    /**
     * 查询微博是否被修改
     * @param weiboId
     * @return
     */
    @GetMapping("/check-weibo-isModify")
    public Result checkWeiboIsModify(int weiboId) {

        Result result= weiboService.checkWeiboIsModify(weiboId);
        return result;

    }

    /**
     * 分页获取自己点赞微博内容
     * @param userId
     * @param pageNum
     * @return
     */
    @GetMapping("/get-like-weibo-by-page")
    public Result getLikeWeiboByPage(@RequestParam("userId")int userId,@RequestParam("pageNum") Integer pageNum) {
        Result result= mainFunctionService.getAllWeiboVoPageByUserId(userId,pageNum);
        return result;
    }

    /**
     * 模糊搜索微博内容
     * @return
     */
    @GetMapping("/search-weibo")
    public Result SearchWeibo(@RequestParam("userId")int userId,@RequestParam("weiboContent")String weiboContent,@RequestParam("pageNum") Integer pageNum) {
        return weiboService.getSearchWeiboVoList(userId,weiboContent,pageNum);
    }

    /**
     * 查询热门微博内容
     * @return
     */
    @GetMapping("/get-hot-weibo")
    public Result GetHotWeibo(@RequestParam("userId")int userId,@RequestParam("pageNum") Integer pageNum) {
        return weiboService.getHotWeiboVoList(userId,pageNum);
    }

    /**
     * 获取某一用户的点赞微博
     * @param userId
     * @param pageNum
     * @return
     */
    @GetMapping(value = "/get-like-weibo")
    public Result getLikeWeibo(@RequestParam("userId") int userId,@RequestParam("pageNum")int pageNum) {
        return ResultUtil.success(weiboService.getLikeWeiboVoList(userId,pageNum));
    }

    /**
     * 获取某一用户的收藏微博
     * @param userId
     * @param pageNum
     * @return
     */
    @GetMapping(value = "/get-collection-weibo-1")
    public Result getCollectiondWeibo(@RequestParam("userId") int userId,@RequestParam("pageNum")int pageNum) {
        return ResultUtil.success(weiboService.getCollectionWeiboVoList(userId,pageNum));
    }



}
