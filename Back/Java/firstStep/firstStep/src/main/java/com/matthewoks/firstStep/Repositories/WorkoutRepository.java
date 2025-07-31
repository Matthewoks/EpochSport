package com.matthewoks.firstStep.Repositories;

import com.matthewoks.firstStep.Models.Workout;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WorkoutRepository implements IRepositoryRead<Workout>,IRepositoryWrite<Workout> {
    @Override
    public Workout getById(int id) {
        Workout wo =null;
        try {
            Connection conn =  ConnectionSingleton.getInstance().getConnection();

            String sqlTxt = "SELECT id, name, color, exercises_json  FROM workouts WHERE id = ? ";
            PreparedStatement ps = conn.prepareStatement(sqlTxt);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                wo= new Workout();
                wo.setId(rs.getInt("id"));
                wo.setMeetName(rs.getString("name"));
                wo.setColor(rs.getString("color"));

            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return wo;
    }
    public Workout getByName(String name) {
        return null;
    }
    @Override
    public List<Workout> getAll() {
        List<Workout> list = new ArrayList<Workout>();

        try {
            Connection conn =  ConnectionSingleton.getInstance().getConnection();

            String sqlTxt = "SELECT id, name, color, exercises_json  FROM workouts ";
            PreparedStatement ps = conn.prepareStatement(sqlTxt);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Workout wo= new Workout();
                wo.setId(rs.getInt("id"));
                wo.setMeetName(rs.getString("name"));
                wo.setColor(rs.getString("color"));
                list.add(wo);
            }
            conn.close();
        } catch (Exception e) {

            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }


        return list;
    }

    @Override
    public boolean Insert(Workout obj) {
        boolean result = false;
        try {
            Connection conn =  ConnectionSingleton.getInstance().getConnection();

            String sqlTxt = "INSERT INTO workouts(name, color) VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(sqlTxt);

            ps.setString(1,obj.getMeetName());
            ps.setString(2,obj.getColor());

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
    public boolean Update(Workout obj) {
        return false;
    }

    @Override
    public boolean Delete(Workout obj) {
        return false;
    }


    //CRUD

    //CREATE

    //READ

    //UPDATE

    //DELETE
}
