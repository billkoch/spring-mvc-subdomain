package io.billkoch.springextensions.mvc.bind.annotation;

import com.google.common.net.InternetDomainName;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SubdomainExtractor {

    public String extract(String serverName) {
        String subdomain = null;
        InternetDomainName from = InternetDomainName.from(serverName);
        if(from.isUnderPublicSuffix()) {
            String domain = from.topPrivateDomain().toString();
            subdomain =  serverName.equals(domain) ? null : serverName.replace("." + domain, "");
        }
        log.debug("Extracted the subdomain '{}' from the server name '{}'");
        return subdomain;
    }
}
