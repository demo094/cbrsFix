package com.dataart.citybikerentalservicespring.service;

import com.dataart.citybikerentalservicespring.constants.UserMessage;
import com.dataart.citybikerentalservicespring.exceptions.adminexceptions.WrongPriceIntervalDataException;
import com.dataart.citybikerentalservicespring.persistence.model.PriceInterval;
import com.dataart.citybikerentalservicespring.persistence.repo.PriceIntervalRepository;
import com.dataart.citybikerentalservicespring.view.responses.PriceIntervalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

@Service
public class PriceIntervalService {

    @Autowired
    private PriceIntervalRepository priceIntervalRepository;

    @Transactional(readOnly = true)
    public List<PriceIntervalResponse> getPriceIntervalTOs() {
        List<PriceIntervalResponse> priceIntervalResponses = new ArrayList<>();
        List<PriceInterval> priceIntervals = priceIntervalRepository.findAllByOrderByEndAsc();
        PriceIntervalResponse lastPriceIntervalResponse = null;
        for (PriceInterval priceInterval : priceIntervals) {
            if (priceInterval.getEnd() != null) {
                priceIntervalResponses.add(new PriceIntervalResponse(priceInterval));
            } else {
                lastPriceIntervalResponse = new PriceIntervalResponse(priceInterval);
            }
        }

        if(lastPriceIntervalResponse == null){
            PriceInterval priceInterval = new PriceInterval(null,
                    priceIntervals.get(priceIntervals.size() - 1).getPrice().add(BigDecimal.ONE));
            priceIntervalRepository.save(priceInterval);
            lastPriceIntervalResponse = new PriceIntervalResponse(priceInterval);
        }

        priceIntervalResponses.add(lastPriceIntervalResponse);

        return priceIntervalResponses;
    }

    @Transactional(readOnly = true)
    public PriceInterval getPriceIntervalById(int priceIntervalId) {
        return priceIntervalRepository.findOne(priceIntervalId);
    }

    @Transactional
    public void updatePriceInterval(int id, Long end, BigDecimal price) {
        PriceInterval priceInterval = priceIntervalRepository.findOne(id);
        if (end != null) {
            priceInterval.setEnd(end);
        }
        priceInterval.setPrice(price);
    }

    @Transactional
    public void deletePriceIntervalById(int priceIntervalId) {
        priceIntervalRepository.delete(priceIntervalId);
    }

    @Transactional
    public void addPriceInterval(Long end, BigDecimal price) {
        for (PriceIntervalResponse priceIntervalResponse : getPriceIntervalTOs()) {
            if (end == null && priceIntervalResponse.getEnd() == null) {
                throw new WrongPriceIntervalDataException(UserMessage.DOUBLE_NULL_PRICE_INTERVAL);
            } else if (priceIntervalResponse.getEnd() != null && end != null && end.equals(priceIntervalResponse.getEnd())) {
                throw new WrongPriceIntervalDataException(UserMessage.DOUBLE_PRICE_INTERVAL);
            } else if (priceIntervalResponse.getPrice().compareTo(price) == 0) {
                throw new WrongPriceIntervalDataException(UserMessage.DOUBLE_PRICING);
            }
        }
        priceIntervalRepository.save(new PriceInterval(end, price));
    }
}
