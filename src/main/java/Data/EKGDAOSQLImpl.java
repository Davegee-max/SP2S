package Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EKGDAOSQLImpl implements EKGDAO {
    @Override
    public void save(EKGDTO ekgdto) {
        Connection conn = SQLConnector.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO EKGdata(CPR,time,EKG) VALUES (?,?,?)");
            preparedStatement.setString(1, (ekgdto.getCPR()));
            preparedStatement.setTimestamp(2, (ekgdto.getTime()));
            preparedStatement.setDouble(3,ekgdto.getEKG());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<EKGDTO> load(Timestamp time) {
       List <EKGDTO> data = new ArrayList<>();
        Connection connection = SQLConnector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM EKGdata WHERE time>?");
            preparedStatement.setTimestamp(1,time);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                EKGDTO ekgdto = new EKGDTO();
                ekgdto.setCPR(resultSet.getString("CPR"));
                ekgdto.setTime(resultSet.getTimestamp("time"));
                ekgdto.setEKG(resultSet.getDouble("EKG"));
                data.add(ekgdto);

            }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
    }
        return data;
}
}

