package business.feature.Impl;

import business.entity.RoLe;
import business.feature.IRoleFeature;
import business.utils.IOFile;


import java.util.ArrayList;
import java.util.List;

public class RoleFeatureImpl implements IRoleFeature {
    public static List <RoLe> roLeList;

    static {
        roLeList = IOFile.readFromFile(IOFile.PATH_ROLE);
    }

    public RoleFeatureImpl() {
        roLeList = IOFile.readFromFile(IOFile.PATH_ROLE);
    }

    @Override
    public List<RoLe> getAll() {
        return roLeList;
    }

    @Override
    public void save(RoLe roLe) {
      int checkId = findById(roLe.getRoleId());
      if (checkId<0){
          roLeList.add(roLe);
      }else {
          roLeList.set(checkId, roLe);
      }
        IOFile.writeToFile(IOFile.PATH_ROLE,roLeList );
    }

    @Override
    public Integer findById(Integer id) {
        for (int i = 0; i <roLeList.size(); i++){
            if (roLeList.get(i).getRoleId() == id)
                return i;
        }
        return -1;
    }

    @Override
    public void delete(Integer id) {
        int idDelete = findById(id);
        if (idDelete>=0){
            roLeList.remove(idDelete);
            IOFile.writeToFile(IOFile.PATH_ROLE,roLeList );
        }else {
            System.err.println("found not role");
        }

    }
}