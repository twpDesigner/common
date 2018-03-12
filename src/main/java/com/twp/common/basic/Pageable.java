package com.twp.common.basic;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Delegate;
import org.springframework.data.domain.Sort;

import java.util.*;

/**
 * Created by tanweiping on 17/8/8.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Pageable extends  HashMap<String,Object>{

    public static final String PAGE = "page";
    public static final String SIZE = "size";
    public static final String SORT = "sort";

    @Delegate
    protected Map<String,Object> delegate = new HashMap<>();

    public Integer getPageNumber() {
        return (Integer) delegate.get(PAGE);
    }

    public void setPageNumber(Integer pageNumber) {
        delegate.put(PAGE, pageNumber);
    }

    public Integer getPageSize() {
        return (Integer) delegate.get(SIZE);
    }

    public void setPageSize(Integer pageSize) {
        delegate.put(SIZE, pageSize);
    }

    @SuppressWarnings("unchecked")
    public List<Sort> getSortOrder() {
        return (List<Sort>) delegate.get(SORT);
    }

    public void setSort(Sort sort) {
        //delegate.put(SORT, sortOrder);
        if (sort!=null){
            if (!delegate.containsKey("sort")){
                delegate.put("sort",new ArrayList<String>());
            }
            ArrayList<String> arr = (ArrayList<String>)delegate.get("sort");
            for (Iterator<Sort.Order> it = sort.iterator(); it.hasNext();){
                Sort.Order order = it.next();
                arr.add(order.getProperty()+","+order.getDirection());
            }
        }
    }
}
