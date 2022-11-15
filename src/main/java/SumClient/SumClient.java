package SumClient;

import com.proto.Sum.SumRequest;
import com.proto.Sum.SumResponse;
import com.proto.Sum.SumServiceGrpc;
import com.proto.dummy.DummyServiceGrpc;
import com.proto.Sum.Sum;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class SumClient {
    public static void main(String[] args){
        System.out.println("Hello gRPC Client");
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 55554)
                .usePlaintext()
                .build();
        System.out.println("Creating stub");
        DummyServiceGrpc.DummyServiceBlockingStub syncClient
                = DummyServiceGrpc.newBlockingStub(channel);

        // created a greet service client (blocking - synchronous)
        SumServiceGrpc.SumServiceBlockingStub sumClient;
        sumClient = SumServiceGrpc.newBlockingStub(channel);
        // created a protocol buffer greeting message
       Sum sum = Sum.newBuilder()
                .setNum1(5)
                .setNum2(10)
                .build();
        // created a protocol buffer greetRequest message
        SumRequest sumRequest = SumRequest.newBuilder()
                .setSum(sum)
                .build();
        // call the RPC and get back a GreetResponse (Protocol Buffers)
        SumResponse sumResponse = sumClient.sum(sumRequest);
        // show the result in GreetResponse message
        System.out.println(sumResponse.getResult());

        System.out.println("Shutting down channel");
        channel.shutdown();
    }
}
