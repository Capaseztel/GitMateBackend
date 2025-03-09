package com.gitmate.gitmatebackend;

import com.gitmate.gitmatebackend.model.Post;
import com.gitmate.gitmatebackend.model.Server;
import com.gitmate.gitmatebackend.model.User;
import com.gitmate.gitmatebackend.service.PostService;
import com.gitmate.gitmatebackend.service.ServerService;
import com.gitmate.gitmatebackend.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class GitMateBackendApplicationTests {

    @Autowired
    UserService userService;
    @Autowired
    PostService postService;
    @Autowired
    ServerService serverService;



    @Test
    void addUsers() {
        String[][] users = {
                {"Jose Miguel", "jose_miguel", "josemi@maricon.com"},
                {"Carlos García", "carlos_garcia", "carlos@correo.com"},
                {"María Fernández", "maria_fernandez", "maria@correo.com"},
                {"Javier López", "javier_lopez", "javier@correo.com"},
                {"Laura Martínez", "laura_martinez", "laura@correo.com"},
                {"Pablo Rodríguez", "pablo_rodriguez", "pablo@correo.com"},
                {"Elena Sánchez", "elena_sanchez", "elena@correo.com"},
                {"Sergio Pérez", "sergio_perez", "sergio@correo.com"},
                {"Lucía Gómez", "lucia_gomez", "lucia@correo.com"},
                {"Alejandro Díaz", "alejandro_diaz", "alejandro@correo.com"},
                {"Isabel Torres", "isabel_torres", "isabel@correo.com"},
                {"David Vázquez", "david_vazquez", "null@gmail.com"}
        };

        User oldUser = null;
        for (String[] userData : users) {
            User user = User.builder()
                    .name(userData[0])
                    .uniqueName(userData[1])
                    .email(userData[2])
                    .password("password")
                    .build();

            user = userService.addUser(user);

            Post post = Post.builder()
                    .title("Título de prueba")
                    .content("Contenido de prueba")
                    .author(user)
                    .build();

            postService.addPost(post);

            if (oldUser != null) {
                Post comment = Post.builder()
                        .title("Título de respuesta")
                        .content("Contenido de respuesta")
                        .author(oldUser)
                        .build();

                comment = postService.addComment(post.getId(), comment);
                Post comment2 = Post.builder()
                        .title("Título de respuesta")
                        .content("Contenido de respuesta")
                        .author(user)
                        .build();

                postService.addComment(comment.getId(), comment2);
            }
            oldUser = user;
        }
    }

    @Test
    void createServer() {
        Server server = serverService.createServer("GitMate");
        serverService.sendMessage(userService.getUserById(1L), server.getChannels().get(1), "Mensaje de prueba Front");

    }
}
