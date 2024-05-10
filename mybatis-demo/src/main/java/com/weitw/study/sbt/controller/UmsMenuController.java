package com.weitw.study.sbt.controller;
 
import com.weitw.study.sbt.domain.UmsMenu;
import com.weitw.study.sbt.service.UmsMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
/**
 * TODO your comment
 *
 * @author Yujiaqi
 * @date 2020/12/2 19:20
 */
@RestController
@RequestMapping("/umsMenu")
public class UmsMenuController {
    @Autowired
    private UmsMenuService umsMenuService;
    
    /**
     * 根据ID获取用户信息
     * @Author Sans
     * @CreateTime 2019/6/8 16:34
     * @Param  userId  用户ID
     * @Return UmsMenu 用户实体
     */
    @RequestMapping("/getInfo")
    public UmsMenu getInfo(Integer userId){
        UmsMenu umsMenu = umsMenuService.findById(userId);
        return umsMenu;
    }
//    /**
//     * 查询全部信息
//     * @Author Sans
//     * @CreateTime 2019/6/8 16:35
//     * @Param  userId  用户ID
//     * @Return List<UmsMenu> 用户实体集合
//     */
//    @RequestMapping("/getList")
//    public List<UmsMenu> getList(){
//        List<UmsMenu> UmsMenuList = umsMenuService.list();
//        return UmsMenuList;
//    }
//    /**
//     * 分页查询全部数据
//     * @Author Sans
//     * @CreateTime 2019/6/8 16:37
//     * @Return IPage<UmsMenu> 分页数据
//     */
//    @RequestMapping("/getInfoListPage")
//    public IPage<UmsMenu> getInfoListPage(){
//        //需要在Config配置类中配置分页插件
//        IPage<UmsMenu> page = new Page<>();
//        page.setCurrent(5); //当前页
//        page.setSize(1);    //每页条数
//        page = umsMenuService.page(page);
//        return page;
//    }
//    /**
//     * 根据指定字段查询用户信息集合
//     * @Author Sans
//     * @CreateTime 2019/6/8 16:39
//     * @Return Collection<UmsMenu> 用户实体集合
//     */
//    @RequestMapping("/getListMap")
//    public Collection<UmsMenu> getListMap(){
//        Map<String,Object> map = new HashMap<>();
//        //kay是字段名 value是字段值
//        map.put("age",20);
//        Collection<UmsMenu> UmsMenuList = umsMenuService.listByMap(map);
//        return UmsMenuList;
//    }
//    /**
//     * 新增用户信息
//     * @Author Sans
//     * @CreateTime 2019/6/8 16:40
//     */
//    @RequestMapping("/saveInfo")
//    public void saveInfo(){
//        UmsMenu UmsMenu = new UmsMenu();
//        UmsMenu.setName("小龙");
//        UmsMenu.setTitle("JAVA");
//        umsMenuService.save(UmsMenu);
//    }
//    /**
//     * 批量新增用户信息
//     * @Author Sans
//     * @CreateTime 2019/6/8 16:42
//     */
//    @RequestMapping("/saveInfoList")
//    public void saveInfoList(){
//        //创建对象
//        UmsMenu sans = new UmsMenu();
//        sans.setName("Sans");
//        sans.setTitle("睡觉");
//        UmsMenu papyrus = new UmsMenu();
//        papyrus.setName("papyrus");
//        papyrus.setTitle("JAVA");
//        //批量保存
//        List<UmsMenu> list =new ArrayList<>();
//        list.add(sans);
//        list.add(papyrus);
//        umsMenuService.saveBatch(list);
//    }
//    /**
//     * 更新用户信息
//     * @Author Sans
//     * @CreateTime 2019/6/8 16:47
//     */
//    @RequestMapping("/updateInfo")
//    public void updateInfo(Integer id){
//        //根据实体中的ID去更新,其他字段如果值为null则不会更新该字段,参考yml配置文件
//        UmsMenu UmsMenu = new UmsMenu();
//        UmsMenu.setId(id);
//        UmsMenu.setLevel(19);
//        umsMenuService.updateById(UmsMenu);
//    }
//    /**
//     * 新增或者更新用户信息
//     * @Author Sans
//     * @CreateTime 2019/6/8 16:50
//     */
//    @RequestMapping("/saveOrUpdateInfo")
//    public void saveOrUpdate(Integer id){
//        //传入的实体类UmsMenu中ID为null就会新增(ID自增)
//        //实体类ID值存在,如果数据库存在ID就会更新,如果不存在就会新增
//        UmsMenu UmsMenu = new UmsMenu();
//        UmsMenu.setId(id);
//        UmsMenu.setName("哈哈哈哈");
//        umsMenuService.saveOrUpdate(UmsMenu);
//    }
//    /**
//     * 根据ID删除用户信息
//     * @Author Sans
//     * @CreateTime 2019/6/8 16:52
//     */
//    @RequestMapping("/deleteInfo")
//    public void deleteInfo(String userId){
//        umsMenuService.removeById(userId);
//    }
//    /**
//     * 根据ID批量删除用户信息
//     * @Author Sans
//     * @CreateTime 2019/6/8 16:55
//     */
//    @RequestMapping("/deleteInfoList")
//    public void deleteInfoList(){
//        List<String> userIdlist = new ArrayList<>();
//        userIdlist.add("12");
//        userIdlist.add("13");
//        umsMenuService.removeByIds(userIdlist);
//    }
//    /**
//     * 根据指定字段删除用户信息
//     * @Author Sans
//     * @CreateTime 2019/6/8 16:57
//     */
//    @RequestMapping("/deleteInfoMap")
//    public void deleteInfoMap(){
//        //kay是字段名 value是字段值
//        Map<String,Object> map = new HashMap<>();
//        map.put("skill","删除");
//        map.put("fraction",10L);
//        umsMenuService.removeByMap(map);
//    }
}