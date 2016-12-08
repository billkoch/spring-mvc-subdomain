package io.billkoch.springextensions.mvc.bind.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class SubdomainResolver implements HandlerMethodArgumentResolver {

    private final SubdomainExtractor subdomainExtactor;

    public SubdomainResolver(SubdomainExtractor subdomainExtactor) {
        this.subdomainExtactor = subdomainExtactor;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(Subdomain.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String serverName = webRequest.getNativeRequest(HttpServletRequest.class).getServerName();
        return subdomainExtactor.extract(serverName);
    }
}
