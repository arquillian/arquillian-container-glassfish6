package org.jboss.arquillian.container.glassfish.managed;

import static org.junit.jupiter.api.Assertions.assertEquals;

import jakarta.servlet.annotation.WebServlet;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.jupiter.api.Test;


public abstract class GlassFishManagedDeploymentTestTemplate {

    @ArquillianResource
    private URL deploymentUrl;

    @Test
    public void shouldBeAbleToDeployEnterpriseArchive() throws Exception {
        final String servletPath =
            greeterImplementationBasedOnDerbyEnabled().getAnnotation(WebServlet.class)
                .urlPatterns()[0];

        final URLConnection response = new URL(
            deploymentUrl.toString() + servletPath.substring(1)).openConnection();

        BufferedReader in = new BufferedReader(new InputStreamReader(response.getInputStream()));
        final String result = in.readLine();

        assertEquals("Hello", result);
    }

    static Class<?> greeterImplementationBasedOnDerbyEnabled() {
        if (Boolean.parseBoolean(System.getProperty("enableDerby"))) {
            return GreeterServletWithDerby.class;
        }
        return GreeterServlet.class;
    }
}
