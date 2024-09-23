package business.feature.Impl;



import business.entity.OrderDetail;
import business.entity.Orders;
import business.feature.IOderDetailFeature;
import business.utils.IOFile;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailFeatureImpl implements IOderDetailFeature {
    public static List<OrderDetail> orderDetailList = new ArrayList<>();

    static {
        orderDetailList = IOFile.readFromFile(IOFile.PATH_ORDER_DETAIL);
    }

    public OrderDetailFeatureImpl (){
        orderDetailList = IOFile.readFromFile(IOFile.PATH_ORDER_DETAIL);
    }


    @Override
    public List<OrderDetail> getAll() {
        return orderDetailList;
    }

    @Override
    public void save(OrderDetail orderDetail) {
     int indexCheck = findById(orderDetail.getOrderId());
     if(indexCheck<0){
         orderDetailList.add(orderDetail);
     }else {
         orderDetailList.set(indexCheck, orderDetail);
     }
        IOFile.writeToFile(IOFile.PATH_ORDER_DETAIL, orderDetailList);
    }

    @Override
    public Integer findById(Integer id) {
        for (int i = 0; i < orderDetailList.size(); i++) {
            if (orderDetailList.get(i).getOrderId() == id){
                return i;
            }
        }
        return -1;
    }

    @Override
    public void delete(Integer id) {
      int indexDelete = findById(id);
      if(indexDelete>=0){
          orderDetailList.remove(indexDelete);
          IOFile.writeToFile(IOFile.PATH_ORDER_DETAIL, orderDetailList);
      }else {
          System.err.println("not fount");
      }
    }
}
