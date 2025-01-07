package dev.nomenarav.noteservice.note;

import dev.nomenarav.CreateNoteRequest;
import dev.nomenarav.NoteResponse;
import dev.nomenarav.NoteServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;

@GrpcService
public class NoteGrpcService extends NoteServiceGrpc.NoteServiceImplBase {
    private final NoteService noteService;

    public NoteGrpcService(NoteService noteService) {
        this.noteService = noteService;
    }

    Logger logger = LoggerFactory.getLogger(NoteGrpcService.class);

    @Override
    public void createNote(CreateNoteRequest request, StreamObserver<NoteResponse> responseObserver) {

        logger.info("got request title =" + request.getTitle());
        logger.info("got request content =" + request.getContent());
        Note note = noteService.createNote(
                request.getTitle(),
                request.getContent(),
                null
        );
//        NoteResponse noteResponse = NoteResponse.newBuilder()
//                .setId(note.id())
//                .setTitle(request.getTitle())
//                .setContent(request.getContent())
//                .build();
//
//        responseObserver.onNext(noteResponse);
//        responseObserver.onCompleted();
    }
}
