package com.yage.springenv.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("users")
@NoArgsConstructor
public class User {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    @TableField("user_name")
    private String userName;
    @TableField("age")
    private Integer age;

    public User(String userName) {
        this.userName = userName;
        this.age = 18;
    }
}
