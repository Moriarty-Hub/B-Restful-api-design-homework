package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private static final String PREFIX_OF_SETTER = "set";
    private static final String PREFIX_OF_GETTER = "get";

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        return studentRepository.addStudent(student);
    }

    public void deleteStudent(Integer id) {
        studentRepository.deleteStudent(id);
    }

    public List<Student> getStudentList(String gender) {
        List<Student> studentList = new LinkedList<>(studentRepository.findAll());
        if (gender == null) {
            return studentList;
        } else {
            return studentList.stream()
                    .filter(student -> student.getGender().getName().equals(gender))
                    .collect(Collectors.toList());
        }
    }

    public Student getStudentById(Integer id) {
        return studentRepository.findById(id);
    }

    public Student updateStudentById(Integer id, Student studentRequestBody) {
        Student student = studentRepository.findById(id);

        Field[] studentFields = Student.class.getDeclaredFields();

        // Get the name, type, getter and setter method of all parameters of Student class by reflection,
        // iterate the request body, find all parameter that is not null,
        // and override the corresponding field in origin student object by them.
        Arrays.stream(studentFields).forEach(studentField -> {
            try {
                String studentParamWithCapitalizedFirstLetter = studentField.getName().substring(0, 1).toUpperCase() + studentField.getName().substring(1);
                Type studentParamType = studentField.getGenericType();
                Method getMethod = Student.class.getMethod(PREFIX_OF_GETTER + studentParamWithCapitalizedFirstLetter);
                Method setMethod = Student.class.getMethod(PREFIX_OF_SETTER + studentParamWithCapitalizedFirstLetter, Class.forName(studentParamType.getTypeName()));
                Object value = getMethod.invoke(studentRequestBody);
                if (value != null) {
                    setMethod.invoke(student, value);
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        studentRepository.updateStudent(id, student);
        return student;
    }
}
