package com.catenax.valueaddedservice.service;

import com.catenax.valueaddedservice.domain.Range;
import com.catenax.valueaddedservice.dto.CompanyUserDTO;
import com.catenax.valueaddedservice.dto.RangeDTO;
import com.catenax.valueaddedservice.repository.RangeRepository;
import com.catenax.valueaddedservice.service.mapper.CompanyUserMapper;
import com.catenax.valueaddedservice.service.mapper.RangeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Range}.
 */
@Service
@Slf4j
public class RangeService {

    @Autowired
    CompanyUserMapper companyUserMapper;

    @Autowired
    CompanyUserService companyUserService;

    private final RangeRepository rangeRepository;

    private final RangeMapper rangeMapper;



    public RangeService(RangeRepository rangeRepository, RangeMapper rangeMapper) {
        this.rangeRepository = rangeRepository;
        this.rangeMapper = rangeMapper;
    }

    //API to get All Ranges Values by User
    public List<RangeDTO> getUserRanges(CompanyUserDTO companyUser) {
        return rangeMapper.toDto(rangeRepository.findByCompanyUserNameAndCompanyUserEmailAndCompanyUserCompanyName(companyUser.getName(), companyUser.getEmail(), companyUser.getCompanyName()));
    }



    /**
     * Save a range.
     *
     * @param rangeDTO the entity to save.
     * @return the persisted entity.
     */
    public RangeDTO save(RangeDTO rangeDTO) {
        log.debug("Request to save Range");
        Range range = rangeMapper.toEntity(rangeDTO);
        range = rangeRepository.save(range);
        return rangeMapper.toDto(range);
    }

    /**
     * Save a range.
     *
     * @param rangeDTO the entity to save.
     * @return the persisted entity.
     */
    public void updateRanges(RangeDTO rangeDTO) {
        log.debug("Request to update Range for user");
        Range range = rangeMapper.toEntity(rangeDTO);
        rangeRepository.setValueForRange(range.getValue(),range.getRange(),range.getCompanyUser().getId());
    }


    /**
     * Partially update a range.
     *
     * @param rangeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RangeDTO> partialUpdate(RangeDTO rangeDTO) {
        log.debug("Request to partially update Range : {}", rangeDTO);

        return rangeRepository
                .findById(rangeDTO.getId())
                .map(existingRange -> {
                    rangeMapper.partialUpdate(existingRange, rangeDTO);

                    return existingRange;
                })
                .map(rangeRepository::save)
                .map(rangeMapper::toDto);
    }

    /**
     * Get all the ranges.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<RangeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Ranges");
        return rangeRepository.findAll(pageable).map(rangeMapper::toDto);
    }

    /**
     * Get one range by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<RangeDTO> findOne(Long id) {
        log.debug("Request to get Range : {}", id);
        return rangeRepository.findById(id).map(rangeMapper::toDto);
    }

    /**
     * Delete the range by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Range : {}", id);
        rangeRepository.deleteById(id);
    }
}
