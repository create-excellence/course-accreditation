package com.excellent.accreditation.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;
}
