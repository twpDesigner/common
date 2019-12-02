package com.twp.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseVo {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "idOrGenerate")
        @GenericGenerator(name = "idOrGenerate", strategy = "com.twp.common.basic.IdOrGenerate")
        private Integer id;

        @Column(length = 40)
        private String createUser;

        @Column(length = 40)
        private String updateUser;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date createTime;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date updateTime;

}


