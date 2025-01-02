package dev.nomenarav.noteservice.server;

import dev.nomenarav.CreateNoteRequest;
import dev.nomenarav.Note;
import dev.nomenarav.NoteServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class Server extends NoteServiceGrpc.NoteServiceImplBase {

    Logger logger = LoggerFactory.getLogger(Server.class);

    @Override
    public void createNote(CreateNoteRequest request, StreamObserver<Note> responseObserver) {

        logger.info("got request title =" + request.getTitle());
        logger.info("got request content =" + request.getContent());
        Note  note = Note.newBuilder()
                .setId("123456")
                .setTitle(request.getTitle())
                .setContent(request.getContent())
                .build();

        responseObserver.onNext(note);
        responseObserver.onCompleted();
    }
}
