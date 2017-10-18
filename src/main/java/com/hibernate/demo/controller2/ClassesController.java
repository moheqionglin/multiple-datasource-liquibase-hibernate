package com.hibernate.demo.controller2;

import com.hibernate.demo.domain2.Classes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wanli zhou
 * @created 2017-10-09 11:00 PM.
 */
@Controller
@RequestMapping(path = "/classes/", consumes = "application/json", produces = "application/json")
public class ClassesController {

    private final ClassesService classesService;

    @Autowired
    public ClassesController(ClassesService classesService){
        this.classesService = classesService;
    }

    //localhost:13003/spring/classes/id/1
    @GetMapping("/id/{id}")
    public ResponseEntity getClassesById(@PathVariable("id") Integer id){
        Classes cs = classesService.getClassesById(id);
        System.out.println(cs);
        return ResponseEntity.ok(cs);
    }
}
