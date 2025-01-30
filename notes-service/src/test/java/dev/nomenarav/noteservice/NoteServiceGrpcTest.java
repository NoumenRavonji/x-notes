package dev.nomenarav.noteservice;

import dev.nomenarav.CreateNoteRequest;
import dev.nomenarav.Note;
import dev.nomenarav.NoteResponse;
import dev.nomenarav.NoteServiceGrpc;
import dev.nomenarav.noteservice.note.NoteGrpcService;
import dev.nomenarav.noteservice.note.NoteService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.inprocess.InProcessServerBuilder;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class NoteServiceGrpcTest {
    private NoteServiceGrpc.NoteServiceBlockingStub noteServiceBlockingStub;

    private ManagedChannel managedChannel;
    private Server server;
    private NoteService mockNoteService;

    @BeforeEach
    public void setUp() throws Exception {
        String serverName = InProcessServerBuilder.generateName();

        // Mock noteService to prevent actual Database operations
        mockNoteService = mock(NoteService.class);

        when(mockNoteService.createNote(
                anyString(),
                anyString(),
                any()
        )).thenReturn(new dev.nomenarav.noteservice.note.Note("Test", "This is a test note", null));

        // Start in-process gRPC server with the mocked service
        server = InProcessServerBuilder.forName(serverName)
                .directExecutor()
                .addService(new NoteGrpcService(mockNoteService))
                .build()
                .start();
        // Create in-process gRPC client channel
        managedChannel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        noteServiceBlockingStub = NoteServiceGrpc.newBlockingStub(managedChannel);
    }

    @AfterEach
    public void tearDown() {
        if (managedChannel != null) {
            managedChannel.shutdown();
        }
        if (server != null) {
            server.shutdown();
        }
    }

    @Test
    public void shouldCreateNoteViaGrpc(){
        CreateNoteRequest request = CreateNoteRequest.newBuilder()
                .setTitle("Test")
                .setContent("This is a test note")
                .build();
        NoteResponse note = noteServiceBlockingStub.createNote(request);
        System.out.println(note.getContent());
        assertNotNull(note.getId());

        assertEquals("Test", note.getTitle());
        assertEquals("This is a test note", note.getContent());

    }

    @Test
    void contextLoads(){
        assertNotNull(server, "gRPC server should be initialized");
    }

}