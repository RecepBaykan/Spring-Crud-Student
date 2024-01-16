package com.proje.crud.controller;

import com.google.gson.Gson;
import com.proje.crud.model.Student;
import com.proje.crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/getAll")
    public List<Student> getAllStudent(){

        return studentService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id){

        if (studentService.delete(id)){

            return "Öğrenci başarıyla silindi";
        }else {
            return "Öğrenci bulunamadı";
        }


    }

    @PostMapping("/addStudent")
    public String addStudent(@RequestBody String _student){
        Gson gson = new Gson();
        Student student = gson.fromJson(_student, Student.class);

        if(!studentService.add(student)){
            return "Öğrenci başarıyla eklendi";
        }else{
            return student.getNumber() + " numaralı öğrenci zaten sistemde mevcuttur. Numarayı değiştirin";
        }



    }

    @PostMapping("/updateStudent")
    public String updateStudent(@RequestBody String _student){
        Gson gson = new Gson();
        Student student = gson.fromJson(_student, Student.class);

        if(studentService.update(student)){
            return "Öğrenci başarıyla güncellendi";
        }else{
            return student.getNumber() + " öğrenci bulunamadı";
        }



    }







}
