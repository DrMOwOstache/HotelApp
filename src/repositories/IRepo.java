package repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public interface IRepo<T, O > {
    public void addToRepo(T item, O id);

    public T remFromRepo(O id);

    public T findInRepo(O id);

    public Collection<T> getAllItems();

}
