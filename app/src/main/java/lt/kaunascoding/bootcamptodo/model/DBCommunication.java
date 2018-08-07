package lt.kaunascoding.bootcamptodo.model;

import java.util.ArrayList;

/**
 * Created by user on 8/7/2018.
 */

public interface DBCommunication {
    ArrayList<ItemVO> getAllItems();
    void addItem(String task);
    void deleteItem(ItemVO itemVO);
    void updateItem(ItemVO itemVO);
}
