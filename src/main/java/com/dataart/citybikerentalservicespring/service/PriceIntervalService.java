package com.dataart.citybikerentalservicespring.service;

import com.dataart.citybikerentalservicespring.persistence.model.PriceInterval;
import com.dataart.citybikerentalservicespring.persistence.repo.PriceIntervalRepository;
import com.dataart.citybikerentalservicespring.view.TO.PriceIntervalTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceIntervalService {

    @Autowired
    private PriceIntervalRepository priceIntervalRepository;

    @Transactional
    public List<PriceIntervalTO> getPriceIntervalTOs() {
        return priceIntervalRepository.findAll().stream().map(PriceIntervalTO::new).collect(Collectors.toList());
    }

    @Transactional
    public PriceInterval getPriceIntervalById(int priceIntervalId) {
        return priceIntervalRepository.findOne(priceIntervalId);
    }

    @Transactional
    public void updatePriceInterval(int id, Long start, Long end, BigDecimal price) {
        PriceInterval priceInterval = priceIntervalRepository.findOne(id);
        priceInterval.setEnd(end);
        priceInterval.setPrice(price);
        priceIntervalRepository.save(priceInterval);
    }

    @Transactional
    public void updatePriceInterval(int id, Long end, BigDecimal price) {
        PriceInterval priceInterval = priceIntervalRepository.findOne(id);
        priceInterval.setEnd(end);
        priceInterval.setPrice(price);
        priceIntervalRepository.save(priceInterval);
    }

    @Transactional
    public void deletePriceIntervalById(int priceIntervalId) {
        priceIntervalRepository.delete(priceIntervalId);
    }

    @Transactional
    public void addPriceInterval(Long end, BigDecimal price) {
        priceIntervalRepository.save(new PriceInterval(end, price));
    }

}
