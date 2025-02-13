package com.teamarc.demo.controller;

import com.teamarc.demo.entity.Sender;
import com.teamarc.demo.entity.SenderConnectionRequest;
import com.teamarc.demo.entity.TrackingRequest;
import com.teamarc.demo.services.SenderConnectionRequestService;
import com.teamarc.demo.services.SenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sender")
public class SenderController {

    private final SenderService senderService;


    @GetMapping("/connection/request")
    public ResponseEntity<List<SenderConnectionRequest>> getConnectionRequests() {
        return ResponseEntity.ok(senderService.getConnectionRequests());
    }

    @PostMapping("/connection/accept/{receiverId}")
    public ResponseEntity<Void> acceptConnectionRequest(@PathVariable Long receiverId) {
        senderService.acceptConnectionRequest(receiverId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/connection/reject/{receiverId}")
    public ResponseEntity<Void> rejectConnectionRequest(@PathVariable Long receiverId) {
        senderService.rejectConnectionRequest(receiverId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/profile")
    public ResponseEntity<Sender> getProfile() {
        return ResponseEntity.ok(senderService.getCurrentSender());
    }

    @GetMapping("/tracking/requests")
    public ResponseEntity<List<TrackingRequest>> getTrackingRequests() {
        return ResponseEntity.ok(senderService.getTrackingRequests());
    }

    @PostMapping("/tracking/accept/{receiverId}")
    public ResponseEntity<String> acceptTrackingRequest(@PathVariable Long receiverId,@RequestBody Long time) {
        return ResponseEntity.ok(senderService.acceptTrackingRequest(receiverId,time));
    }

    @PostMapping("/tracking/reject/{receiverId}")
    public ResponseEntity<Void> rejectTrackingRequest(@PathVariable Long receiverId) {
        senderService.rejectTrackingRequest(receiverId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/tracking/cancel/{receiverId}")
    public ResponseEntity<Void> cancelTrackingRequest(@PathVariable Long receiverId) {
        senderService.cancelTrackingRequest(receiverId);
        return ResponseEntity.ok().build();
    }
}
