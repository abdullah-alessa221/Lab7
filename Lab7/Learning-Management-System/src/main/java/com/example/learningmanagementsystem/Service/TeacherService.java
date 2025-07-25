package com.example.learningmanagementsystem.Service;
import com.example.learningmanagementsystem.Model.TeacherModel;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TeacherService {
    private final ArrayList<TeacherModel> teachers = new ArrayList<>();

    public void addTeacher(TeacherModel teacher) {
        teachers.add(teacher);
    }

    public ArrayList<TeacherModel> getAllTeachers() {
        return teachers;
    }

    public boolean updateTeacher(String id, TeacherModel updatedTeacher) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId().equals(id)) {
                updatedTeacher.setId(id);
                teachers.set(i, updatedTeacher);
                return true;
            }
        }
        return false;
    }

    public boolean deleteTeacher(String id) {
       for(TeacherModel t: teachers){
           if(t.getId().equals(id)){
               teachers.remove(t);
               return true;
           }
       }
       return false;
    }




    public TeacherModel getTeacherById(String id) {
        for (TeacherModel t : teachers) {
            if (t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }


    public ArrayList<TeacherModel> getTeachersAboveAge(int age) {
        ArrayList<TeacherModel> result = new ArrayList<>();
        for (TeacherModel t : teachers) {
            if (t.getAge() > age) {
                result.add(t);
            }
        }
        return result;
    }


    public boolean existsByName(String name) {
        for (TeacherModel t : teachers) {
            if (t.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }


    public int countTeachers() {
        return teachers.size();
    }
}
