package com.ccy.dao;

import com.ccy.pojo.Student;
import com.ccy.utils.MyBatisUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import javax.annotation.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class StudentDAOTest {

    @org.junit.Test
    public void insertStudent() {



            SqlSession sqlSession = MyBatisUtil.getSqlSession();
            StudentDAO studentDAO = sqlSession.getMapper(StudentDAO.class);

            //System.out.println(studentDAO);
            Student student = new Student(0,"10005","Lily","女",27);
            int i = studentDAO.insertStudent(student);

            System.out.println(student);


    }


    @Test
    public void testDeleteStudent(){


        //SqlSession表示MyBatis与数据库之间的会话，通过工厂方法设计模式
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        //通过mybatis提供的SqlSession对象调用getMapper方法获取DAO接口对象
        StudentDAO studentDAO = sqlSession.getMapper(StudentDAO.class);

        //调用被测试方法
        int i = studentDAO.deleteStudent("10001");
        //提交事务
        sqlSession.commit();
        System.out.println(i);


    }


    @Test       //不知道为什么这个test一直修改失败，返回值是0
    public void testUpdateStudent(){
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(is);
            SqlSession sqlSession = factory.openSession();
            StudentDAO studentDAO = sqlSession.getMapper(StudentDAO.class);
            int i = studentDAO.updateStudent(new Student(0,"10001","李四","女",21));
            sqlSession.commit();
            assertEquals(1,i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testListStudents() {
        StudentDAO studentDAO = MyBatisUtil.getMapper(StudentDAO.class);
        List<Student> list = studentDAO.listStudents();
            for (Student stu:list){
                System.out.println(stu);
            }
    }
    @Test
    public void testQueryStudent(){
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(is);
            SqlSession sqlSession = factory.openSession();
            StudentDAO studentDAO = sqlSession.getMapper(StudentDAO.class);

            Student student = studentDAO.queryStudent("10002");
            System.out.println(student);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testListStudentsByPage() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(is);
            SqlSession sqlSession = factory.openSession();
            StudentDAO studentDAO = sqlSession.getMapper(StudentDAO.class);

            HashMap<String,Integer> map = new HashMap<>();
            map.put("start",0);
            map.put("pageSize",2);
            List<Student> list = studentDAO.listStudentsByPage(0,2);

            for(Student stu:list){
                System.out.println(stu);
            }

            assertNotNull(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
        StudentDAO studentDAO = MyBatisUtil.getMapper(StudentDAO.class);
        PageHelper.startPage(1,4);
        List<Student> students = studentDAO.listStudents();
        for (Student s:students){
            System.out.println(s);
        }
    }

}


