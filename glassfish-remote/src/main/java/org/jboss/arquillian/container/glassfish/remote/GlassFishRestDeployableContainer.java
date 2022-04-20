/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * @author <a href="http://community.jboss.org/people/LightGuard">Jason Porter</a>
 */
package org.jboss.arquillian.container.glassfish.remote;

import org.jboss.arquillian.container.glassfish.CommonGlassFishConfiguration;
import org.jboss.arquillian.container.glassfish.CommonGlassFishManager;
import org.jboss.arquillian.container.spi.client.container.DeployableContainer;
import org.jboss.arquillian.container.spi.client.container.DeploymentException;
import org.jboss.arquillian.container.spi.client.container.LifecycleException;
import org.jboss.arquillian.container.spi.client.protocol.ProtocolDescription;
import org.jboss.arquillian.container.spi.client.protocol.metadata.ProtocolMetaData;
import org.jboss.arquillian.protocol.servlet5.v_5.ServletProtocol;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.descriptor.api.Descriptor;

import java.util.logging.Logger;

/**
 * Glassfish v6.x remote container using REST deployment.
 *
 * @author <a href="http://community.jboss.org/people/LightGuard">Jason Porter</a>
 */
public class GlassFishRestDeployableContainer implements DeployableContainer<CommonGlassFishConfiguration> {

    private CommonGlassFishConfiguration configuration;
    private CommonGlassFishManager<CommonGlassFishConfiguration> glassFishManager;

    private static final Logger log = Logger.getLogger(GlassFishRestDeployableContainer.class.getName());

    public Class<CommonGlassFishConfiguration> getConfigurationClass() {
        return CommonGlassFishConfiguration.class;
    }

    public void setup(CommonGlassFishConfiguration configuration) {
        if (configuration == null) {
            throw new IllegalArgumentException("configuration must not be null");
        }
        this.configuration = configuration;
        this.glassFishManager = new CommonGlassFishManager<CommonGlassFishConfiguration>(configuration);
    }

    public void start() throws LifecycleException {
        glassFishManager.start();
    }

    public void stop() throws LifecycleException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public ProtocolDescription getDefaultProtocol() {
        return new ProtocolDescription(ServletProtocol.PROTOCOL_NAME);
    }

    public ProtocolMetaData deploy(Archive<?> archive) throws DeploymentException {
        return glassFishManager.deploy(archive);
    }

    public void undeploy(Archive<?> archive) throws DeploymentException {
        glassFishManager.undeploy(archive);
    }

    public void deploy(Descriptor descriptor) throws DeploymentException {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void undeploy(Descriptor descriptor) throws DeploymentException {
        throw new UnsupportedOperationException("Not implemented");
    }
}
