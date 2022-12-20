package com.example.client;

import java.io.IOException;

import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.NoCredentialsProvider;
import com.google.api.gax.grpc.GrpcTransportChannel;
import com.google.api.gax.rpc.FixedTransportChannelProvider;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.cloud.pubsub.v1.TopicAdminClient;
import com.google.cloud.pubsub.v1.TopicAdminSettings;
import com.google.pubsub.v1.TopicName;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class pub {
    public void publish(int message) throws IOException {
        String hostport = System.getenv("PUBSUB_EMULATOR_HOST");
        System.out.print(hostport);
        ManagedChannel channel = ManagedChannelBuilder.forTarget(hostport).usePlaintext().build();

        try {
            TransportChannelProvider channelProvider = FixedTransportChannelProvider
                    .create(GrpcTransportChannel.create(channel));
            CredentialsProvider credentialsProvider = NoCredentialsProvider.create();

            TopicAdminClient topicAdminClient = TopicAdminClient.create(
                    TopicAdminSettings.newBuilder()
                            .setTransportChannelProvider(channelProvider)
                            .setCredentialsProvider(credentialsProvider).build());

            TopicName topicName = TopicName.of("TODO_DOCKER_PUBSUB", "test-pub");
            Publisher publisher = Publisher.newBuilder(topicName).setChannelProvider(channelProvider)
                    .setCredentialsProvider(credentialsProvider).build();

        } finally {
            channel.shutdown();
        }
    }
}
