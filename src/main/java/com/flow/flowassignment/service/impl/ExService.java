package com.flow.flowassignment.service.impl;

import com.flow.flowassignment.model.EXTENSION;
import com.flow.flowassignment.model.USER;
import com.flow.flowassignment.service.ExtensionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ExService implements ExtensionMapper {

    @Autowired
    ExtensionMapper extensionMapper;

    @Override
    public int ExOverLabCheck(String ex_name) throws Exception {
        int result = 0;
        try {
            result = extensionMapper.ExOverLabCheck(ex_name);
        } catch(Exception e) {
            log.error("DBServiceException",e);
            throw new Exception("중복 조회에 실패하였습니다.");
        }
        if(result != 0) throw new Exception("이미 등록된 확장자입니다.");
        return result;
    }

    @Override
    public void ExtensionAdd(EXTENSION extension) throws Exception {
        try {
            extensionMapper.ExtensionAdd(extension);
        } catch (NullPointerException e) {
            log.error("DBServiceException",e);
            throw new Exception("저장된 데이터가 없습니다.");
        } catch (Exception e) {
            log.error("DBServiceException",e);
            throw new Exception("데이터 저장에 실패했습니다.");
        }
    }

    @Override
    public void ExtensionDel(String ex_name) throws Exception {
        try {
            extensionMapper.ExtensionDel(ex_name);
        } catch (NullPointerException e) {
            log.error("DBServiceException",e);
            throw new Exception("삭제된 데이터가 없습니다..");
        } catch (Exception e) {
            log.error("DBServiceException",e);
            throw new Exception("데이터 삭제에 실패했습니다.");
        }
    }

    @Override
    public List<EXTENSION> ExtensionList(String ex_sort) throws Exception {
        List<EXTENSION> result = null;
        try {
            result = extensionMapper.ExtensionList(ex_sort);
        } catch(Exception e) {
            log.error("DBServiceException",e);
            throw new Exception("데이터 조회에 실패하였습니다.");
        }
        return result;
    }

    @Override
    public int ExtensionCount(String ex_sort) throws Exception {
        int result = 0;
        try {
            result = extensionMapper.ExtensionCount(ex_sort);
        } catch(Exception e) {
            log.error("DBServiceException",e);
            throw new Exception("갯수 조회에 실패하였습니다.");
        }
        return result;
    }

    @Override
    public Optional<USER> findByUserId(String user_id) {
        return Optional.ofNullable(extensionMapper.findByUserId(user_id).orElseThrow(() -> new UsernameNotFoundException("없는 유저입니다.")));
    }
}
