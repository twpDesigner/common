package com.twp.common.basic;

import com.twp.common.vo.BaseVo;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;
import java.io.Serializable;

public class IdOrGenerate extends IdentityGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor s, Object obj) {
        if (obj == null){
            throw new HibernateException(new NullPointerException()) ;
        }
        if ((((BaseVo) obj).getId()) == null) {//id is null it means generate ID
            Serializable id = super.generate(s, obj) ;
            return id;
        } else {
            return ((BaseVo) obj).getId();//id is not null so using assigned id.

        }
    }
}