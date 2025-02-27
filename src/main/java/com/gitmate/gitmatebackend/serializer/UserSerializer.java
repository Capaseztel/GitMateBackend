package com.gitmate.gitmatebackend.serializer;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.gitmate.gitmatebackend.model.User;

import java.io.IOException;

public class UserSerializer extends StdSerializer<User> {

    public UserSerializer() {
        this(null);
    }

    public UserSerializer(Class<User> t) {
        super(t);
    }

    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", user.getId());
        jsonGenerator.writeStringField("name", user.getName());
        jsonGenerator.writeStringField("uniqueName", user.getUniqueName());
        jsonGenerator.writeStringField("email", user.getEmail());
        jsonGenerator.writeStringField("password", user.getPassword());

        jsonGenerator.writeEndObject();
    }
}
