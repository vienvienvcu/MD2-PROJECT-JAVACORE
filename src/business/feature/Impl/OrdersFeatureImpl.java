package business.feature.Impl;

import business.entity.Orders;
import business.feature.IOrdersFeature;
import business.utils.IOFile;

import java.util.ArrayList;
import java.util.List;

public class OrdersFeatureImpl implements IOrdersFeature {
    public static List<Orders> orderlist = new ArrayList<Orders>();

    static {
        orderlist = IOFile.readFromFile(IOFile.PATH_ORDER);
    }

    public OrdersFeatureImpl() {

        orderlist = IOFile.readFromFile(IOFile.PATH_ORDER);
    }
    @Override
    public void delete(Integer id) {
        int indexDelete = findById(id);
        if (indexDelete >=0) {
            orderlist.remove(indexDelete);
            IOFile.writeToFile(IOFile.PATH_ORDER, orderlist);
        }else {
            System.err.println("not found");
        }

    }

    @Override
    public List<Orders> getAll() {

        return orderlist;
    }

    @Override
    public void save(Orders orders) {
        int indexCheck = findById(orders.getOrderId());
        if (indexCheck<0) {
            orderlist.add(orders);
        }else {
            orderlist.set(indexCheck, orders);
        }
        IOFile.writeToFile(IOFile.PATH_ORDER, orderlist);
    }

    @Override
    public Integer findById(Integer id) {
        for (int i = 0; i < orderlist.size(); i++) {
            if (orderlist.get(i).getOrderId() == id){
                return i;
            }
        }
        return -1;
    }
}
