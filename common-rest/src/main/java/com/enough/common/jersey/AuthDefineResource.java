package com.supermap.gaf.jersey;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import com.supermap.gaf.security.tenant.commontypes.AuthDefine;
import com.supermap.gaf.security.tenant.commontypes.Resource;
import com.supermap.gaf.security.tenant.commontypes.RoleDefine;
import com.supermap.gaf.utils.LogUtil;
import com.supermap.gaf.utils.XMLHelper;

/**
 * <p>
 * 提供应用定义默认资源、角色分配展示 
 * </p>
 * @author ${Author}
 * @version ${Version}
 * @since 1.0.0
 *
 */
@Path("/authdefines")
@Component
public class AuthDefineResource {

    public static String authDefinePath = "config/authdefine.xml";

    private static Logger logger = LogUtil.getLocLogger(AuthDefineResource.class);
    
    @Path("/")
    @GET
    @Produces( {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public AuthDefine getAuthDefines(){
        return AuthDefineParser.getAuthDefine(authDefinePath);
    }
    
    @Path("/resources")
    @GET
    @Produces( {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Resource> getAuthResources(){
        List<Resource> resources = new ArrayList<>();
        AuthDefine authDefine = getAuthDefines();
        if(authDefine != null && CollectionUtils.isNotEmpty(resources)) {
            resources.addAll(authDefine.getResources());
        }
        return resources;
    }
    
    @Path("/roles")
    @GET
    @Produces( {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<RoleDefine> queryRoles(){
        List<RoleDefine> roles = new ArrayList<>();
        AuthDefine authDefine = getAuthDefines();
        if(authDefine != null && CollectionUtils.isNotEmpty(roles)) {
            roles.addAll(authDefine.getRoles());
        }
        return roles;
    }
    
    public static class AuthDefineParser{
        
        private static Map<String, AuthDefine> authDefines = new HashMap<>();
        
        public static AuthDefine getAuthDefine(String config) {
            if(StringUtils.isEmpty(config)) {
                return null;
            }
            if(!authDefines.containsKey(config)) {
                AuthDefine defineInfo = null;
                try (InputStream ins = getFileInputStream(config)){
                    defineInfo = XMLHelper.deserializeXML(ins, AuthDefine.class);
                } catch (Exception e) {
                    logger.debug("授权定义信息解析失败:", e);
                }
                if(defineInfo == null) {
                    logger.info("授权定义信息解析失败:" + config);
                }
                authDefines.put(config, defineInfo);
            }
            return authDefines.get(config);
        }
        
        private static InputStream getFileInputStream(String relativelyPath){
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = loader.getResourceAsStream(relativelyPath);
            if (inputStream == null) {
                File resourceFile = new File(relativelyPath);
                if (resourceFile.exists()) {
                    try {
                        inputStream = new FileInputStream(resourceFile);
                    } catch (FileNotFoundException e) {
                    }
                }
            }
            return inputStream;
        }
    }
}
