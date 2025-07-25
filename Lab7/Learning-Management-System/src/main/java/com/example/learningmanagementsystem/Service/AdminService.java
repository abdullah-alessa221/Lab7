package com.example.learningmanagementsystem.Service;

import com.example.learningmanagementsystem.Model.AdminModel;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdminService {
    private final ArrayList<AdminModel> admins = new ArrayList<>();

    public void addAdmin(AdminModel admin) {
        admins.add(admin);
    }

    public ArrayList<AdminModel> getAllAdmins() {
        return admins;
    }

    public boolean updateAdmin(String id, AdminModel updatedAdmin) {
        for (int i = 0; i < admins.size(); i++) {
            if (admins.get(i).getId().equals(id)) {
                updatedAdmin.setId(id);
                admins.set(i, updatedAdmin);
                return true;
            }
        }
        return false;
    }

    public boolean deleteAdmin(String id) {
       for(AdminModel a: admins){
           if(a.getId().equals(id)){
               admins.remove(a);
               return true;
           }
       }
       return false;
    }


    public AdminModel getAdminById(String id) {
        for (AdminModel a : admins) {
            if (a.getId().equals(id)) {
                return a;
            }
        }
        return null;
    }


    public ArrayList<AdminModel> getAdminsByName(String name) {
        ArrayList<AdminModel> result = new ArrayList<>();
        for (AdminModel a : admins) {
            if (a.getName().equalsIgnoreCase(name)) {
                result.add(a);
            }
        }
        return result;
    }


    public ArrayList<AdminModel> getAdminsOverAge(int age) {
        ArrayList<AdminModel> result = new ArrayList<>();
        for (AdminModel a : admins) {
            if (a.getAge() > age) {
                result.add(a);
            }
        }
        return result;
    }


    public int countAdmins() {
        return admins.size();
    }
}
