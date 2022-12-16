package com.flow.flowassignment.service.impl;

import com.flow.flowassignment.model.EXTENSION;
import com.flow.flowassignment.service.ExtensionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
            throw new Exception("중복 조회에 실패하였습니다.");
        }
        return result;
    }

    @Override
    public void ExtensionAdd(String ex_name) throws Exception {
        try {
            extensionMapper.ExtensionAdd(ex_name);
        } catch (NullPointerException e) {
            throw new Exception("저장된 데이터가 없습니다.");
        } catch (Exception e) {
            throw new Exception("데이터 저장에 실패했습니다.");
        }
    }

    @Override
    public void ExtensionDel(String ex_name) throws Exception {
        try {
            extensionMapper.ExtensionDel(ex_name);
        } catch (NullPointerException e) {
            throw new Exception("삭제된 데이터가 없습니다..");
        } catch (Exception e) {
            throw new Exception("데이터 삭제에 실패했습니다.");
        }
    }

    @Override
    public List<EXTENSION> ExtensionList() throws Exception {
        List<EXTENSION> result = null;
        try {
            result = extensionMapper.ExtensionList();
        } catch(Exception e) {
            throw new Exception("데이터 조회에 실패하였습니다.");
        }
        return result;
    }
}
