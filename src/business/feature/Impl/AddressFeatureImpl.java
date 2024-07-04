package business.feature.Impl;

import business.entity.Address;
import business.feature.IAddressFeature;
import business.utils.IOFile;

import java.util.ArrayList;
import java.util.List;

public class AddressFeatureImpl implements IAddressFeature {
    public static List<Address> addressList = new ArrayList<>();

    public AddressFeatureImpl() {
        addressList = IOFile.readFromFile(IOFile.PATH_ADDRESS);
    }

    @Override
    public void delete(Integer id) {
      int indexDelete = findById(id);
      if (indexDelete >=0) {
          addressList.remove(indexDelete);
      }else {
          System.err.println("Address id not found");
      }
        IOFile.writeToFile(IOFile.PATH_ADDRESS, addressList);
    }

    @Override
    public List<Address> getAll() {
        return addressList;
    }

    @Override
    public void save(Address address) {
      int indexCheck = findById(address.getAddressId());
      if (indexCheck >= 0) {
          addressList.set(indexCheck, address);
      }else {
          addressList.add(address);
      }
        IOFile.writeToFile(IOFile.PATH_ADDRESS, addressList);
    }

    @Override
    public Integer findById(Integer id) {
        for (int i = 0; i < addressList.size(); i++) {
            if (addressList.get(i).getAddressId() ==id){
                return i;
            }
        }
        return -1;
    }
}
