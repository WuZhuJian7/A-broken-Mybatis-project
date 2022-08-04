package com.ccy.dao;

import com.ccy.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface StudentDAO {

    //在mybatis中操作：
    //1.如果操作方法中只有一个简单类型或者字符串类型的参数，在Mapper配置中可以直接通过#{key}直接获取
    //2.如果操作方法中有个一对象类型的参数，在mapper配置中可以直接通过#{attrName}获取对象的指定属性值（attrName必须是参数对象的属性）
    //3.如果操作方法中有一个map类型的参数，在Mapper配置中可以直接通过#{key}获取key对应的value
    //4.如果操作方法有多个参数，

    public int insertStudent(Student student);
    public int deleteStudent(String stuNum);
    public int updateStudent(Student student);
    public List<Student> listStudents();
    public Student queryStudent(String stuName);
    public List<Student> listStudentsByPage(@Param("start") int start,
                                              @Param("pageSize") int pageSize);


}
