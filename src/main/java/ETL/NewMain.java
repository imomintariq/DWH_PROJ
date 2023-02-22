package ETL;

//import DataSourceEntities.Transaction;
import HQL_Queries.DWH_HQL_Queries;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

public class NewMain {
    public static void main(String [] args) throws InterruptedException, SQLException, ClassNotFoundException {


        MeshJoin mj = new MeshJoin();
        mj.RunMeshJoin();



    }
}
