package business.feature;

import business.entity.Orders;

import java.util.List;

public interface IGenericFeature<T,E> {
    List<T> getAll ();
    void save(T t);
    Integer findById(E e);
    void delete(E e);
}
