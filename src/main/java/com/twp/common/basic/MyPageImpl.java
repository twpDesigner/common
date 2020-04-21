package com.twp.common.basic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by tanweiping on 17/8/8.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyPageImpl<T> {
//    "content": [],
//            "last": true,
//            "totalElements": 0,
//            "totalPages": 0,
//            "size": 5,
//            "number": 1,
//            "sort": [
//    {
//        "direction": "DESC",
//            "property": "id",
//            "ignoreCase": false,
//            "nullHandling": "NATIVE",
//            "descending": true,
//            "ascending": false
//    }
//        ],
//                "first": false,
//                "numberOfElements": 0


    public MyPageImpl(List<T> content) {
        this.content = content;
    }

    private List<T> content;
    private boolean last;

    private int totalElements;
    private int totalPages;

    private int size;

    private int number;

    //private Sort sort;
    private boolean first;
    private boolean empty;

    private int numberOfElements;


}
