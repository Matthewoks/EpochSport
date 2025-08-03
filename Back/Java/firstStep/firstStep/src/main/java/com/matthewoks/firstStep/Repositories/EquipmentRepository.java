package com.matthewoks.firstStep.Repositories;


import com.matthewoks.firstStep.Models.Equipment;
import com.matthewoks.firstStep.Models.Workout;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EquipmentRepository implements IRepositoryRead<Equipment>,IRepositoryWrite<Equipment>{


    @Override
    public Equipment getById(int id) {
        Equipment eq =null;
        try {
            Connection conn =  ConnectionSingleton.getInstance().getConnection();

            String sqlTxt = "SELECT id, name, category FROM equipments WHERE id = ? ";
            PreparedStatement ps = conn.prepareStatement(sqlTxt);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                eq = new Equipment();
                eq.setId(rs.getLong("id"));
                eq.setName(rs.getString("name"));
                eq.setCategory(rs.getString("category"));
                eq.setImageUrl("");

            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return eq;
    }

    @Override
    public List<Equipment> getAll() {
        List<Equipment> list = new ArrayList<Equipment>();

        try {
            Connection conn =  ConnectionSingleton.getInstance().getConnection();

            String sqlTxt = "SELECT id, name, category  FROM equipments ";
            PreparedStatement ps = conn.prepareStatement(sqlTxt);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Equipment eq= new Equipment();
                eq.setId(rs.getLong("id"));
                eq.setName(rs.getString("name"));
                eq.setCategory(rs.getString("category"));
                list.add(eq);
            }
            conn.close();
        } catch (Exception e) {

            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }


        return list;
    }

    @Override
    public boolean Insert(Equipment obj) {
        boolean result = false;
        try {
            Connection conn =  ConnectionSingleton.getInstance().getConnection();

            String sqlTxt = "INSERT INTO equipments(name, category,image_url) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sqlTxt);

            ps.setString(1,obj.getName());
            ps.setString(2,obj.getCategory());
            ps.setString(3,obj.getImageUrl());

            int affRows = ps.executeUpdate();
            if(affRows>0)result = true;


            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean Update(Equipment obj) {
        return false;
    }

    @Override
    public boolean Delete(int id) {
        boolean result = false;
        try {
            Connection conn =  ConnectionSingleton.getInstance().getConnection();

            String sqlTxt = "DELETE FROM equipments WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sqlTxt);

            ps.setInt(1,id);


            int affRows = ps.executeUpdate();
            if(affRows>0)result = true;


            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }
}
