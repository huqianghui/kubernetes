/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.client.loadbalancer;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Olga Maciaszek-Sharma
 * @since 2.2.0
 */
public final class LoadBalancerUriTools {

    private LoadBalancerUriTools() {
        throw new IllegalStateException("Can't instantiate a utility class");
    }

    private static final String PERCENTAGE_SIGN = "%";

    private static final String DEFAULT_SCHEME = "http";

    private static final String DEFAULT_SECURE_SCHEME = "https";

    private static final Map<String, String> INSECURE_SCHEME_MAPPINGS;

    static {
        INSECURE_SCHEME_MAPPINGS = new HashMap<>();
        INSECURE_SCHEME_MAPPINGS.put(DEFAULT_SCHEME, DEFAULT_SECURE_SCHEME);
        INSECURE_SCHEME_MAPPINGS.put("ws", "wss");
    }

    // see original
    // https://github.com/spring-cloud/spring-cloud-gateway/blob/master/spring-cloud-gateway-core/
    // src/main/java/org/springframework/cloud/gateway/support/ServerWebExchangeUtils.java
    private static boolean containsEncodedParts(URI uri) {
        boolean encoded = (uri.getRawQuery() != null
                && uri.getRawQuery().contains(PERCENTAGE_SIGN))
                || (uri.getRawPath() != null
                && uri.getRawPath().contains(PERCENTAGE_SIGN))
                || (uri.getRawFragment() != null
                && uri.getRawFragment().contains(PERCENTAGE_SIGN));
        // Verify if it is really fully encoded. Treat partial encoded as unencoded.
        if (encoded) {
            try {
                UriComponentsBuilder.fromUri(uri).build(true);
                return true;
            }
            catch (IllegalArgumentException ignore) {
            }
            return false;
        }
        return false;
    }

    private static int computePort(int port, String scheme) {
        if (port >= 0) {
            return port;
        }
        if (Objects.equals(scheme, DEFAULT_SECURE_SCHEME)) {
            return 443;
        }
        return 80;
    }

    /**
     * Modifies the URI in order to redirect the request to a service instance of choice.
     * @param serviceInstance the {@link ServiceInstance} to redirect the request to.
     * @param original the {@link URI} from the original request
     * @return the modified {@link URI}
     */
    public static URI reconstructURI(ServiceInstance serviceInstance, URI original) {
        if (serviceInstance == null) {
            throw new IllegalArgumentException("Service Instance cannot be null.");
        }
        return doReconstructURI(serviceInstance, original);
    }

    private static URI doReconstructURI(ServiceInstance serviceInstance, URI original) {
        String host = serviceInstance.getHost();
        System.out.println("originalURI:" + original);
        System.out.println("***host:" + host);
        String scheme = Optional.ofNullable(serviceInstance.getScheme())
                .orElse(computeScheme(original, serviceInstance));
        System.out.println("***scheme:" + scheme);
        int port = computePort(serviceInstance.getPort(), scheme);
        System.out.println("***port:" + port);

        if (Objects.equals(host, original.getHost()) && port == original.getPort()
                && Objects.equals(scheme, original.getScheme())) {
            return original;
        }

        boolean encoded = containsEncodedParts(original);
        System.out.println("***encoded11:" + encoded);

        UriComponents test1 = UriComponentsBuilder.fromUri(original).scheme(scheme).host(host).port(port)
                .build(encoded);
        System.out.println("test1:" + test1);
        System.out.println("**before to url>>>>>");
        // System.out.println(test1.toUri());
        System.out.println("**after to url>>>>>" + original.getPath());
        //URI test =  test1.toUri();
        try{
            System.out.println("1: " + UriComponentsBuilder.newInstance().scheme(scheme).toUriString());
            System.out.println("2: " + UriComponentsBuilder.newInstance().scheme(scheme).host(host).toUriString());
            System.out.println("3: " + UriComponentsBuilder.newInstance().scheme(scheme).host(host).port(port).toUriString());

            URI test = UriComponentsBuilder.newInstance().scheme(scheme).host(host).port(port)
                    .build(encoded).toUri();
        }catch (Exception e){
            System.out.println("e11:" + e.toString());
            System.out.println("after e11 return :   " + scheme + host + ":" + port + original.getPath());
            return URI.create(scheme + host + ":" + port + original.getPath());
        }
        URI test = UriComponentsBuilder.newInstance().scheme(scheme).host(host).port(port)
                .build(encoded).toUri();
        System.out.println("***test11:" + test);

        return test;
    }

    private static String computeScheme(URI original, ServiceInstance serviceInstance) {
        String originalOrDefault = Optional.ofNullable(original.getScheme())
                .orElse(DEFAULT_SCHEME);
        if (serviceInstance.isSecure()
                && INSECURE_SCHEME_MAPPINGS.containsKey(originalOrDefault)) {
            return INSECURE_SCHEME_MAPPINGS.get(originalOrDefault);
        }
        return originalOrDefault;
    }

}
