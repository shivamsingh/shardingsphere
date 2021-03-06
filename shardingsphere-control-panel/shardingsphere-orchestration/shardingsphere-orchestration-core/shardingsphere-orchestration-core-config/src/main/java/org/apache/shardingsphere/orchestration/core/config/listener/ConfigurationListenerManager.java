/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.orchestration.core.config.listener;

import org.apache.shardingsphere.orchestration.repository.api.ConfigurationRepository;
import org.apache.shardingsphere.orchestration.repository.api.listener.DataChangedEvent.ChangedType;

import java.util.Collection;

/**
 * Configuration listener manager.
 */
public final class ConfigurationListenerManager {
    
    private final SchemaChangedListener schemaChangedListener;
    
    private final PropertiesChangedListener propertiesChangedListener;
    
    private final AuthenticationChangedListener authenticationChangedListener;
    
    private final MetricsConfigurationChangedListener metricsConfigurationChangedListener;
    
    private final ClusterConfigurationChangedListener clusterConfigurationChangedListener;
    
    public ConfigurationListenerManager(final String name, final ConfigurationRepository configurationRepository, final Collection<String> schemaNames) {
        schemaChangedListener = new SchemaChangedListener(name, configurationRepository, schemaNames);
        propertiesChangedListener = new PropertiesChangedListener(name, configurationRepository);
        authenticationChangedListener = new AuthenticationChangedListener(name, configurationRepository);
        metricsConfigurationChangedListener = new MetricsConfigurationChangedListener(name, configurationRepository);
        clusterConfigurationChangedListener = new ClusterConfigurationChangedListener(name, configurationRepository);
    }
    
    /**
     * Initialize all configuration changed listeners.
     */
    public void initListeners() {
        schemaChangedListener.watch(ChangedType.UPDATED, ChangedType.DELETED);
        propertiesChangedListener.watch(ChangedType.UPDATED);
        authenticationChangedListener.watch(ChangedType.UPDATED);
        metricsConfigurationChangedListener.watch(ChangedType.UPDATED);
        clusterConfigurationChangedListener.watch(ChangedType.UPDATED);
    }
}
