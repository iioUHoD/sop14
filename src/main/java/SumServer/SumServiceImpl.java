package SumServer;

import com.proto.Sum.*;
import io.grpc.stub.StreamObserver;
public class SumServiceImpl extends SumServiceGrpc.SumServiceImplBase {
    @Override
    public void sum(SumRequest request, StreamObserver<SumResponse>
            responseObserver) {
        // Block 1: extract the data required
        Sum sum = request.getSum();
        int num1 = sum.getNum1();
        int num2 = sum.getNum2();
        // Block 2: create the response message
        String result = "Server Output : " + num1 + " + " + num2 + " = " + (num1+num2);
        SumResponse response = SumResponse.newBuilder()
                .setResult(result)
                .build();
        // Block 3: send the response
        responseObserver.onNext(response);
        // Block 4: complete the RPC call
        responseObserver.onCompleted();
    }
}