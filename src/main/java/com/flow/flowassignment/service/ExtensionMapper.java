package com.flow.flowassignment.service;


import com.flow.flowassignment.model.EXTENSION;
import com.flow.flowassignment.model.USER;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ExtensionMapper {

    /**
     * extension 테이블에 동일한 데이터가 있는지 확인합니다.
     * @Mapper ExOverLabCheck
     * @param ex_name
     * @DBTable extension
     * @return 중복값이 존재하지않으면 0, 존재하면 >= 1
     * @throws Exception
     */
    int ExOverLabCheck(String ex_name) throws Exception;

    /**
     * extension 추가
     * @Mapper ExtensionAdd
     * @param extension
     * @DBTable extension
     * @return void
     * @throws Exception
     */
    void ExtensionAdd(EXTENSION extension) throws Exception;

    /**
     * extension 삭제
     * @Mapper ExtensionDel
     * @param ex_name
     * @DBTable extension
     * @return void
     * @throws Exception
     */
    void ExtensionDel(String ex_name) throws Exception;

    /**
     * extension 조회
     * @Mapper ExOverLabCheck
     * @param ex_name
     * @DBTable extension
     * @return List<EXTENSION>
     * @throws Exception
     */
    List<EXTENSION> ExtensionList(String ex_sort) throws Exception;

    /**
     * Extension의 Count 조회
     * @Mapper ExtensionCount
     * @Param ex_Sort
     * @DBTable extension
     * @return Custom Extension의 count
     * @throws Exception
     */
    int ExtensionCount(String ex_sort) throws  Exception;

    Optional<USER> findByUserId(String name);
}
