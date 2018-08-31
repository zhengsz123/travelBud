package com.travel.core;



import com.travel.core.domain.Authority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Utils {
    public static Collection<GrantedAuthority> getAuthorities(List<Authority> authorities) {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();

        for (Authority auth : authorities){
            String ROLE = auth.getAuthorities().toUpperCase();
            authList.add(new SimpleGrantedAuthority(ROLE));
        }

        return authList;
    }

    public  static File multipartToFile(MultipartFile multipart, String fileName) throws IllegalStateException, IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+fileName);
        multipart.transferTo(convFile);
        return convFile;
    }
}
