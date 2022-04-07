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

package org.apache.ignite.lang;

import java.util.UUID;

/**
 * Exception is thrown when appropriate index is not found.
 */
public class IndexNotFoundException extends IgniteException {
    /**
     * Create a new exception with given index name.
     *
     * @param indexName Index canonical name.
     */
    public IndexNotFoundException(String indexName) {
        super(IgniteStringFormatter.format("Index '{}' does not exist.", indexName));
    }

    /**
     * Create a new exception with given index name.
     *
     * @param indexName Index canonical name.
     * @param id Index id.
     */
    public IndexNotFoundException(String indexName, UUID id) {
        super(IgniteStringFormatter.format("Index '{}' was concurrently modified or deleted, id='{}'.", indexName, id));
    }
}
