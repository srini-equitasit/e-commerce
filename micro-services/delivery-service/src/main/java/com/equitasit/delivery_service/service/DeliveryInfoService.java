package com.equitasit.delivery_service.service;

import com.equitasit.delivery_service.dto.DeliveryInfoDTO;
import com.equitasit.delivery_service.entity.DeliveryInfo;
import com.equitasit.delivery_service.exception.DeliveryException;
import com.equitasit.delivery_service.repository.DeliveryInfoRepository;
import com.equitasit.delivery_service.util.MsgConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class DeliveryInfoService {

    @Autowired
    private DeliveryInfoRepository deliveryInfoRepository;

    @Autowired
    ModelMapper modelMapper;

    @Transactional
    public List<DeliveryInfoDTO> save(DeliveryInfoDTO... deliveryInfoDTOList) {
        List<DeliveryInfo> deliveryInfos = Arrays.stream(deliveryInfoDTOList).map(deliveryInfoDTO -> deliveryInfoRepository.save(modelMapper.map(deliveryInfoDTO, DeliveryInfo.class))).collect(Collectors.toList());
        List<DeliveryInfo> saved = deliveryInfoRepository.saveAll(deliveryInfos);
        return saved.stream().map(s -> modelMapper.map(s, DeliveryInfoDTO.class)).collect(Collectors.toList());
    }

    @Transactional
    public DeliveryInfoDTO update(DeliveryInfoDTO deliveryInfoDTO) {

        Optional<DeliveryInfo> existingDeliveryInfo = deliveryInfoRepository.findById(deliveryInfoDTO.getId());
        if (!existingDeliveryInfo.isPresent()) {
            throw new DeliveryException(MsgConstants.DELIVERY_INFO_NOT_FOUND);
        }

        DeliveryInfo saved = deliveryInfoRepository.save(modelMapper.map(deliveryInfoDTO, DeliveryInfo.class));
        return modelMapper.map(saved, DeliveryInfoDTO.class);
    }

    public DeliveryInfoDTO getAccount(Integer id) {
        Optional<DeliveryInfo> existingDeliveryInfo = deliveryInfoRepository.findById(id);
        if (!existingDeliveryInfo.isPresent()) {
            throw new DeliveryException(MsgConstants.DELIVERY_INFO_NOT_FOUND);
        }
        return modelMapper.map(existingDeliveryInfo.get(), DeliveryInfoDTO.class);

    }

    @Transactional
    public void remove(Integer id) {
        deliveryInfoRepository.deleteById(id);
    }

    public void remove(DeliveryInfoDTO... deliveryInfoDTOList) {
        List<Integer> idsList = Arrays.stream(deliveryInfoDTOList).map(deliveryInfoDTO -> deliveryInfoDTO.getId()).collect(Collectors.toList());
        deliveryInfoRepository.deleteAllByIdInBatch(idsList);
    }

    public List<DeliveryInfoDTO> getAll() {
        List<DeliveryInfo> deliveryInfos = deliveryInfoRepository.findAll();
        return deliveryInfos.stream().map(deliveryInfo -> modelMapper.map(deliveryInfo, DeliveryInfoDTO.class)).collect(Collectors.toList());
    }

    public List<DeliveryInfoDTO> getOrderDeliveries(Integer orderId) {
        List<DeliveryInfo> deliveryInfos = deliveryInfoRepository.findByOrderId(orderId);
        return deliveryInfos.stream().map(deliveryInfo -> modelMapper.map(deliveryInfo, DeliveryInfoDTO.class)).collect(Collectors.toList());
    }


}
