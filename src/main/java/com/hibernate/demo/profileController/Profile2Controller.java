package com.hibernate.demo.profileController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wanli zhou
 * @created 2017-10-17 10:08 PM.
 */
@Controller
@Profile("profile2")
public class Profile2Controller {
    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ServiceInterface service;
    @GetMapping("/{id}")
    public ResponseEntity getProfile(@PathVariable("id") Integer id ){
        log.info("=============id={}", id);
        ResultResponse rsp = service.generateRsp(id);
        return ResponseEntity.ok(rsp);

    }
}
