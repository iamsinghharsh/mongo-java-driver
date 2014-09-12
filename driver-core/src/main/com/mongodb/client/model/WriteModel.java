/*
 * Copyright (c) 2008-2014 MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mongodb.client.model;

/**
 * A base class for models that can be used in a bulk write operations.
 *
 * @param <T> the document type for storage
 * @param <D> the document type for other documents describing the write, such as criteria, update, etc
 * @since 3.0
 * @see BulkWriteModel
 */
public abstract class WriteModel<T, D> {
    WriteModel() {
    }
}
