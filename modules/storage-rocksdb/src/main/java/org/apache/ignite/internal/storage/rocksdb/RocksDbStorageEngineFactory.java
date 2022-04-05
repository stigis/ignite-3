/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ignite.internal.storage.rocksdb;

import java.nio.file.Path;
import org.apache.ignite.internal.configuration.ConfigurationRegistry;
import org.apache.ignite.internal.storage.StorageException;
import org.apache.ignite.internal.storage.engine.StorageEngine;
import org.apache.ignite.internal.storage.engine.StorageEngineFactory;
import org.apache.ignite.internal.storage.rocksdb.configuration.schema.RocksDbStorageEngineConfiguration;

/**
 * Implementation for creating {@link RocksDbStorageEngine}s.
 */
public class RocksDbStorageEngineFactory implements StorageEngineFactory {
    /** {@inheritDoc} */
    @Override
    public StorageEngine createEngine(ConfigurationRegistry configRegistry, Path storagePath) throws StorageException {
        RocksDbStorageEngineConfiguration engineConfig = configRegistry.getConfiguration(RocksDbStorageEngineConfiguration.KEY);

        assert engineConfig != null;

        return new RocksDbStorageEngine(engineConfig, storagePath);
    }
}
