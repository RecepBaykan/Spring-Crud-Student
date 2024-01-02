package com.proje.crud.service;

import com.proje.crud.model.Student;
import com.proje.crud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService
{
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAll(){

        return studentRepository.findAll().stream().collect(Collectors.toList());
    }

    public boolean add(Student _student){

        boolean has = false;

        for(Student student: getAll()){
            if(_student.getNumber() == student.getNumber()){
                has = true;
                break;
            }
        }

        if(!has){
            studentRepository.save(_student);
        }

        return has;

    }

    public boolean update(Student _student){
        boolean has = false;

        for(Student student: getAll()){
            if(_student.getNumber() == student.getNumber()){
                has = true;
                student.setName(_student.getName());
                student.setSurname(_student.getSurname());
                studentRepository.save(student);
                break;
            }
        }



        return has;
    }

    public boolean delete(int number){
        List<Student> students = getAll();
        boolean has = false;
        for(Student student: students){

            if(student.getNumber() == number){
                studentRepository.delete(student);
                has = true;
                break;
            }
        }

        return has;



    }


}
