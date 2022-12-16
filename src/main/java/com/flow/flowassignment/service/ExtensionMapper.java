package com.flow.flowassignment.service;


import com.flow.flowassignment.model.EXTENSION;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
/** 파일확장자 관련 페이지 Service 계층 **/
public interface ExtensionMapper {

    int ExOverLabCheck(String ex_name) throws Exception;

    void ExtensionAdd(String ex_name) throws Exception;

    void ExtensionDel(String ex_name) throws Exception;

    List<EXTENSION> ExtensionList() throws Exception;
}
