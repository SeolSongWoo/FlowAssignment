package com.flow.flowassignment.service;


import org.apache.ibatis.annotations.Mapper;

@Mapper
/** 파일확장자 관련 페이지 Service 계층 **/
public interface ExtensionMapper {

    int ExOverLabCheck(String name) throws Exception;

    void ExtensionAdd(String name) throws Exception;
}
