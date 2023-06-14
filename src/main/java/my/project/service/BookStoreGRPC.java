package my.project.service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import my.project.entity.Book;
import my.grpc.proto.BookServiceGrpc;
import my.grpc.proto.BookServiceOuterClass;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookStoreGRPC {
    public void saveBook(Book book) {
        BookServiceOuterClass.BookRequest request = BookServiceOuterClass.BookRequest
                .newBuilder()
                .setName(book.getTitle())
                .build();

        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8081")
                .usePlaintext().build();

        BookServiceGrpc.BookServiceBlockingStub stub = BookServiceGrpc.newBlockingStub(channel);

        BookServiceOuterClass.BookResponse response = stub.saveBook(request);
        book.setExternalId(UUID.fromString(response.getExternalId()));
    }
}
