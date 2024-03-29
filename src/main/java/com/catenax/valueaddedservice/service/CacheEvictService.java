package com.catenax.valueaddedservice.service;

import com.catenax.valueaddedservice.service.logic.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import static javax.management.timer.Timer.ONE_HOUR;
import static javax.management.timer.Timer.ONE_MINUTE;

@Service
@Slf4j
public class CacheEvictService {

    @Autowired
    CountryLogicService countryLogicService;

    @Autowired
    ExternalBusinessPartnersLogicService externalBusinessPartnersLogicService;

    @Autowired
    DataSourceLogicService dataSourceLogicService;

    @Autowired
    RangeLogicService rangeLogicService;

    @Autowired
    ReportLogicService reportLogicService;

    @Scheduled(fixedRate = ONE_HOUR)
    public void clearCacheCountry() {
        countryLogicService.invalidateAllCache();
        log.info("Cache for country's cleared.");
    }

    @Scheduled(fixedRate = ONE_MINUTE*15)
    public void clearCacheBpn() {
        externalBusinessPartnersLogicService.invalidateAllCache();
        log.info("Cache for bpn cleared.");
    }

    @Scheduled(fixedRate = ONE_MINUTE*15)
    public void clearCacheDataSource() {
        dataSourceLogicService.invalidateAllCache();
        log.info("Cache for DataSource cleared.");
    }

    @Scheduled(fixedRate = ONE_HOUR)
    public void clearCacheRange() {
        rangeLogicService.invalidateAllCache();
        log.info("Cache for user range cleared.");
    }

    @Scheduled(fixedRate = ONE_HOUR)
    public void clearCacheReports() {
        reportLogicService.invalidateAllCache();
        log.info("Cache for Reports cleared.");
    }
}
