package com.matthewoks.firstStep.Repositories;


import com.matthewoks.firstStep.Models.Equipment;
import com.matthewoks.firstStep.Models.Exercise;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ExerciseRepository  implements IRepositoryRead<Exercise>,IRepositoryWrite<Exercise> {

    private final DataSource ds;

    public ExerciseRepository(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Exercise getById(long id) {
        Exercise ex =null;
        try {
            //utilizzo bean adesso
            //  Connection conn =  ConnectionSingleton.getInstance().getConnection();
            Connection conn = ds.getConnection();
            String sqlTxt = "SELECT id, " +
                    "name,  " +
                    "description,  " +
                    "color,  " +
                    "image_url,  " +
                    "duration,  " +
                    "repetitions,  " +
                    "sets,  " +
                    "rest_time,  " +
                    "execution_mode,  " +
                    "intensity_level  "+ 
                    "FROM exercises WHERE id = ? ";
            PreparedStatement ps = conn.prepareStatement(sqlTxt);
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                ex = new Exercise();
                ex.setId(rs.getLong("id"));
                ex.setName(rs.getString("name"));
                ex.setDescription(rs.getString("description"));
                ex.setColor(rs.getString("color"));
                ex.setImageUrl(rs.getString("image_url"));
                ex.setDuration(rs.getInt("duration"));
                ex.setRepetitions(rs.getInt("repetitions"));
                ex.setSets(rs.getInt("sets"));
                ex.setRestTime(rs.getInt("rest_time"));
                ex.setExecutionMode(rs.getString("execution_mode"));
                ex.setIntensityLevel(rs.getInt("intensity_level"));

                sqlTxt = "SELECT T0.id, T0.name, T0.category, T0.image_url " +
                        " FROM equipments T0 " +
                        " INNER JOIN exercises_equipments T1 ON T1.equipment_id = T0.id " +
                        " WHERE T1.exercise_id = ? ";
                PreparedStatement psEq = conn.prepareStatement(sqlTxt);
                psEq.setLong(1,id);
                ResultSet rsEq = psEq.executeQuery();
                List<Equipment> listEq = new ArrayList<Equipment>();
                while (rsEq.next()) {
                    Equipment eq= new Equipment();
                            eq.setId(rsEq.getLong("id"));
                            eq.setName(rsEq.getString("name"));
                            eq.setCategory(rsEq.getString("category"));
                            eq.setImageUrl(rsEq.getString("image_url"));
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
            //utilizzo bean adesso
            //  Connection conn =  ConnectionSingleton.getInstance().getConnection();
            Connection conn = ds.getConnection();
            String sqlTxt = "SELECT id, " +
                    "name, " +
                    "description, " +
                    "color, " +
                    "image_url, " +
                    "duration, " +
                    "repetitions, " +
                    "sets, " +
                    "rest_time, " +
                    "execution_mode, " +
                    "intensity_level " +
                    " FROM exercises ";
            PreparedStatement ps = conn.prepareStatement(sqlTxt);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Exercise ex= new Exercise();
                ex.setId(rs.getLong("id"));
                ex.setName(rs.getString("name"));
                ex.setDescription(rs.getString("description"));
                ex.setColor(rs.getString("color"));
                ex.setImageUrl(rs.getString("image_url"));
                ex.setDuration(rs.getInt("duration"));
                ex.setRepetitions(rs.getInt("repetitions"));
                ex.setSets(rs.getInt("sets"));
                ex.setRestTime(rs.getInt("rest_time"));
                ex.setExecutionMode(rs.getString("execution_mode"));
                ex.setIntensityLevel(rs.getInt("intensity_level"));
                sqlTxt = "SELECT T0.id, T0.name, T0.category, T0.image_url " +
                        " FROM equipments T0 " +
                        " INNER JOIN exercises_equipments T1 ON T1.equipment_id = T0.id "+
                        " WHERE T1.exercise_id = ? ";
                PreparedStatement psEq = conn.prepareStatement(sqlTxt);
                psEq.setLong(1,rs.getLong("id"));
                ResultSet rsEq = psEq.executeQuery();
                List<Equipment> listEq = new ArrayList<Equipment>();
                while (rsEq.next()) {
                    Equipment eq= new Equipment();
                    eq.setId(rsEq.getLong("id"));
                    eq.setName(rsEq.getString("name"));
                    eq.setCategory(rsEq.getString("category"));
                    eq.setImageUrl(rsEq.getString("image_url"));
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
            //utilizzo bean adesso
            //  Connection conn =  ConnectionSingleton.getInstance().getConnection();
            Connection conn = ds.getConnection();
            String sqlTxt = "INSERT INTO exercises(name, " +
                    "description," +
                    "color," +
                    "image_url," +
                    "duration," +
                    "repetitions," +
                    "sets," +
                    "rest_time," +
                    "execution_mode," +
                    "intensity_level) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sqlTxt);

            ps.setString(1,obj.getName());
            ps.setString(2,obj.getDescription());
            ps.setString(3,obj.getColor());
            ps.setString(4,obj.getImageUrl());
            ps.setInt(5,obj.getDuration());
            ps.setInt(6,obj.getRepetitions());
            ps.setInt(7,obj.getSets());
            ps.setInt(8,obj.getRestTime());
            ps.setString(9,obj.getExecutionMode());
            ps.setInt(10,obj.getIntensityLevel());




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
    public boolean Delete(int id) {
        boolean result = false;
        try {
            //utilizzo bean adesso
            //  Connection conn =  ConnectionSingleton.getInstance().getConnection();
            Connection conn = ds.getConnection();
            String sqlTxt = "DELETE FROM exercises WHERE id = ?";
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
