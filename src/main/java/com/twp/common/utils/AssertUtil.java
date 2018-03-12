/*    
 * 五星电器公司拥有完全的版权   
 * 使用者必须经过许可
 *----------------------------------------------------------------------*
 * Copyright  (c) 2016 FiveStar Co.,Ltd. All rights reserved
 * Author       : FiveStar Development
 * Description  : AssertUtil.java
 *----------------------------------------------------------------------*
 * Change-History: Change history
 * Developer  Date      Description
 * shidong  2017年5月24日 Short description containing Message, Note ID or CR ID
 *----------------------------------------------------------------------*  
 */
package com.twp.common.utils;



/**
 * 断言util
 *
 * @since JDK 1.8
 */
public class AssertUtil {
    public static void isNull(Object object, String message) throws Exception {
        if (object == null) {
            throw new Exception(message);
        }
    }

    public static void isNotNull(Object object, String message) throws Exception {
        if (object != null) {
            throw new Exception(message);
        }
    }
}
