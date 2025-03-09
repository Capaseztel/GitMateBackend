package com.gitmate.gitmatebackend.service;

import com.gitmate.gitmatebackend.Repositories.ChannelRepo;
import com.gitmate.gitmatebackend.Repositories.MessagesRepo;
import com.gitmate.gitmatebackend.Repositories.ServerRepo;
import com.gitmate.gitmatebackend.model.Channel;
import com.gitmate.gitmatebackend.model.Message;
import com.gitmate.gitmatebackend.model.Server;
import com.gitmate.gitmatebackend.model.User;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ServerService {

    private ServerRepo serverRepo;
    private ChannelRepo channelRepo;
    private MessagesRepo messagesRepo;

    public Server createServer(String serverName) {
        // Default Channels
        List<Channel> channels = new ArrayList<>();
        channels.add(createChannel("general"));
        channels.add(createChannel("FrontEnd"));
        channels.add(createChannel("BackEnd"));
        return serverRepo.save(Server.builder().name(serverName).channels(channels).build());
    }

    public Channel createChannel(String channelName) {
        return channelRepo.save(Channel.builder().name(channelName).build());
    }

    public Channel addChannel(String channelName, Server server) {
        Channel channel = createChannel(channelName);
        server.addChannel(channel);
        serverRepo.save(server);
        return channel;
    }

    public Channel getChannel(Long id) {
        return channelRepo.findById(id).orElse(null);
    }

    public Server getServer(Long id) {
        return serverRepo.findById(id).orElse(null);
    }

    public Message sendMessage(User user, Channel channel, String content) {
        Message message = messagesRepo.save(Message.builder().sender(user).content(content).build());
        channel.addMessage(message);
        channelRepo.save(channel);
        return message;
    }
}
