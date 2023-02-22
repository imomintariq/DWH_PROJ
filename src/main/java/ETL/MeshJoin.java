package ETL;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class MeshJoin {

    public int queue_size = 10;

    /*
    * Algorithm MESHJOIN
Input: A relation R and a stream S.
Output: Stream R S.
Parameters: w tuples of S and b pages of R.
Method:
    1. While (true)
        2.  Read b pages from R and w tuples from S
        3.  If queue Q is full Then
        4.      Dequeue T from Q where T are w pointers
        5.      Remove the tuples of hash H that correspond to T
        6.  EndIf
        7.  Add the w tuples of S in H
        8.  Enqueue in Q, w pointers to the above tuples in H
        9.  For each tuple r in the b pages of R
        10.     Ouput r x H
    11. EndWhile
Algorithm MESHJOIN*/
    public void RunMeshJoin() throws SQLException, ClassNotFoundException {
        StreamData sd = new StreamData();
        sd.setUp();
        MasterData md = new MasterData();
        md.setUp();
        //HashMap<String, HashMap> joinRecord = new HashMap<>();
        HashMap<String, HashMap> final_map = new HashMap<>();

        md.loadMasterPartition();
        sd.loadQueue();

        while (true) {



            if (sd.InputQueue.size() >= queue_size) {
                HashMap<String, HashMap> output = (HashMap<String, HashMap>) sd.InputQueue.poll();
                sd.streamHashTableCustomerId.remove(output.keySet());
                System.out.println("Completely Joined!");

            }





            for (Map.Entry<String, HashMap> set : sd.streamHashTableProductId.entrySet()) {


                String streamRecordPid = set.getKey();
                HashMap masterRecord = md.masterBufferTable.get(streamRecordPid);

                if (masterRecord != null) {

                    //HashMap<String, HashMap> temp_hashMap = new HashMap<>();
                    set.getValue().put("product_joined", "true");
                    set.getValue().put("product_id",masterRecord.get("product_id"));
                    set.getValue().put("product_name",masterRecord.get("product_name"));
                    set.getValue().put("supplier_id",masterRecord.get("supplier_id"));
                    set.getValue().put("supplier_name", masterRecord.get("supplier_name"));
                    set.getValue().put("product_price", masterRecord.get("product_price"));


                    if(set.getValue().containsKey("customer_joined") && set.getValue().containsKey("product_joined")){

                        final_map.put(set.getValue().get("transaction_id").toString(),set.getValue());
                        System.out.println("Insert");
                    }
                }
            }

            for (Map.Entry<String, HashMap> set : sd.streamHashTableCustomerId.entrySet()) {


                String streamRecordCid = set.getKey();
                HashMap masterRecord = md.masterBufferTable.get(streamRecordCid);
                if (masterRecord != null) {


                    set.getValue().put("customer_joined", "true");
                    set.getValue().put("customer_id", masterRecord.get("customer_id"));
                    set.getValue().put("customer_name",masterRecord.get("customer_name"));

                    if( set.getValue().containsKey("customer_joined") && set.getValue().containsKey("product_joined")){


                        final_map.put(set.getValue().get("transaction_id").toString(),set.getValue());
                        System.out.println("Insert");
                    }
                }
            }
            md.loadMasterPartition();
            if(sd.loadQueue() == false){
                break;
            }


        }

        System.out.println("END RESULT");
        for (Map.Entry<String, HashMap> set : final_map.entrySet()){
            //System.out.println(set);
            DataTransformation dt = new DataTransformation();
            dt.setUp();
            dt.transformData(set.getValue());

        }


    }
}
