package com.yang.fo;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class UserFo {


    private Integer age;

    @Pattern(regexp = "^(0|([1-9][0-9]?))$", message = "年龄格式错误")
    private String ageStr;

    public void setAgeStr(String ageStr) {
        this.age = Integer.parseInt(ageStr);
        this.ageStr = ageStr;
    }


    @NotEmpty(message = "姓名不能为空")
    private String name;

    @NotEmpty(message = "地址不能为空")
    private String address;

    @NotNull(message = "创建时间不能为空")
    private Date createTime;

}