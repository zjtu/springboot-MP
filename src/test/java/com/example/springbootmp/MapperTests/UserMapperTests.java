package com.example.springbootmp.MapperTests;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootmp.entity.User;
import com.example.springbootmp.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTests {

    @Autowired
    private UserMapper userMapper;


    /**
     * 单元测试方法：可以单独独立运行，不需要启动整个项目，提升了代码的测试效率
     * 1.单元测试类必须被@Test注解修饰
     * 2.返回类型必须是void
     * 3.方法的参数列表不指定任何类型
     * 4.方法的访问修饰符必须是public
     */
    /**
     * 查询操作
     */
    @Test
    public void testSelect() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users.toString());
    }

    /**
     * 根据id查询
     */
    @Test
    public void testFindById() {
        User user = userMapper.selectById(21);
        System.out.println(user);
    }

    /**
     * 插入操作
     */
    @Test
    public void testInsert() {
        User user = new User();
        user.setUsername("曹操");
        user.setPassword("123456");
        user.setNickname("西楚霸王");
        user.setAddress("三国");
        user.setEmail("2688123@qq.com");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse("1991-01-01 00:00:00", dateTimeFormatter);
        user.setBirthday(localDateTime);
        int i = userMapper.insert(user);
        System.out.println(user.getId());
    }



    /**
     * 更新操作
     */
    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(21);
        user.setNickname("系统管理员");
        user.setEmail("admin@qq.com");
        user.setAddress("衡水");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse("2022-02-02 00:00:00", dateTimeFormatter);
        user.setBirthday(localDateTime);
        int i = userMapper.updateById(user);
        System.out.println(i);
        System.out.println(user);
    }

    /**
     * 根据条件更新
     */
    @Test
    public void testUpdate2() {
        User user = new User();
        user.setPassword("654321");
        user.setEmail("123456@qq.com");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", "hhhh1");
        int i = userMapper.update(user, queryWrapper);
        System.out.println(i);
    }

    /**
     * 根据用户名修改年龄
     */
    @Test
    public void updateAgeByUsername(){
        userMapper.updateAgeByUsername(100,"admin");
    }

    /**
     * 根据条件更新，可以更新为null值
     */
    @Test
    public void testUpdate3() {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("nick_name", "普通用户").set("email", null);
        int i = userMapper.update(null, updateWrapper);
        System.out.println(i);
    }

    /**
     * 根据条件更新，更新多个属性值
     */
    @Test
    public void testUpdate4() {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("nick_name", "管理员").set("address", "水帘洞").set("email", null);
        int i = userMapper.update(null, updateWrapper);
        System.out.println(i);
    }

    /**
     * 根据id删除一条记录
     */
    @Test
    public void testDeleteById() {
        int result = userMapper.deleteById(37);
        System.out.println(result);
    }

    /**
     * 根据entity删除
     */
    @Test
    public void testDelete() {
        //通过对象设置删除条件
//        User user = new User();
//        user.setUsername("admin");
//        user.setPassword("123456");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //手动设置删除条件
        queryWrapper.eq("username", "sky");
        queryWrapper.eq("password", "654321");
        //根据条件删除
        int i = userMapper.delete(queryWrapper);
        System.out.println(i);
    }

    /**
     * 根据id批量删除
     */
    @Test
    public void testDelete2() {
        int i = userMapper.deleteBatchIds(Arrays.asList(22L, 23L, 24L));
        System.out.println(i);
    }

    /**
     * 根据id查询
     */
    @Test
    public void testSelectById() {
        User user = userMapper.selectById(37);
        System.out.println("result=" + user);
    }

    /**
     * 根据id批量查询
     */
    @Test
    public void testSelectBatchIds() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(25L, 26L, 27L));
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 根据entity查询一条记录
     * 注意:如果查询结果为多条记录则报错（TooManyResultsException)
     */
    @Test
    public void testSelectOne() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", "admin");
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }

    /**
     * 根据wrapper条件查询总记录条数
     */
    @Test
    public void testSelectCount() {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.gt("age", 18);//年龄大于18岁
        Long count = userMapper.selectCount(wrapper);
        System.out.println(count);
    }

    /**
     * 根据entity条件查询全部记录
     */
    @Test
    public void testSelectList() {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.gt("age", 18);//年龄大于18岁
        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println("user=" + user);
        }
    }

    /**
     * 根据entity条件查询全部记录并翻页
     */
    @Test
    public void testSelectPage() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 18);
        int pageIndex = 1; //当前页
        int size = 3; //每页条数
        Page<User> page = new Page<>(pageIndex, size);
        IPage<User> userIPage = userMapper.selectPage(page, queryWrapper);
        long pages = userIPage.getPages(); //总页数
        long total = userIPage.getTotal();//总记录数
        System.out.println("数据总条数" + total);
        System.out.println("总页数" + pages);
        List<User> records = userIPage.getRecords();
        //记录列表
        System.out.println("记录列表" + records);
    }

    /**
     * wrapper基本比较操作
     * eq 等于=
     * ne 不等于<>
     * gt 大于>
     * ge 大于等于>=
     * lt 小于<
     * le 小于等于<=
     * between BETWEEN 值1 AND 值2
     * notBetween NOT BETWEEN 值1 AND 值2
     * in 字段 in (value.get(0),value.get(1),...)
     * NOT IN(v0 ,v1,...)
     */
    @Test
    public void testEqCp() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //select id,username,password,nick_name,address,email,birthday,age from user where password= ? AND age>= 20and username IN (?,?,?)
        //eq:等于
        queryWrapper.eq("password", "123456")
                //ge:大于等于
                .ge("age", 20)
                //in:字段in(value.get(0)
                .in("username", "曹操", "马超", "刘邦");

        List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testLambdaEqCp() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //使用lambda表达式
        //SELECT id,BIRTHDAY,PASSWORD,ADDRESS,nick_name,AGE,EMAIL,USERNAME FROM user WHERE (PASSWORD = ? AND AGE >= ? AND USERNAME IN (?,?,?))
        lambdaQueryWrapper.eq(User::getPassword, "123456")
                .ge(User::getAge, 20)
                .in(User::getUsername, "马超", "曹操", "刘邦");
        List<User> users = userMapper.selectList(lambdaQueryWrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testEq3() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        String username = null;
        Integer age = 20;
        //SELECT id,BIRTHDAY,PASSWORD,ADDRESS,nick_name,AGE,EMAIL,USERNAME FROM user WHERE (AGE > ?)
        lambdaQueryWrapper.eq(username != null && !username.equals(""), User::getUsername, username);
        lambdaQueryWrapper.gt(age != null, User::getAge, age);
        List<User> users = userMapper.selectList(lambdaQueryWrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 模糊查询
     * like      username like  '%值%'
     * not like  username not like  '%值%'
     * likeLeft  username like  '%曹'
     * likeRight username like  '曹%'
     */
    @Test
    public void testWrapper() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //SELECT id,username,birthday FROM user WHERE (username LIKE '曹%')
        wrapper.likeRight("username", "曹");
        //通过select方法指定查询字段
        wrapper.select("id", "username", "birthday");
        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     *将testWrapper()改为lambda表达式
     */
    @Test
    public void testLamWrapper() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //SELECT id,USERNAME,BIRTHDAY FROM user WHERE (USERNAME LIKE ? OR AGE = ?)
        lambdaQueryWrapper.likeRight(User::getUsername, "曹操")
                .or()
                .eq(User::getAge, 27)
                //通过select方法指定查询字段
                .select(User::getId, User::getUsername, User::getBirthday);
        List<User> users = userMapper.selectList(lambdaQueryWrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 逻辑查询
     * 拼接 or
     * AND 嵌套
     */
    @Test
    public void testOr() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // SELECT id,BIRTHDAY,PASSWORD,ADDRESS,nick_name,AGE,EMAIL,USERNAME FROM user WHERE (username = ? OR age = ?)
        queryWrapper.eq("username", "刘备").or().eq("age", 28);
        //SELECT id,BIRTHDAY,PASSWORD,ADDRESS,nick_name,AGE,EMAIL,USERNAME FROM user WHERE (username = ? AND age = ?)
        queryWrapper.eq("username", "刘备").eq("age", 27);
        List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * testOr()方法改为lambda表达式
     */
    @Test
    public void testLamOr() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        //SELECT id,BIRTHDAY,PASSWORD,ADDRESS,nick_name,AGE,EMAIL,USERNAME FROM user WHERE (USERNAME = ? OR AGE = ?)
        //wrapper.eq(User::getUsername,"刘备").or().eq(User::getAge,28);
        wrapper.eq(User::getUsername, "刘备").eq(User::getAge, 27);
        //SELECT id,BIRTHDAY,PASSWORD,ADDRESS,nick_name,AGE,EMAIL,USERNAME FROM user WHERE (USERNAME = ? AND AGE = ?)
        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * orderByDesc：降序排序
     */
    @Test
    public void testOrderByDesc(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //查询结果按年龄降序
        //SELECT id,BIRTHDAY,PASSWORD,ADDRESS,nick_name,AGE,EMAIL,USERNAME FROM user ORDER BY age DESC
        wrapper.orderByDesc("age");
        List<User> users = userMapper.selectList(wrapper);
        for(User user: users){
            System.out.println(user);
        }
    }

    /**
     * orderByAsc:升序排序
     */
    @Test
    public void testOrderByAsc(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //查询结果按年龄降序
        //SELECT id,BIRTHDAY,PASSWORD,ADDRESS,nick_name,AGE,EMAIL,USERNAME FROM user ORDER BY age ASC
        wrapper.orderByAsc("age");
        List<User> users = userMapper.selectList(wrapper);
        for(User user: users){
            System.out.println(user);
        }
    }

    @Test
    public void testLamOrderByAsc(){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //查询结果按年龄降序
        //SELECT id,BIRTHDAY,PASSWORD,ADDRESS,nick_name,AGE,EMAIL,USERNAME FROM user ORDER BY AGE ASC
        lambdaQueryWrapper.orderByAsc(User::getAge);
        List<User> users = userMapper.selectList(lambdaQueryWrapper);
        for(User user: users){
            System.out.println(user);
        }
    }

}
