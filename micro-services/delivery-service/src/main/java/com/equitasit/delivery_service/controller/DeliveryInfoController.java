package com.equitasit.delivery_service.controller;

import com.equitasit.delivery_service.dto.DeliveryInfoDTO;
import com.equitasit.delivery_service.service.DeliveryInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("delivery")
@Slf4j
public class DeliveryInfoController {

    @Autowired
    private DeliveryInfoService deliveryInfoService;


    @PostMapping
    public ResponseEntity save(@RequestBody DeliveryInfoDTO ... deliveryInfoDTO) {

        log.debug("enter");
        log.info("saving the delivery info {}", deliveryInfoDTO);

        List<DeliveryInfoDTO> savedDeliveryInfoDTOs= deliveryInfoService.save(deliveryInfoDTO);
        log.info("saved delivery info {}", savedDeliveryInfoDTOs);
        log.debug("exit");
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDeliveryInfoDTOs);
    }


    @PutMapping
    public ResponseEntity update(@RequestBody DeliveryInfoDTO deliveryInfoDTO) {
        log.debug("enter");
        log.info("updating the delivery info {}", deliveryInfoDTO);
        DeliveryInfoDTO savedDeliveryInfoDTO = deliveryInfoService.update(deliveryInfoDTO);
        log.info("updated delivery info {}", savedDeliveryInfoDTO);
        log.debug("exit");
        return ResponseEntity.ok(savedDeliveryInfoDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable("id") Integer id) {
        log.debug("enter");
        log.info("getting user info for delivery id {}", id);
        DeliveryInfoDTO deliveryInfoDTO = deliveryInfoService.getAccount(id);
        log.info("delivery info {}", deliveryInfoDTO);
        log.debug("exit");
        return ResponseEntity.ok(deliveryInfoDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable("id") Integer id) {

        log.debug("enter");
        log.info("removing delivery info for id {}", id);

        deliveryInfoService.remove(id);

        log.info("getting delivery info for id {}", id);
        log.debug("exit");
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity get() {
        log.debug("enter");
        List<DeliveryInfoDTO> deliveryInfoDTOList = deliveryInfoService.getAll();
        log.info("delivery size {}", deliveryInfoDTOList.size());
        log.debug("exit");
        return ResponseEntity.ok(deliveryInfoDTOList);
    }

    @GetMapping("bookings/{bookingId}")
    public ResponseEntity getBookings(@PathVariable("bookingId") String bookingId) {
        log.debug("enter");
        List<DeliveryInfoDTO> deliveryInfoDTOList = deliveryInfoService.getBookingInfoList(bookingId);
        log.info("delivery size {}", deliveryInfoDTOList.size());
        log.debug("exit");
        return ResponseEntity.ok(deliveryInfoDTOList);
    }


}
