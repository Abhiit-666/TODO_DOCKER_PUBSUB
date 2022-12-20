package com.example.demo;

import java.io.*;

import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.NoCredentialsProvider;
import com.google.api.gax.grpc.GrpcTransportChannel;
import com.google.api.gax.rpc.FixedTransportChannelProvider;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.cloud.pubsub.v1.TopicAdminClient;
import com.google.cloud.pubsub.v1.TopicAdminSettings;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/* public class MySubscriber {
    /*
     * public Sub(){
     * super(ProjectSubscriptionName.of("TODO_DOCKER_PUBSUB", "test-sub"), new
     * MessageReceiver() {
     * 
     * @Override
     * public void receiveMessage(com.google.pubsub.v1.PubsubMessage message,
     * AckReplyConsumer consumer) {
     * System.out.println("Message Id: " + message.getMessageId());
     * System.out.println("Data: " + message.getData().toStringUtf8());
     * consumer.ack();
     * }
     * })
     * }
     * 
     
    String a = "";

    public String Subs() throws IOException {

        String hostport = System.getenv("PUBSUB_EMULATOR_HOST");
        System.out.println(hostport);
        ManagedChannel channel = ManagedChannelBuilder.forTarget(hostport).usePlaintext().build();

        try {
            TransportChannelProvider channelProvider = FixedTransportChannelProvider
                    .create(GrpcTransportChannel.create(channel));
            CredentialsProvider credentialsProvider = NoCredentialsProvider.create();

            TopicAdminClient topicAdminClient = TopicAdminClient.create(
                    TopicAdminSettings.newBuilder()
                            .setTransportChannelProvider(channelProvider)
                            .setCredentialsProvider(credentialsProvider).build());

            // TopicName topicName = TopicName.of("TODO_DOCKER_PUBSUB", "test-sub");

            ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.of("TODO_DOCKER_PUBSUB", "test-sub");
            MessageReceiver receiver = new MessageReceiver() {
                @Override
                public void receiveMessage(com.google.pubsub.v1.PubsubMessage message, AckReplyConsumer consumer) {
                    System.out.println("Message Id: " + message.getMessageId());
                    System.out.println("Data: " + message.getData().toStringUtf8());
                    a = message.getData().toStringUtf8();
                    consumer.ack();
                }
            };
            // create the subscriber
            Subscriber subscriber = Subscriber.newBuilder(subscriptionName, receiver)
                    .setChannelProvider(channelProvider)
                    .setCredentialsProvider(credentialsProvider).build();

            subscriber.startAsync().awaitRunning();

        } finally {
            channel.shutdown();
        }

        return a;
    }
} */

public class MySubscriber {
    public Subscriber subscriber;

    public MySubscriber() throws IOException {
        String hostport = System.getenv("PUBSUB_EMULATOR_HOST");
        System.out.println(hostport);
        ManagedChannel channel = ManagedChannelBuilder.forTarget(hostport).usePlaintext().build();
        try {
            TransportChannelProvider channelProvider = FixedTransportChannelProvider
                    .create(GrpcTransportChannel.create(channel));
            CredentialsProvider credentialsProvider = NoCredentialsProvider.create();

            /*
             * TopicAdminClient topicAdminClient = TopicAdminClient.create(
             * TopicAdminSettings.newBuilder()
             * .setTransportChannelProvider(channelProvider)
             * .setCredentialsProvider(credentialsProvider).build());
             */

            ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.of("TODO_DOCKER_PUBSUB", "test-sub");
            MessageReceiver receiver = new MessageReceiver() {
                @Override
                public void receiveMessage(PubsubMessage message, AckReplyConsumer consumer) {
                    // Process the message
                    System.out.println("Message Id: " + message.getMessageId());
                    System.out.println("Data: " + message.getData().toStringUtf8());
                    consumer.ack();
                }
            };
            this.subscriber = Subscriber.newBuilder(subscriptionName, receiver)
                    .setChannelProvider(channelProvider)
                    .setCredentialsProvider(credentialsProvider).build();

        } finally {
            channel.shutdown();
        }
    }
}