package com.example.springbootmp.service.impl;

import com.example.springbootmp.entity.Student;
import com.example.springbootmp.mapper.StudentMapper;
import com.example.springbootmp.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tao
 * @since 2022-03-27
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}
