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

package org.apache.ignite.internal.storage.pagememory.configuration.schema;

import static org.apache.ignite.internal.storage.pagememory.PageMemoryStorageEngine.ENGINE_NAME;
import static org.apache.ignite.internal.storage.pagememory.configuration.schema.PageMemoryStorageEngineConfigurationSchema.DEFAULT_DATA_REGION_NAME;

import org.apache.ignite.configuration.annotation.PolymorphicConfigInstance;
import org.apache.ignite.configuration.annotation.Value;
import org.apache.ignite.configuration.schemas.store.DataStorageConfigurationSchema;
import org.apache.ignite.internal.storage.pagememory.PageMemoryStorageEngine;
import org.apache.ignite.internal.storage.pagememory.configuration.PageMemoryDataRegionName;

/**
 * Data storage configuration for {@link PageMemoryStorageEngine}.
 */
@PolymorphicConfigInstance(ENGINE_NAME)
public class PageMemoryDataStorageConfigurationSchema extends DataStorageConfigurationSchema {
    /** Data region. */
    @Value(hasDefault = true)
    @PageMemoryDataRegionName
    public String dataRegion = DEFAULT_DATA_REGION_NAME;
}
