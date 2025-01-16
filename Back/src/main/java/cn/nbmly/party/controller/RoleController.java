package cn.nbmly.party.controller;

import cn.nbmly.party.model.Result;
import cn.nbmly.party.model.Role;
import cn.nbmly.party.service.Imp.RoleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/role")
@RestController
public class RoleController {
    @Autowired
    private RoleServiceImp roleServiceImp;

    @GetMapping("/getList")
    public Result<List<Role>> getList(){
        return Result.success(roleServiceImp.selectAll());
    }
}
