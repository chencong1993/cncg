package com.mycxf.webservice;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.mycxf.adapter.MapAdapter;
import com.mycxf.entity.Role;
import com.mycxf.entity.User;

@WebService
public interface HelloWorld {

    public String say(String str);
    
    public List<Role> getRoleByUser(User user);
    
    /**
     * 获取所有用户以及对应的角色
     * @return
     */
    @XmlJavaTypeAdapter(MapAdapter.class)
    public Map<String,List<Role>> getRoles();
}
