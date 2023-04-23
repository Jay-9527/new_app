/**
 * @author JOJO
 * @class ResultConvert
 * @date 2023/4/22
 * @apiNote
 */

package com.depsystem.app.systemServer.util;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class ResultConvert {
    private final ModelMapper modelMapper = new ModelMapper();

    public <T, U> List<U> mapList(List<T> source,Class<U> targetClass){
        List<U> targetList = new ArrayList<>();
        for (T element : source) {
            U target = modelMapper.map(element,targetClass);
            targetList.add(target);
        }
        return targetList;
    }

    /**
     * 类型转换
     * @param source 源
     * @param destinationType 目标
     * @return 目标对象
     * @param <S> 源泛型
     * @param <D> 目标泛型
     */
    public <S, D> D map(S source, Class<D> destinationType) {
        return modelMapper.map(source, destinationType);
    }
}
