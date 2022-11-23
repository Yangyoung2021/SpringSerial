package com.yang.testAdvanced;


import com.yang.config.SpringConfig;
import com.yang.fo.ListPropertiesCopy;
import com.yang.fo.QueryParam;
import com.yang.mapper.UserMapper;
import com.yang.pojo.User;
import com.yang.fo.UserFo;
import com.yang.service.StudentService;
import com.yang.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.Instant;
import java.util.*;
import java.util.function.BiFunction;


@Slf4j
@SpringBootTest(classes = SpringConfig.class)
public class ConnectTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private DataSource dataSource;

    private static final int count = 20000;

    @Test
    public void testConditionSelect() {
        QueryParam queryParam = new QueryParam();
        queryParam.setAddress("南昌");
        queryParam.setName(null);

        List<User> userList = userMapper.selectByCondition(queryParam);

        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    void insetBatchUser() throws Exception {
        HashSet<UserFo> userFos = new HashSet<>();

        for (int i = 0; i < 1000; i++) {
            UserFo userFo = new UserFo();
            userFo.setAge(i + 30);
            userFo.setName(UUID.randomUUID().toString().replace("-", ""));
            userFo.setAddress(UUID.randomUUID().toString().replace("-", ""));
            userFos.add(userFo);
        }

        HashSet<User> users = ListPropertiesCopy.copyProperties(userFos, User.class);

        Instant start = Instant.now();
//        userMapper.insertUserBatch(users);
//        batch(users);

//        batchUpdateOrInsert(users, UserMapper.class, (ele, mapper) -> {
//            mapper.insertOne(ele);
//            return null;
//        });

//        jdbcInsertBatch(users);

        batchInsertSeveralTimes(users, userMapper, (list, mapper) -> mapper.insertUserBatch(list));

        Instant end = Instant.now();
        System.out.println("----------------------------");
//        System.out.println("数据库持久化花费时间" + Duration.between(start,end).getSeconds() + "s");

//        userMapper.insertUserBatch(users);

    }



    public static  <T, U> void  batchInsertSeveralTimes(HashSet<T> elements, U mapper, BiFunction<HashSet<T>, U, Integer> function) {
        long start = System.currentTimeMillis();
        HashSet<T> list = new HashSet<>();
        Integer result = 0;
        for (T ele : elements) {
            list.add(ele);
            if (list.size() >= count) {
                result += function.apply(list, mapper);
                list.clear();
                log.info("插入{}条数据", count);
            }
        }
        if (!CollectionUtils.isEmpty(list)) {
            result += function.apply(list, mapper);
        }
        log.info("共插入{}条数据，花费时间：{} ms", result, (System.currentTimeMillis() - start));
    }

    public void jdbcInsertBatch(List<User> userList) throws Exception {


        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);

        PreparedStatement statement = connection.prepareStatement("insert into test.user(id, name, address, age, create_time) values (?,?,?,?,?)");

        int batchCount = 0;

        for (User user : userList) {
            statement.setString(1,user.getId());
            statement.setString(2,user.getName());
            statement.setString(3,user.getAddress());
            statement.setInt(4,user.getAge());
            statement.setObject(5, user.getCreateTime());
            statement.addBatch();

            batchCount++;

            if(batchCount >= 2000){
                statement.executeBatch();
                connection.commit();
                statement.clearBatch();
                batchCount = 0;
            }
        }

        if(batchCount > 0 ){
            statement.executeBatch();
            connection.commit();
        }

        connection.close();

    }

    /**
     * 每次处理10000条
     */
    private static final int BATCH_SIZE = 100000;



    public void batch(List<User> userList) {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

//        Instant start = Instant.now();
        int batchCount = 0;


        for (User user : userList) {
            mapper.insertOne(user);
            batchCount++;

            if(batchCount >= 2000){
                sqlSession.commit();
                sqlSession.clearCache();
//                System.out.println("提交数量:"+batchCount);
                batchCount = 0;
            }
        }

        if(batchCount > 0 ){
            sqlSession.commit();
        }
        sqlSession.close();
//        Instant end = Instant.now();
//        System.out.println("数据库持久化花费时间" + Duration.between(start,end).getSeconds() + "s");
    }


    @Test
    void insertRollBack() {
        User user = new User();
        user.setId(UUID.randomUUID().toString().substring(3, 19));
        user.setName("王五");
        user.setAddress("深圳");
        user.setAge(19);
        user.setCreateTime(new Date());
        userService.insertOne(user);
    }

    @Test
    void testAnnotationMetadata() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println("获取容器。。。。");
        Arrays.stream(beanDefinitionNames).filter(name -> name.startsWith("custom")).forEach(System.out::println);
    }




}
