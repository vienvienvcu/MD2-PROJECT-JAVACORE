package business.feature.Impl;

import business.entity.Address;
import business.feature.IAddressFeature;

import java.util.ArrayList;
import java.util.List;

public class AddressFeatureImpl implements IAddressFeature {
    public static List<Address> addressList = new ArrayList<>();
    @Override
    public void delete(Integer integer) {

    }

    @Override
    public List<Address> getAll() {
        return List.of();
    }

    @Override
    public void save(Address address) {

    }

    @Override
    public Integer findById(Integer integer) {
        return null;
    }
}
