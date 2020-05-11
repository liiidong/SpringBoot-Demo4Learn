package com.supermap.gaf.jersey;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.supermap.gaf.rest.utils.JerseyResourceScanner;

/**
 * <p>
 * 动态扫描当前环境下的Jsersey资源定义，返回URL资源管理 
 * </p>
 * @author ${Author}
 * @version ${Version}
 * @since 1.0.0
 *
 */
@Path("/scanedapis")
public class APIScanerResource {

	@GET
    @Produces( MediaType.APPLICATION_JSON)
    public List<String> get(@QueryParam("package") String packageName, @QueryParam("rooturl") String rootUrl) {
		return new JerseyResourceScanner().scan(packageName, rootUrl);
    }
}
