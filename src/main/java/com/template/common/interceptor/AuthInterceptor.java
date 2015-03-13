package com.template.common.interceptor;

import com.template.common.beetl.utils.BeetlUtils;
import com.template.common.constant.Constant;
import com.template.web.sys.model.SysResource;
import com.template.web.sys.model.SysUser;
import com.template.web.sys.utils.SysUserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AuthInterceptor implements HandlerInterceptor {

    private Set<String> ignorePath = new HashSet<String>(Arrays.asList("/login", "/code.image", "/notlogin", "/ErrorHandler"));
    private String ignorePathReg = ".+/(login|code.image|notlogin|ErrorHandler)";

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        String url = request.getRequestURI(); //请求路径
        String rootPath = BeetlUtils.getBeetlSharedVars("rootPath");
        boolean isEmpty = StringUtils.isEmpty(rootPath);

        //是否是容器默认servlet
        /*if(handler instanceof DefaultServletHttpRequestHandler) {
			return false;
		}*/

        String path = "";
        int len = 0;
        if (!isEmpty) {
            len = url.indexOf(rootPath) + rootPath.length() + 1;
        }
        if (len <= url.length()) {
            path = url.substring(len, url.length());
        }

        if ((isEmpty&&!ignorePath.contains(path))||(!isEmpty&&!url.matches(ignorePathReg))) {
            //获得session中的登陆用户
            SysUser sessionUser = SysUserUtils.getSessionLoginUser();

            //获得缓存中的登陆用户
            SysUser cacheUser = SysUserUtils.getCacheLoginUser();

            if (sessionUser == null || cacheUser == null) { // 转到登陆页面
                response.sendRedirect(rootPath + "/notlogin");
                return false;
            } else {
                Map<String, SysResource> allRes = BeetlUtils
                        .getBeetlSharedVars(Constant.CACHE_ALL_RESOURCE);
                SysResource sysResource = allRes.get(path);
                if (sysResource == null
                        || Constant.RESOURCE_COMMON.equals(sysResource.getCommon())) {
                    return true;
                }
                //检测用户认证是否改变，如果认证改变则重置，否则不进行任何操作
                SysUserUtils.setUserAuth();
                //从缓存中的用户权限
                Map<String, SysResource> userRes = SysUserUtils.getUserResources();
                if (userRes.containsKey(path)) {
                    return true;
                } else {
                    response.sendRedirect(rootPath + "/notauth");
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

}
