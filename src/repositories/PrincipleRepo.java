package repositories;

import java.util.Collection;
import java.util.HashMap;

public abstract class PrincipleRepo<T,O> implements IRepo<T,O> {

    protected HashMap<O,T> dataList =new HashMap<O,T>();

    @Override
    public void addToRepo(T item, O id){
        dataList.putIfAbsent(id,item);
    }
    @Override
    public T remFromRepo(O id){
        return dataList.remove(id);
    }
    @Override
    public T findInRepo(O id){
        return dataList.get(id);
    }
    @Override
    public Collection<T> getAllItems(){
        return dataList.values();
    }


}
