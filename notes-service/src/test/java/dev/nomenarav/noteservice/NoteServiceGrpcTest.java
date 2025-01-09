package dev.nomenarav.noteservice;

import dev.nomenarav.CreateNoteRequest;
import dev.nomenarav.Note;
import dev.nomenarav.NoteResponse;
import dev.nomenarav.NoteServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
class NoteServiceGrpcTest {
    private NoteServiceGrpc.NoteServiceBlockingStub noteServiceBlockingStub;

    private ManagedChannel managedChannel;

    @BeforeEach
    public void setUp() throws Exception {
        managedChannel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        noteServiceBlockingStub = NoteServiceGrpc.newBlockingStub(managedChannel);
    }


    @Test
    public void shouldCreateNoteViaGrpc(){
        CreateNoteRequest request = CreateNoteRequest.newBuilder()
                .setTitle("Test")
                .setContent("This is a test note")
                .build();
        NoteResponse note = noteServiceBlockingStub.createNote(request);
        assertNotNull(note.getId());

        assertEquals("Test", note.getTitle());
        assertEquals("This is a test note", note.getContent());

    }

    @Test
    void contextLoads(){
    }

}