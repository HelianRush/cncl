package cn.net.cncl.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.net.cncl.entity.Celebritys;
import cn.net.cncl.mapper.CelebritysMapper;
import cn.net.cncl.service.CelebritysService;

@Service
@Transactional
public class CelebritysServiceImpl implements CelebritysService {

	private static Logger logger = LoggerFactory.getLogger(CelebritysServiceImpl.class);

	@Autowired
	public CelebritysMapper celebritysMapper;

	/**
	 * 添加名人库
	 */
	@Override
	public int insertCelebritys(Celebritys celebritys) {
		celebritys.setCelebrityId(new Date().getTime());
		// celebritys.setVideoIdFk(0L);
		// celebritys.setImageIdFk(0L);
		celebritys.setBrowseCount(0);
		return celebritysMapper.insertCelebritys(celebritys);
	}

	/**
	 * 修改名人库
	 */
	@Override
	public int updateCelebritys(Celebritys celebritys) {
		return 0;
	}

	/**
	 * 名人库列表
	 */
	@Override
	public List<Celebritys> showCelebritysList() {
		return celebritysMapper.queryCelebritys();
	}

}
