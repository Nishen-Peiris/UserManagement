/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorialspoint;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nishen
 */
public class UserDao {
    public List<User> getAllUsers(){
      List<User> userList = null;
      try {
         File file = new File("Users.dat");
         if (!file.exists()) {
            User user = new User(1, "Mahesh", "Teacher");
            userList = new ArrayList<>();
            userList.add(user);
            saveUserList(userList);		
         }
         else{
            FileInputStream fis = new FileInputStream(file);
             try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                 userList = (List<User>) ois.readObject();
             }
         }
      } catch (IOException | ClassNotFoundException e) {
      }		
      return userList;
   }

   private void saveUserList(List<User> userList){
      try {
         File file = new File("Users.dat");
         FileOutputStream fos;

         fos = new FileOutputStream(file);

          try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
              oos.writeObject(userList);
          }
      } catch (FileNotFoundException e) {
      } catch (IOException e) {
      }
   }   
}
