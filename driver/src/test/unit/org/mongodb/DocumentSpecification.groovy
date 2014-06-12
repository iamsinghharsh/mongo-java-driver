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



package org.mongodb

import org.bson.json.JsonParseException
import org.bson.types.BsonRegularExpression
import org.bson.types.ObjectId
import spock.lang.Specification

class DocumentSpecification extends Specification {

    def 'should return correct type for each typed method'() {
        given:
        Date date = new Date();
        ObjectId objectId = new ObjectId();

        when:
        Document doc = new Document()
                .append('int', 1).append('long', 2L).append('double', 3.0 as double).append('string', 'hi').append('boolean', true)
                .append('objectId', objectId).append('date', date);

        then:
        doc.getInteger('int') == 1;
        doc.getLong('long') == 2L;
        doc.getDouble('double') == 3.0;
        doc.getString('string') == 'hi';
        doc.getBoolean('boolean');
        doc.getObjectId('objectId') == objectId;
        doc.getDate('date') == date;
        doc.get('objectId', ObjectId) == objectId;
    }

    def 'should convert valid JSON string to a Document'() {
        when:
        Document document = Document.valueOf("{ 'int' : 1, 'string' : 'abc' }");

        then:
        document != null;
        document.keySet().size() == 2;
        document.getInteger('int') == 1;
        document.getString('string') == 'abc';
    }

    def 'test value of method with mode'() {
        when:
        Document document = Document.valueOf("{'regex' : /abc/im }");

        then:
        document != null;
        document.keySet().size() == 1;

        BsonRegularExpression regularExpression = (BsonRegularExpression) document.get('regex');
        regularExpression.options == 'im'
        regularExpression.pattern == 'abc'
    }

    def 'should throw an exception when parsing an invalid JSON String'() {
        when:
        Document.valueOf("{ 'int' : 1, 'string' : }");

        then:
        thrown(JsonParseException)
    }

    def 'should produce nice JSON when calling toString'() {
        expect:
        new Document('int', 1).append('string', 'abc').toString() == '{ "int" : 1, "string" : "abc" }';
    }

}
