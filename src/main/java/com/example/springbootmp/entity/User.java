package com.example.springbootmp.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user")
public class User {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField("username")
    private String username;
    @TableField("password")
    private String password;
    @TableField("nick_name")
    private String nickname;
    @TableField("address")
    private String address;
    @TableField("email")
    private String email;
    @TableField("birthday")
    private LocalDateTime birthday;
    @TableField("age")
    private Integer age;
    /**
     * 自动填充策略：插入时填充
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;
    /**
     * 自动填充策略：插入或更新时填充
     */
    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date updateTime;

    /**
     * value = "0" 逻辑未删除值
     * delval = "1" 逻辑删除值
     */
    @TableLogic(value = "0",delval = "1")
    @TableField(value = "delete_flag",fill = FieldFill.INSERT)
    private int delete_flag;
}
