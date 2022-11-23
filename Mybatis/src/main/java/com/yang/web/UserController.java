package com.yang.web;


import com.yang.aspect.MethodArgumentException;
import com.yang.fo.UserFo;
import com.yang.fo.ValidList;
import com.yang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public String insertOne(@Valid @RequestBody UserFo userFo, BindingResult bindingResult) throws MethodArgumentException {
        ArrayList<UserFo> userFos = new ArrayList<>();
        userFos.add(userFo);
        userService.insertList(userFos);
        if (bindingResult.hasErrors()) {
            throw new MethodArgumentException(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        return "添加成功";
    }


    @PostMapping("/insertList")
    public String insertList(@RequestBody @Valid ValidList<UserFo> userFos) {
        userService.insertList(userFos);

        return "添加成功";
    }
}
