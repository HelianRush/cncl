package cn.net.cncl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.net.cncl.entity.Cooperation;

@Mapper
public interface CooperationMapper {

	int insertCoperation(Cooperation record);

	int updateCoperation(Cooperation record);

	Cooperation selectCoperationById(Long cooperationId);

	List<Cooperation> selectCoperation();
}