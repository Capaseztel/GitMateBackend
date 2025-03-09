package com.gitmate.gitmatebackend.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.gitmate.gitmatebackend.model.Post;

import java.io.IOException;

public class PostSerializer extends StdSerializer<Post> {

    protected PostSerializer() {
        this(null);
    }

    protected PostSerializer(Class<Post> t) {
        super(t);
    }

    @Override
    public void serialize(Post post, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (post.getParent() == null) {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", post.getId());
        jsonGenerator.writeStringField("title", post.getTitle());
        jsonGenerator.writeStringField("content", post.getContent());
        jsonGenerator.writeFieldName("author");
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", post.getAuthor().getId());
            jsonGenerator.writeStringField("name", post.getAuthor().getName());
            jsonGenerator.writeStringField("uniqueName", post.getAuthor().getUniqueName());
            jsonGenerator.writeEndObject();
        printComment(post, jsonGenerator);
        jsonGenerator.writeEndObject();
        }
    }

    public void printComment(Post post, JsonGenerator jsonGenerator) throws IOException {
        if (!post.getComments().isEmpty()) {
            jsonGenerator.writeArrayFieldStart("comments");
            for (Post comment : post.getComments()) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeNumberField("id", comment.getId());
                jsonGenerator.writeStringField("title", comment.getTitle());
                jsonGenerator.writeStringField("content", comment.getContent());
                jsonGenerator.writeFieldName("author");
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeNumberField("id", comment.getAuthor().getId());
                    jsonGenerator.writeStringField("name", comment.getAuthor().getName());
                    jsonGenerator.writeStringField("uniqueName", comment.getAuthor().getUniqueName());
                    jsonGenerator.writeEndObject();
                printComment(comment, jsonGenerator);
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();
        }
    }

}
