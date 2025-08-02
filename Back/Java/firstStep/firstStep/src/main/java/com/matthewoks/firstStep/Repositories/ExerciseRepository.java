package com.matthewoks.firstStep.Repositories;


import com.matthewoks.firstStep.Models.Equipment;
import com.matthewoks.firstStep.Models.Exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ExerciseRepository  implements IRepositoryRead<Exercise>,IRepositoryWrite<Exercise> {
    @Override
    public Exercise getById(int id) {
        Exercise ex =null;
        try {
            Connection conn =  ConnectionSingleton.getInstance().getConnection();

            String sqlTxt = "SELECT id, name, category FROM exercises WHERE id = ? ";
            PreparedStatement ps = conn.prepareStatement(sqlTxt);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                ex = new Exercise();
                ex.setId(rs.getLong("id"));
                ex.setName(rs.getString("name"));
                ex.setDescription(rs.getString("description"));
                ex.setColor(rs.getString("color"));
                ex.setImageUrl(rs.getString("imageUrl"));
                ex.setDuration(rs.getFloat("duration"));
                ex.setRepetitions(rs.getInt("repetitions"));
                ex.setSets(rs.getInt("sets"));
                ex.setRestTime(rs.getFloat("restTime"));
                ex.setExecutionMode(rs.getString("executionMode"));
                ex.setIntensityLevel(rs.getInt("intensityLevel"));

                sqlTxt = "SELECT T0.id, T0.name, T0.category, T0.imageUrl " +
                        " FROM equipments T0 " +
                        " INNER JOIN exercises_equipments T1 ON T1.id_equipment = T0.id "+
                        " WHERE T1.id_exercise = ? ";
                PreparedStatement psEq = conn.prepareStatement(sqlTxt);
                psEq.setLong(1,id);
                ResultSet rsEq = psEq.executeQuery();
                List<Equipment> listEq = new ArrayList<Equipment>();
                while (rsEq.next()) {
                    Equipment eq= new Equipment();
                            eq.setId(rsEq.getLong("id"));
                            eq.setName(rsEq.getString("name"));
                            eq.setCategory(rsEq.getString("category"));
                            eq.setImageUrl(rsEq.getString("imageUrl"));
                    listEq.add(eq);
                }
                ex.setEquipments(listEq);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return ex;
    }

    @Override
    public List<Exercise> getAll() {
        List<Exercise> list = new ArrayList<Exercise>();

        try {
            Connection conn =  ConnectionSingleton.getInstance().getConnection();

            String sqlTxt = "SELECT id, name, category  FROM exercises ";
            PreparedStatement ps = conn.prepareStatement(sqlTxt);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Exercise ex= new Exercise();
                ex.setId(rs.getLong("id"));
                ex.setName(rs.getString("name"));
                ex.setDescription(rs.getString("description"));
                ex.setColor(rs.getString("color"));
                ex.setImageUrl(rs.getString("imageUrl"));
                ex.setDuration(rs.getFloat("duration"));
                ex.setRepetitions(rs.getInt("repetitions"));
                ex.setSets(rs.getInt("sets"));
                ex.setRestTime(rs.getFloat("restTime"));
                ex.setExecutionMode(rs.getString("executionMode"));
                ex.setIntensityLevel(rs.getInt("intensityLevel"));
                sqlTxt = "SELECT T0.id, T0.name, T0.category, T0.imageUrl " +
                        " FROM equipments T0 " +
                        " INNER JOIN exercises_equipments T1 ON T1.id_equipment = T0.id "+
                        " WHERE T1.id_exercise = ? ";
                PreparedStatement psEq = conn.prepareStatement(sqlTxt);
                psEq.setLong(1,rs.getLong("id"));
                ResultSet rsEq = psEq.executeQuery();
                List<Equipment> listEq = new ArrayList<Equipment>();
                while (rsEq.next()) {
                    Equipment eq= new Equipment();
                    eq.setId(rsEq.getLong("id"));
                    eq.setName(rsEq.getString("name"));
                    eq.setCategory(rsEq.getString("category"));
                    eq.setImageUrl(rsEq.getString("imageUrl"));
                    listEq.add(eq);
                }
                ex.setEquipments(listEq);
                list.add(ex);
            }
            conn.close();
        } catch (Exception e) {

            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }


        return list;
    }

    @Override
    public boolean Insert(Exercise obj) {
        boolean result = false;
        try {
            Connection conn =  ConnectionSingleton.getInstance().getConnection();

            String sqlTxt = "INSERT INTO exercises(name, category) VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(sqlTxt);

            ps.setString(1,obj.getName());
            ps.setString(2,obj.getDescription());

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
    public boolean Update(Exercise obj) {
        return false;
    }

    @Override
    public boolean Delete(Exercise obj) {
        return false;
    }
}
