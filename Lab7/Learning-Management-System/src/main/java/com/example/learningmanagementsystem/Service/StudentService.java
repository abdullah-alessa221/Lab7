package com.example.learningmanagementsystem.Service;

import com.example.learningmanagementsystem.Model.StudentModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {

    private final ArrayList<StudentModel> students = new ArrayList<>();

    public void addStudent(StudentModel student) {
        students.add(student);
    }

    public ArrayList<StudentModel> getAllStudents() {
        return students;
    }

    public boolean updateStudent(String id, StudentModel updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                updatedStudent.setId(id);
                students.set(i, updatedStudent);
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudent(String id) {
        for(StudentModel s: students){
            if(s.getId().equals(id)){
                students.remove(s);
                return true;
            }
        }
        return false;
    }



    public StudentModel getStudentById(String id) {
        for (StudentModel s : students) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }


    public ArrayList<StudentModel> getStudentsUnderAge(int age) {
        ArrayList<StudentModel> result = new ArrayList<>();
        for (StudentModel s : students) {
            if (s.getAge() < age) {
                result.add(s);
            }
        }
        return result;
    }


    public ArrayList<StudentModel> searchByName(String keyword) {
        ArrayList<StudentModel> result = new ArrayList<>();
        for (StudentModel s : students) {
            if (s.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(s);
            }
        }
        return result;
    }


    public int countStudents() {
        return students.size();
    }
}
