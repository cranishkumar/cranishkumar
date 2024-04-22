package com.anish.email.Idvalidation.Student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    public final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository)   {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }
    public void addNewStudent(Student student){
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists){
            throw new IllegalStateException(
                    "Student with Id "+ studentId +" doest not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email){
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new IllegalStateException(
                "Student with Id "+ studentId +" doest not exist"));
        if (name !=null &&
                name.length()>0 &&
                !Objects.equals(student.getName(), name)){
            student.setName(name);
        }
        if (email != null &&
        email.length()>0 &&
        !Objects.equals(student.getEmail(),email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

            if (studentOptional.isPresent()){
                throw new IllegalStateException("email already taken");
            }
            student.setEmail(email);
        }
        //syntax check - park for later
        //email gibberish check - park for later
        //domain existence check - TBI
        //MX record check - TBI
        //catch-all domain check - park for later
        //SMTP authentication or address ping test - TBI
    }
}

