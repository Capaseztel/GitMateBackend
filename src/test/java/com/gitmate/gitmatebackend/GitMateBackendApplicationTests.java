package com.gitmate.gitmatebackend;

import com.gitmate.gitmatebackend.Repositories.PostRepo;
import com.gitmate.gitmatebackend.Repositories.UserRepo;
import com.gitmate.gitmatebackend.model.Post;
import com.gitmate.gitmatebackend.model.User;
import jakarta.transaction.Transaction;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class GitMateBackendApplicationTests {

    @Autowired
    UserRepo userRepo;
    @Autowired
    PostRepo postRepo;

    @Test
    void addUsers() {
        String[][] users = {
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
                    .UniqueName(userData[1])
                    .email(userData[2])
                    .password("password")
                    .build();

            user = userRepo.save(user);
            oldUser = user;

            Post post = Post.builder()
                    .title("Título de prueba")
                    .content("Contenido de prueba")
                    .author(user)
                    .build();

            if (oldUser != null) {
                Post comment = Post.builder()
                        .title("Comentario")
                        .content("Contenido de comentario")
                        .author(oldUser)
                        .build();

                comment = postRepo.save(comment);
                post.addComment(comment);

            }

            postRepo.save(post);
            user.addPost(post);
            userRepo.save(user);
        }
    }




}
