package com.matthewoks.firstStep.Repositories;

import com.matthewoks.firstStep.Models.Equipment;
import com.matthewoks.firstStep.Models.Exercise;
import com.matthewoks.firstStep.Models.Workout;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WorkoutRepository implements IRepositoryRead<Workout>,IRepositoryWrite<Workout> {
    private final DataSource ds;

    public WorkoutRepository(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Workout getById(long id) {
        Workout wo =null;
        try {
            //utilizzo bean adesso
            //  Connection conn =  ConnectionSingleton.getInstance().getConnection();
            Connection conn = ds.getConnection();
            String sqlTxt = "SELECT id, name, color  FROM workouts WHERE id = ? ";
            PreparedStatement ps = conn.prepareStatement(sqlTxt);
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                wo= new Workout();
                wo.setId(rs.getLong("id"));
                wo.setMeetName(rs.getString("name"));
                wo.setColor(rs.getString("color"));
                sqlTxt = "SELECT id, " +
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
                PreparedStatement psEx = conn.prepareStatement(sqlTxt);
                psEx.setLong(1,id);
                ResultSet rsEx = psEx.executeQuery();
                List<Exercise> listEx = new ArrayList<Exercise>();
                while (rsEx.next()) {
                    Exercise ex = new Exercise();
                    ex.setId(rsEx.getLong("id"));
                    ex.setName(rsEx.getString("name"));
                    ex.setDescription(rsEx.getString("description"));
                    ex.setColor(rsEx.getString("color"));
                    ex.setImageUrl(rsEx.getString("image_url"));
                    ex.setDuration(rsEx.getInt("duration"));
                    ex.setRepetitions(rsEx.getInt("repetitions"));
                    ex.setSets(rsEx.getInt("sets"));
                    ex.setRestTime(rsEx.getInt("rest_time"));
                    ex.setExecutionMode(rsEx.getString("execution_mode"));
                    ex.setIntensityLevel(rsEx.getInt("intensity_level"));
                    listEx.add(ex);
                }
                wo.setExercises(listEx);
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
            //utilizzo bean adesso
            //  Connection conn =  ConnectionSingleton.getInstance().getConnection();
            Connection conn = ds.getConnection();
            String sqlTxt = "SELECT id, name, color  FROM workouts ";
            PreparedStatement ps = conn.prepareStatement(sqlTxt);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Workout wo= new Workout();
                wo.setId(rs.getLong("id"));
                wo.setMeetName(rs.getString("name"));
                wo.setColor(rs.getString("color"));
                sqlTxt = "SELECT id, " +
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
                PreparedStatement psEx = conn.prepareStatement(sqlTxt);
                psEx.setLong(1,rs.getLong("id"));
                ResultSet rsEx = psEx.executeQuery();
                List<Exercise> listEx = new ArrayList<Exercise>();
                while (rsEx.next()) {
                    Exercise ex = new Exercise();
                    ex.setId(rsEx.getLong("id"));
                    ex.setName(rsEx.getString("name"));
                    ex.setDescription(rsEx.getString("description"));
                    ex.setColor(rsEx.getString("color"));
                    ex.setImageUrl(rsEx.getString("image_url"));
                    ex.setDuration(rsEx.getInt("sets"));
                    ex.setRepetitions(rsEx.getInt("sets"));
                    ex.setSets(rsEx.getInt("sets"));
                    ex.setRestTime(rsEx.getInt("rest_time"));
                    ex.setExecutionMode(rsEx.getString("execution_mode"));
                    ex.setIntensityLevel(rsEx.getInt("intensity_level"));
                    listEx.add(ex);
                }
                wo.setExercises(listEx);
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
            //utilizzo bean adesso
            //  Connection conn =  ConnectionSingleton.getInstance().getConnection();
            Connection conn = ds.getConnection();
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
    public boolean Delete(int id) {
        boolean result = false;
        try {
            //utilizzo bean adesso
            //  Connection conn =  ConnectionSingleton.getInstance().getConnection();
            Connection conn = ds.getConnection();
            String sqlTxt = "DELETE FROM workouts WHERE id = ?";
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


    //CRUD

    //CREATE

    //READ

    //UPDATE

    //DELETE
}
