package com.hibernate.demo.profileController;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author wanli zhou
 * @created 2017-10-17 10:10 PM.
 */
@Service
@Profile("profile1")
public class Profile1Service implements ServiceInterface{
    @Override
    public ResultResponse generateRsp(Integer code) {
        return new ResultResponse("" + code,  "profile1");
    }
}