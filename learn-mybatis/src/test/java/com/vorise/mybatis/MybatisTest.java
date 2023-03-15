package com.vorise.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.vorise.mybatis.entity.User;
import com.vorise.mybatis.mapper.UserMapper;

/**
 * mybatis不依赖spring, 原生方式使用
 *
 * https://cloud.tencent.com/developer/article/1772213
 */
public class MybatisTest {

    @Test
    public void test() throws IOException, ClassNotFoundException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        SqlSession sqlSession = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 读取mybatis-config.xml
            inputStream = Resources.getResourceAsStream(resource);
            // 解析mybatis-config.xml配置文件，创建sqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            // 创建sqlSession
            sqlSession = sqlSessionFactory.openSession();
            // 创建userMapper对象（UserMapper并没有实现类）
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 调用userMapper对象的方法
            User user = userMapper.selectById(1);
            System.out.println(user.getName());
        } finally {
            // 关闭资源
            if (inputStream != null) {
                inputStream.close();
            }
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
