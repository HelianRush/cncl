/*
Navicat MySQL Data Transfer

Source Server         : MYSQL55
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : database_cncl

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-09-28 05:54:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_user`;
CREATE TABLE `t_admin_user` (
  `ADMIN_USER_ID` bigint(16) NOT NULL COMMENT '管理员ID',
  `ADMIN_USER_NAME` varchar(50) DEFAULT NULL COMMENT '管理员名称',
  `PASSWORD` varchar(50) DEFAULT NULL COMMENT '密码',
  `NAME` varchar(50) DEFAULT NULL COMMENT '姓名',
  `NICK_NAME` varchar(50) DEFAULT NULL COMMENT '昵称',
  `MOBILE_PHONE` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `TELEPHONE` varchar(20) DEFAULT NULL COMMENT '座机号码',
  `EMAIL` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `CREATE_TIME` date DEFAULT NULL COMMENT '创建时间',
  `LAST_LOGIN_TIME` date DEFAULT NULL COMMENT '上次登录时间',
  `LAST_LOGIN_IP` varchar(15) DEFAULT NULL COMMENT '上次登录IP地址',
  PRIMARY KEY (`ADMIN_USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_admin_user
-- ----------------------------
INSERT INTO `t_admin_user` VALUES ('1538083957146', 'testUser0', 'admin0', '测试用户0', '用户0', '010-8000000', '18900000000', 'xxx@cncl.net.cn', '2018-09-28', '2018-09-28', '172.0.01');
INSERT INTO `t_admin_user` VALUES ('1538083961878', 'testUser1', 'admin1', '测试用户1', '用户1', '010-8000000', '18900000000', 'xxx@cncl.net.cn', '2018-09-28', '2018-09-28', '172.0.01');
INSERT INTO `t_admin_user` VALUES ('1538083994672', 'testUser2', 'admin2', '测试用户2', '用户2', '010-8000000', '18900000000', 'xxx@cncl.net.cn', '2018-09-28', '2018-09-28', '172.0.01');
INSERT INTO `t_admin_user` VALUES ('1538083998636', 'testUser3', 'admin3', '测试用户3', '用户3', '010-8000000', '18900000000', 'xxx@cncl.net.cn', '2018-09-28', '2018-09-28', '172.0.01');
INSERT INTO `t_admin_user` VALUES ('1538083998691', 'testUser4', 'admin4', '测试用户4', '用户4', '010-8000000', '18900000000', 'xxx@cncl.net.cn', '2018-09-28', '2018-09-28', '172.0.01');
INSERT INTO `t_admin_user` VALUES ('1538083998747', 'testUser5', 'admin5', '测试用户5', '用户5', '010-8000000', '18900000000', 'xxx@cncl.net.cn', '2018-09-28', '2018-09-28', '172.0.01');
INSERT INTO `t_admin_user` VALUES ('1538083998813', 'testUser6', 'admin6', '测试用户6', '用户6', '010-8000000', '18900000000', 'xxx@cncl.net.cn', '2018-09-28', '2018-09-28', '172.0.01');
INSERT INTO `t_admin_user` VALUES ('1538083998869', 'testUser7', 'admin7', '测试用户7', '用户7', '010-8000000', '18900000000', 'xxx@cncl.net.cn', '2018-09-28', '2018-09-28', '172.0.01');
INSERT INTO `t_admin_user` VALUES ('1538083998924', 'testUser8', 'admin8', '测试用户8', '用户8', '010-8000000', '18900000000', 'xxx@cncl.net.cn', '2018-09-28', '2018-09-28', '172.0.01');
INSERT INTO `t_admin_user` VALUES ('1538083998980', 'testUser9', 'admin9', '测试用户9', '用户9', '010-8000000', '18900000000', 'xxx@cncl.net.cn', '2018-09-28', '2018-09-28', '172.0.01');
INSERT INTO `t_admin_user` VALUES ('1538083999036', 'testUser10', 'admin10', '测试用户10', '用户10', '010-8000000', '18900000000', 'xxx@cncl.net.cn', '2018-09-28', '2018-09-28', '172.0.01');
INSERT INTO `t_admin_user` VALUES ('1538083999102', 'testUser11', 'admin11', '测试用户11', '用户11', '010-8000000', '18900000000', 'xxx@cncl.net.cn', '2018-09-28', '2018-09-28', '172.0.01');
INSERT INTO `t_admin_user` VALUES ('1538083999161', 'testUser12', 'admin12', '测试用户12', '用户12', '010-8000000', '18900000000', 'xxx@cncl.net.cn', '2018-09-28', '2018-09-28', '172.0.01');
INSERT INTO `t_admin_user` VALUES ('1538083999213', 'testUser13', 'admin13', '测试用户13', '用户13', '010-8000000', '18900000000', 'xxx@cncl.net.cn', '2018-09-28', '2018-09-28', '172.0.01');
INSERT INTO `t_admin_user` VALUES ('1538083999269', 'testUser14', 'admin14', '测试用户14', '用户14', '010-8000000', '18900000000', 'xxx@cncl.net.cn', '2018-09-28', '2018-09-28', '172.0.01');
INSERT INTO `t_admin_user` VALUES ('1538083999324', 'testUser15', 'admin15', '测试用户15', '用户15', '010-8000000', '18900000000', 'xxx@cncl.net.cn', '2018-09-28', '2018-09-28', '172.0.01');
INSERT INTO `t_admin_user` VALUES ('1538083999379', 'testUser16', 'admin16', '测试用户16', '用户16', '010-8000000', '18900000000', 'xxx@cncl.net.cn', '2018-09-28', '2018-09-28', '172.0.01');
INSERT INTO `t_admin_user` VALUES ('1538083999446', 'testUser17', 'admin17', '测试用户17', '用户17', '010-8000000', '18900000000', 'xxx@cncl.net.cn', '2018-09-28', '2018-09-28', '172.0.01');
INSERT INTO `t_admin_user` VALUES ('1538083999502', 'testUser18', 'admin18', '测试用户18', '用户18', '010-8000000', '18900000000', 'xxx@cncl.net.cn', '2018-09-28', '2018-09-28', '172.0.01');
INSERT INTO `t_admin_user` VALUES ('1538083999558', 'testUser19', 'admin19', '测试用户19', '用户19', '010-8000000', '18900000000', 'xxx@cncl.net.cn', '2018-09-28', '2018-09-28', '172.0.01');
INSERT INTO `t_admin_user` VALUES ('1538083999613', 'testUser20', 'admin20', '测试用户20', '用户20', '010-8000000', '18900000000', 'xxx@cncl.net.cn', '2018-09-28', '2018-09-28', '172.0.01');
INSERT INTO `t_admin_user` VALUES ('1538083999668', 'testUser21', 'admin21', '测试用户21', '用户21', '010-8000000', '18900000000', 'xxx@cncl.net.cn', '2018-09-28', '2018-09-28', '172.0.01');
INSERT INTO `t_admin_user` VALUES ('1538083999725', 'testUser22', 'admin22', '测试用户22', '用户22', '010-8000000', '18900000000', 'xxx@cncl.net.cn', '2018-09-28', '2018-09-28', '172.0.01');
INSERT INTO `t_admin_user` VALUES ('1538083999791', 'testUser23', 'admin23', '测试用户23', '用户23', '010-8000000', '18900000000', 'xxx@cncl.net.cn', '2018-09-28', '2018-09-28', '172.0.01');
INSERT INTO `t_admin_user` VALUES ('1538083999847', 'testUser24', 'admin24', '测试用户24', '用户24', '010-8000000', '18900000000', 'xxx@cncl.net.cn', '2018-09-28', '2018-09-28', '172.0.01');
INSERT INTO `t_admin_user` VALUES ('1538083999902', 'testUser25', 'admin25', '测试用户25', '用户25', '010-8000000', '18900000000', 'xxx@cncl.net.cn', '2018-09-28', '2018-09-28', '172.0.01');
INSERT INTO `t_admin_user` VALUES ('1538083999958', 'testUser26', 'admin26', '测试用户26', '用户26', '010-8000000', '18900000000', 'xxx@cncl.net.cn', '2018-09-28', '2018-09-28', '172.0.01');
INSERT INTO `t_admin_user` VALUES ('1538084000013', 'testUser27', 'admin27', '测试用户27', '用户27', '010-8000000', '18900000000', 'xxx@cncl.net.cn', '2018-09-28', '2018-09-28', '172.0.01');
INSERT INTO `t_admin_user` VALUES ('1538084000060', 'testUser28', 'admin28', '测试用户28', '用户28', '010-8000000', '18900000000', 'xxx@cncl.net.cn', '2018-09-28', '2018-09-28', '172.0.01');
INSERT INTO `t_admin_user` VALUES ('1538084000113', 'testUser29', 'admin29', '测试用户29', '用户29', '010-8000000', '18900000000', 'xxx@cncl.net.cn', '2018-09-28', '2018-09-28', '172.0.01');
INSERT INTO `t_admin_user` VALUES ('10000000000000001', 'admin', 'admin', 'admin', 'admin', '18900000000', '010-00000000', 'admin@cncl.net.cn', '2018-09-15', '2018-09-15', '172.0.0.1');

-- ----------------------------
-- Table structure for t_celebritys
-- ----------------------------
DROP TABLE IF EXISTS `t_celebritys`;
CREATE TABLE `t_celebritys` (
  `CELEBRITY_ID` bigint(16) NOT NULL COMMENT '名人ID',
  `CELEBRITY_NAME` varchar(50) DEFAULT NULL COMMENT '名称',
  `ANOTHER_NAME` varchar(50) DEFAULT NULL COMMENT '别名',
  `FOREIGN_NAME` varchar(50) DEFAULT NULL COMMENT '外文名称',
  `NATIONALITY` varchar(20) DEFAULT NULL COMMENT '国籍',
  `NATION` varchar(10) DEFAULT NULL COMMENT '民族',
  `CONSTELLATION` varchar(10) DEFAULT NULL COMMENT '星座',
  `BLOOD_TYPE` int(10) DEFAULT NULL COMMENT '血型',
  `HEIGHT` int(10) DEFAULT NULL COMMENT '身高',
  `BIRTHPLACE` varchar(50) DEFAULT NULL COMMENT '出生地',
  `NATIVE_PLACE` varchar(50) DEFAULT NULL COMMENT '籍贯',
  `ANCESTRAL_HOME` varchar(50) DEFAULT NULL COMMENT '祖籍',
  `IDENTITY_CARD` varchar(20) DEFAULT NULL COMMENT '身份证',
  `CERTIFICATE_CODE` varchar(255) DEFAULT NULL COMMENT '证书编号',
  `CERTIFICATE_NAME` varchar(255) DEFAULT NULL COMMENT '证书名称',
  `PROFESSION` varchar(50) DEFAULT NULL COMMENT '职业',
  `COMPANY` varchar(50) DEFAULT NULL COMMENT '公司',
  `REPRESENTATIVE_NAME` varchar(1000) DEFAULT NULL COMMENT '代表作品',
  `ACHIEVEMENT` varchar(1000) DEFAULT NULL COMMENT '主要成就',
  `BIRTHDAY` date DEFAULT NULL COMMENT '出生日期',
  `DEATHDAY` date DEFAULT NULL COMMENT '逝世日期',
  `OUTLINE` longtext COMMENT '人物摘要',
  `INTRODUCE` longtext COMMENT '人物介绍',
  `VIDEO_ID_FK` bigint(16) DEFAULT NULL COMMENT '视频ID',
  `IMAGE_ID_FK` bigint(16) DEFAULT NULL COMMENT '图片ID',
  `BROWSE_COUNT` int(11) DEFAULT NULL COMMENT '浏览计数',
  PRIMARY KEY (`CELEBRITY_ID`),
  KEY `C_VIDEO_FK` (`VIDEO_ID_FK`),
  KEY `C_IMAGE_FK` (`IMAGE_ID_FK`),
  CONSTRAINT `C_IMAGE_FK` FOREIGN KEY (`IMAGE_ID_FK`) REFERENCES `t_images` (`IMAGE_ID`),
  CONSTRAINT `C_VIDEO_FK` FOREIGN KEY (`VIDEO_ID_FK`) REFERENCES `t_videos` (`VIDEO_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_celebritys
-- ----------------------------
INSERT INTO `t_celebritys` VALUES ('1537760814542', '测试用户2', '测试用户2', '测试用户2', '测试用户2', '测试用户2', '12', '12', '123', '测试用户2', '测试用户2', '测试用户2', '123', '123', '123', '123', '123', '', '123', '2018-09-24', '2018-09-24', null, '<p><img src=\"http://localhost:8080//upload/20180924/88711537760808249.jpg\"/>				</p><p>这里我可以写一些输入提示</p><p>\r\n				</p>', null, null, '0');
INSERT INTO `t_celebritys` VALUES ('1537866724496', '测试用户3', '测试用户3', '测试用户3', '123', '123', '123', '123', '123', '123123', '123', '123123', '123', '123', '123', '123', '123', '', '123', '2018-09-25', '2018-09-25', null, '<p><img src=\"http://localhost:8080//upload/20180925/361537866722065.gif\"/>				</p><p>这里我可以写一些输入提示</p><p>\r\n				</p>', null, null, '0');
INSERT INTO `t_celebritys` VALUES ('1537866832966', '测试用户4', '测试用户4', '测试用户4', '123', '123', '123', '123', '123', '123123', '123', '123123', '123', '123', '123', '123', '123', '', '123', '2018-09-25', '2018-09-25', null, '<p><img src=\"http://localhost:8080//upload/20180925/18241537866830501.jpg\"/>				</p><p>这里我可以写一些输入提示</p><p>\r\n				</p>', null, null, '0');
INSERT INTO `t_celebritys` VALUES ('1537867035909', '测试用户4', '测试用户4', '测试用户4', '123', '123', '123', '123', '123', '123123', '123', '123123', '123', '123', '123', '123', '123', '', '123', '2018-09-25', '2018-09-25', null, '<p><img src=\"http://localhost:8080//upload/20180925/18241537866830501.jpg\"/>				</p><p>这里我可以写一些输入提示</p><p>\r\n				</p>', null, null, '0');
INSERT INTO `t_celebritys` VALUES ('1537867660274', '测试用户5', '测试用户5', '测试用户5', '中国', '汉', '射手', '10', '100', '北京', '北京', '北京', '100100100010101100', '110-110-110', '测试证书', '测试职业', '测试公司', '', '成就', '2018-09-25', '2018-09-25', null, '<p>				</p><p>这里我可以写一些输入提示</p><p>\r\n				<img src=\"http://img.baidu.com/hi/jx2/j_0002.gif\"/></p>', null, null, '0');
INSERT INTO `t_celebritys` VALUES ('1537868893096', '测试用户6', '测试用户6', '测试用户6', '123', '123', '123', '10', '123', '123', '123', '123123', '123', '123', '123', '123', '123', '', '23', '2018-09-25', '2018-09-25', null, '<p>				</p><p>这里我可以写一些输入提示</p><p>\r\n				</p>', null, null, '0');
INSERT INTO `t_celebritys` VALUES ('1537869099600', '', '', '', '', '', '', null, null, '', '', '', '', '', '', '', '', '', '', null, null, null, '<p>				</p><p>这里我可以写一些输入提示</p><p>\r\n				</p>', null, null, '0');
INSERT INTO `t_celebritys` VALUES ('1537869605077', '123', '', '', '', '', '', null, null, '', '', '', '', '', '', '', '', '', '', null, null, null, '<p>				</p><p>这里我可以写一些输入提示</p><p>\r\n				</p>', null, null, '0');
INSERT INTO `t_celebritys` VALUES ('1537873916366', '测试用户7', '测试用户7', '测试用户7', '123', '123', '123', '10', '123', '123', '123', '123123', '123', '123', '123', '123', '123', '1232123123', '123', '2018-09-25', '2018-09-25', null, '<p>				</p><p>这里我可以写一些输入提示</p><p>\r\n				<img src=\"http://img.baidu.com/hi/jx2/j_0024.gif\"/></p>', null, null, '0');
INSERT INTO `t_celebritys` VALUES ('1537874270900', '测试用户8', '测试用户8', '测试用户8', '123', '123', '123', '10', '123', '123', '123', '123123', '123', '123', '123', '123', '123', '1232123123', '123', '2018-09-25', '2018-09-25', null, '<p>				</p><p>这里我可以写一些输入提示</p><p>\r\n				</p>', null, null, '0');
INSERT INTO `t_celebritys` VALUES ('1537874379375', '测试用户9', '测试用户8', '测试用户8', '123', '123', '123', '10', '123', '123', '123', '123123', '123', '123', '123', '123', '123', '1232123123', '123', '2018-09-25', '2018-09-25', null, '<p>				</p><p>这里我可以写一些输入提示</p><p>\r\n				</p>', null, null, '0');
INSERT INTO `t_celebritys` VALUES ('1537874943426', '123', '', '', '', '', '', null, null, '', '', '', '', '', '', '', '', '', '', null, null, null, '<p>				</p><p>这里我可以写一些输入提示</p><p>\r\n				</p>', null, null, '0');
INSERT INTO `t_celebritys` VALUES ('1537875417276', '测试用户7', '', '', '', '', '', null, null, '', '', '', '', '', '', '', '', '', '', null, null, null, '<p>				</p><p>这里我可以写一些输入提示</p><p>\r\n				</p>', null, null, '0');
INSERT INTO `t_celebritys` VALUES ('1000000000000001', '测试用户1', '测试用户1', '测试用户1', '测试用户1', '测试用户1', '12,12', null, '123', '测试用户1', '测试用户1', '测试用户1', '123', '123', '123', '123', '123', null, '123,123', '2018-09-24', '2018-09-24', null, '<p><img src=\"http://localhost:8080//upload/20180924/65951537758747368.gif\"/>				</p><p>这里我可以写一些输入提示</p><p>\r\n				</p>', null, null, '0');

-- ----------------------------
-- Table structure for t_images
-- ----------------------------
DROP TABLE IF EXISTS `t_images`;
CREATE TABLE `t_images` (
  `IMAGE_ID` bigint(16) NOT NULL COMMENT '图片ID',
  `IMAGE_CODE` varchar(16) NOT NULL COMMENT '图片编码',
  `IMAGE_NAME` varchar(255) DEFAULT NULL COMMENT '图片名称',
  `IMAGE_EXTENSION` varchar(10) DEFAULT NULL COMMENT '扩展名',
  `IMAGE_TITLE` varchar(255) DEFAULT NULL COMMENT '图片标题',
  `IMAGE_PATH` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `IMAGE_OUTLINE` longtext COMMENT '图片概要',
  `IMAGE_CONTENT` longtext COMMENT '图片配文',
  PRIMARY KEY (`IMAGE_ID`),
  UNIQUE KEY `IMAGE_CODE_IN` (`IMAGE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_images
-- ----------------------------

-- ----------------------------
-- Table structure for t_news
-- ----------------------------
DROP TABLE IF EXISTS `t_news`;
CREATE TABLE `t_news` (
  `NEWS_ID` bigint(16) NOT NULL COMMENT '资讯ID',
  `NEWS_TYPE_FK` bigint(16) NOT NULL COMMENT '资讯类别ID',
  `NEWS_TITEL` varchar(255) DEFAULT NULL COMMENT '资讯标题',
  `NEWS_OUTLINE` longtext COMMENT '资讯摘要',
  `NEWS_CONTENT` longtext COMMENT '资讯内容',
  `VIDEO_ID_FK` bigint(16) DEFAULT NULL COMMENT '视频ID',
  `IMAGE_ID_FK` bigint(16) DEFAULT NULL COMMENT '图片ID',
  `ADMIN_USER_ID_FK` bigint(16) DEFAULT NULL COMMENT '作者',
  `CEATE_TIME` date DEFAULT NULL COMMENT '创建时间',
  `BROWSE_COUNT` int(11) DEFAULT NULL COMMENT '浏览计数',
  PRIMARY KEY (`NEWS_ID`),
  KEY `NEWS_USER_FK` (`ADMIN_USER_ID_FK`),
  KEY `NEWS_VIDEO_FK` (`VIDEO_ID_FK`),
  KEY `NEWS_IMAGE_FK` (`IMAGE_ID_FK`),
  CONSTRAINT `NEWS_IMAGE_FK` FOREIGN KEY (`IMAGE_ID_FK`) REFERENCES `t_images` (`IMAGE_ID`),
  CONSTRAINT `NEWS_USER_FK` FOREIGN KEY (`ADMIN_USER_ID_FK`) REFERENCES `t_admin_user` (`ADMIN_USER_ID`),
  CONSTRAINT `NEWS_VIDEO_FK` FOREIGN KEY (`VIDEO_ID_FK`) REFERENCES `t_videos` (`VIDEO_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_news
-- ----------------------------

-- ----------------------------
-- Table structure for t_news_type
-- ----------------------------
DROP TABLE IF EXISTS `t_news_type`;
CREATE TABLE `t_news_type` (
  `NEWS_TYPE_ID` bigint(16) NOT NULL COMMENT '资源类别ID',
  `NEWS_TYPE_CODE` varchar(16) DEFAULT NULL COMMENT '资源类别编码',
  `NEWS_TYPE_NAME` varchar(20) DEFAULT NULL COMMENT '资源类别名称',
  PRIMARY KEY (`NEWS_TYPE_ID`),
  UNIQUE KEY `NEWS_TYPE_CODE_IN` (`NEWS_TYPE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_news_type
-- ----------------------------

-- ----------------------------
-- Table structure for t_videos
-- ----------------------------
DROP TABLE IF EXISTS `t_videos`;
CREATE TABLE `t_videos` (
  `VIDEO_ID` bigint(16) NOT NULL COMMENT '视频ID',
  `VIDEO_CODE` varchar(16) NOT NULL COMMENT '视频编码',
  `VIDEO_NAME` varchar(255) DEFAULT NULL COMMENT '视频名称',
  `VIDEO_EXTENSION` varchar(10) DEFAULT NULL COMMENT '扩展名',
  `VIDEO_TITLE` varchar(255) DEFAULT NULL COMMENT '视频标题',
  `VIDEO_PATH` varchar(255) DEFAULT NULL COMMENT '视频地址',
  `VIDEO_OUTLINE` longtext COMMENT '视频概要',
  `VIDEO_CONTENT` longtext COMMENT '视频配文',
  PRIMARY KEY (`VIDEO_ID`),
  UNIQUE KEY `VIDEO_CODE_IN` (`VIDEO_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_videos
-- ----------------------------

-- ----------------------------
-- Table structure for t_web_info
-- ----------------------------
DROP TABLE IF EXISTS `t_web_info`;
CREATE TABLE `t_web_info` (
  `WEB_ID` int(1) NOT NULL COMMENT 'ID',
  `WEB_NAME` varchar(50) DEFAULT NULL COMMENT '网站名称',
  `DOMAIN_NAME` varchar(50) DEFAULT NULL COMMENT '域名',
  `RECORDS_CODE` varchar(255) DEFAULT NULL COMMENT '备案编号',
  `COMPANY` varchar(255) DEFAULT NULL COMMENT '公司名称',
  `LEGAL_PERSON` varchar(255) DEFAULT NULL COMMENT '法人_站长',
  `IMAGE_LOGO` varchar(255) DEFAULT NULL COMMENT 'LOGO',
  `WEB_EMAIL` varchar(255) DEFAULT NULL COMMENT '网站邮箱',
  PRIMARY KEY (`WEB_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_web_info
-- ----------------------------
INSERT INTO `t_web_info` VALUES ('1', '中华名人库', 'http://www.cncl.net.cn', '京ICP备案00000000号-1', '测试公司', '测试', 'logo1.png', 'abc@cncl.net.cn');
