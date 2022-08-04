package com.ccy.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.sound.midi.Track;
import java.io.IOException;
import java.io.InputStream;

//帮助类
public class MyBatisUtil {

    private static SqlSessionFactory factory;
    private static final ThreadLocal<SqlSession> local = new ThreadLocal<SqlSession>();
    //得到factory对象
    static {
        try {
            //加载主配置文件，mybatis-config.xml
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            factory = builder.build(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getFactory(){
        return factory;
    }

    //得到sqlsession对象
    public static SqlSession getSqlSession(){
        SqlSession sqlSession = local.get();
        if(sqlSession == null){
            sqlSession = factory.openSession();
            local.set(sqlSession);
        }

        return sqlSession;

    }

    public static <T extends Object>T getMapper(Class<T> c){
        SqlSession sqlSession = getSqlSession();
        return sqlSession.getMapper(c);
    }



}
