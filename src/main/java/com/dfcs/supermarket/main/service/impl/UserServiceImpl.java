package com.dfcs.supermarket.main.service.impl;

import com.dfcs.supermarket.main.entity.User;
import com.dfcs.supermarket.main.mapper.UserMapper;
import com.dfcs.supermarket.main.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caoxinyu
 * @since 2020-01-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
