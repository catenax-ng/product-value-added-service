package com.catenax.valueaddedservice.repository;

import com.catenax.valueaddedservice.domain.DataSource;
import com.catenax.valueaddedservice.domain.enumeration.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the DataSource entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DataSourceRepository extends JpaRepository<DataSource, Long> {

    List<DataSource> findByYearPublishedAndType(Integer year, Type type);

    List<DataSource> findByYearPublishedAndCompanyUserNameAndCompanyUserEmailAndCompanyUserCompanyNameAndType(Integer year,String name,String email,String company,Type type);

    List<DataSource> findByCompanyUserNameAndCompanyUserEmailAndCompanyUserCompanyNameOrTypeOrTypeAndCompanyUserCompanyName(String name,String email,String company,Type type,Type typeCompany ,String companyName );

    List<DataSource> findByYearPublishedAndCompanyUserCompanyNameAndType(Integer year,String company, Type type);

}
