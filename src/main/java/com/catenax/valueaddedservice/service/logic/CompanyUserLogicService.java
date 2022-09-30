package com.catenax.valueaddedservice.service.logic;

import com.catenax.valueaddedservice.dto.CompanyUserDTO;
import com.catenax.valueaddedservice.service.CompanyUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class CompanyUserLogicService {

    @Autowired
    CompanyUserService companyUserService;

    public CompanyUserDTO getOrCreate(CompanyUserDTO companyUserDTO)  {
        CompanyUserDTO companyUserDTOUse = companyUserService.findBYNameEmailAndCompany(companyUserDTO);
        if(companyUserDTOUse == null){
            companyUserDTOUse =companyUserService.save(companyUserDTO);
        }

        return companyUserDTOUse;
    }
}
